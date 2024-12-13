package com.AdobeAssignment.deduplication;

public interface DeDuplicationStrategy<T> {
    String getField(T field);
    boolean shouldUpdate(T existingLead, T candidateLead);
}
