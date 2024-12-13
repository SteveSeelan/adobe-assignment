import com.AdobeAssignment.deduplication.DeDuplicationStrategy;
import com.AdobeAssignment.deduplication.DeDuplicator;
import com.AdobeAssignment.deduplication.FieldBasedDeDuplicationStrategy;
import com.AdobeAssignment.model.LeadContainer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import com.AdobeAssignment.model.Lead;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DeDuplicationTest {
    ObjectMapper objectMapper = new ObjectMapper().findAndRegisterModules();

    @Test
    void testDeDuplicateById() throws IOException {
        LeadContainer leadContainer = objectMapper.readValue(DeDuplicationTest.class.getResourceAsStream("/similarId.json"), LeadContainer.class);
        if (leadContainer == null || leadContainer.getLeads().isEmpty()) {
            throw new RuntimeException("No valid Leads");
        }
        leadContainer.sortLeadsByEntryDateAscending();
        DeDuplicationStrategy<Lead> idDeDupStrategy = new FieldBasedDeDuplicationStrategy(Lead::get_id);
        List<Lead> processedLeads = DeDuplicator.getDeduplicatedResult(leadContainer.getLeads(), idDeDupStrategy).stream().toList();
        LeadContainer deDuplicatedLeadContainer = new LeadContainer(processedLeads);
        deDuplicatedLeadContainer.sortLeadsByEntryDateAscending();

        String obj1 = objectMapper.writeValueAsString(deDuplicatedLeadContainer);
        String obj2 = String.valueOf(objectMapper.readTree(DeDuplicationTest.class.getResourceAsStream("/similarIdResult.json")));

        System.out.println(obj1);
        System.out.println(obj2);

        assertEquals(obj1, obj2);
    }
}
