package com.AdobeAssignment.deduplication;

import java.util.*;

public class DeDuplicator {
    public static <T> Collection<T> getDeduplicatedResult(Collection<T> items, DeDuplicationStrategy<T> strategy) {
        Map<String, T> uniqueMap = new HashMap<>();

        for (T item : items) {
            String field = strategy.getField(item);
            T existingItem = uniqueMap.get(field);

            if (strategy.shouldUpdate(existingItem, item)) {
                logChange(field, existingItem, item);
                uniqueMap.put(field, item);
            }
            else {
                uniqueMap.putIfAbsent(field, item);
            }
        }

        return uniqueMap.values();
    }

    private static <T> void logChange(String field, T existing, T candidate) {
        if (existing != null) {
            System.out.println("########################################################");
            System.out.printf("Logging change due to: %s %n", field);
            System.out.printf("Before: \"%s\" %n --------------------> %nAfter: \"%s\"%n", existing, candidate);
            System.out.println("########################################################");
            System.out.println();
        }
    }
}
