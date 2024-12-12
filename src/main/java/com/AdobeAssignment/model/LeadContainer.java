package com.AdobeAssignment.model;

import java.util.Comparator;
import java.util.List;

public class LeadContainer {
    private List<Lead> leads;

    public LeadContainer() {}

    public LeadContainer(List<Lead> leads) {
        this.leads = leads;
    }

    public List<Lead> getLeads() {
        return leads;
    }

    public void setLeads(List<Lead> leads) {
        this.leads = leads;
    }

    public void sortLeadsByEntryDateDescending() {
        leads.sort(Comparator.comparing(Lead::getEntryDate).reversed());
    }

    public void sortLeadsByEntryDateAscending() {
        leads.sort(Comparator.comparing(Lead::getEntryDate));
    }
}
