package com.AdobeAssignment.deduplication;

import com.AdobeAssignment.model.Lead;

import java.util.function.Function;

public class FieldBasedDeDuplicationStrategy implements DeDuplicationStrategy<Lead> {
    private final Function<Lead, String> fieldExtractor;

    public FieldBasedDeDuplicationStrategy(Function<Lead, String> fieldExtractor) {
        this.fieldExtractor = fieldExtractor;
    }

    @Override
    public String getField(Lead lead) {
        return fieldExtractor.apply(lead);
    }

    @Override
    public boolean shouldUpdate(Lead existingLead, Lead candidateLead) {
        if (existingLead == null) return true;
        return candidateLead.getEntryDate().isAfter(existingLead.getEntryDate())
                || candidateLead.getEntryDate().equals(existingLead.getEntryDate());
    }
}
