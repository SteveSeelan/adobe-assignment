package com.AdobeAssignment;

import com.AdobeAssignment.deduplication.LeadProcessor;
import com.AdobeAssignment.model.Lead;
import com.AdobeAssignment.model.LeadContainer;
import com.AdobeAssignment.utilities.CliOptions;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class App {
    private final CliOptions cliOptions;

    public App(CliOptions cliOptions) {
        this.cliOptions = cliOptions;
    }

    public void run() {
        String inputFilePath = cliOptions.getInputFilePath();
        String outputFilePath = cliOptions.getOutputFilePath();
        System.out.printf("Input file path: %s%n", inputFilePath);
        System.out.printf("Output file path: %s%n", outputFilePath);


        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();

        try {
            LeadContainer leadContainer = objectMapper.readValue(new File(inputFilePath), LeadContainer.class);
            if (leadContainer == null || leadContainer.getLeads().isEmpty()) {
                throw new RuntimeException("No valid Leads");
            }
            leadContainer.sortLeadsByEntryDateAscending();
            List<Lead> processedLeads = LeadProcessor.processLeads(leadContainer).stream().toList();

            LeadContainer deDuplicatedLeadContainer = new LeadContainer(processedLeads);
            deDuplicatedLeadContainer.sortLeadsByEntryDateAscending();
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(outputFilePath), deDuplicatedLeadContainer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
