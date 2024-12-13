package com.AdobeAssignment.deduplication;

import com.AdobeAssignment.model.Lead;
import com.AdobeAssignment.model.LeadContainer;

import java.util.Collection;

public class LeadProcessor {
    public static Collection<Lead> processLeads(LeadContainer leadContainer) {
        DeDuplicationStrategy<Lead> idDeDupStrategy = new FieldBasedDeDuplicationStrategy(Lead::get_id);
        DeDuplicationStrategy<Lead> emailDeDupStrategy = new FieldBasedDeDuplicationStrategy(Lead::getEmail);
        Collection<Lead> uniqueByIdLeads = DeDuplicator.getDeduplicatedResult(leadContainer.getLeads(), idDeDupStrategy);

        return DeDuplicator.getDeduplicatedResult(uniqueByIdLeads, emailDeDupStrategy);
    }
}
