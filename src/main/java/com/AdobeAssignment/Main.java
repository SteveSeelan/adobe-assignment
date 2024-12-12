package com.AdobeAssignment;
import com.AdobeAssignment.model.Lead;
import com.AdobeAssignment.model.LeadContainer;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;

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

        try (InputStream stream = new FileInputStream(filepath)) {
            LeadContainer leads = objectMapper.readValue(new File(filepath), LeadContainer.class);

            System.out.println("Parsed Leads:");
            leads.getLeads().forEach(System.out::println);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}