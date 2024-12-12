package com.AdobeAssignment;
import com.AdobeAssignment.model.Lead;
import com.AdobeAssignment.model.LeadContainer;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Please provide path of JSON file");
            return;
        }

        String filepath = args[0];
        System.out.println("JSON file: " + filepath);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();

        try {
            LeadContainer leads = objectMapper.readValue(new File(filepath), LeadContainer.class);
            if (leads == null || leads.getLeads().isEmpty()) {
                System.out.println("No valid Leads:");
                return;
            }
            LeadContainer deDuplicatedLeads = new LeadContainer(deDupLeads(leads));
            deDuplicatedLeads.sortLeadsByEntryDateAscending();
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File("deduplicated_leads.json"), deDuplicatedLeads);
            deDuplicatedLeads.getLeads().forEach(System.out::println);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
    private static List<Lead> deDupLeads(LeadContainer leads) {
        Map<String, Lead> uniqueById = new HashMap<>();

        for (Lead lead : leads.getLeads()) {
            String id = lead.get_id();

            Lead existingLead = uniqueById.get(id);

            boolean update = shouldUpdate(existingLead, lead);

            if (update) {
//                logChange("id", existingLead, lead);
                uniqueById.put(id, lead);
            }
             else {
                uniqueById.putIfAbsent(id, lead);
            }
        }

        Map<String, Lead> uniqueByEmail = new HashMap<>();

        for (Lead lead : uniqueById.values()) {
            String email = lead.getEmail();

            Lead existingLead = uniqueByEmail.get(email);

            boolean update = shouldUpdate(existingLead, lead);

            if (update) {
//                logChange("email", existingLead, lead);
                uniqueByEmail.put(email, lead);
            }
            else {
                uniqueByEmail.putIfAbsent(email, lead);
            }
        }

        return new ArrayList<>(uniqueByEmail.values());
    }

    private static void logChange(String idOrField, Lead from, Lead to) {
        if (from == null || to == null) {
            return;
        }
        System.out.println("----------------------------------------------------");
        System.out.println("Lead Change: " + from);
        System.out.println("###### Updating " + idOrField + " ########");
        System.out.println("Lead Change: " + to);
        System.out.println("----------------------------------------------------");
    }

    private static boolean shouldUpdate(Lead existingLead, Lead candidateLead) {
        if (existingLead == null) return true;
        return candidateLead.getEntryDate().isAfter(existingLead.getEntryDate())
                || candidateLead.getEntryDate().equals(existingLead.getEntryDate());
    }
}