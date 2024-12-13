import com.AdobeAssignment.model.Lead;
import com.AdobeAssignment.model.LeadContainer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

public class mappingTest {
    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void mappingLeadContainerTest() throws IOException {
        objectMapper.findAndRegisterModules();
        InputStream stream = mappingTest.class.getResourceAsStream("/leads.json");
        LeadContainer leadContainer = objectMapper.readValue(stream, LeadContainer.class);
        assert(leadContainer != null);

        leadContainer.getLeads().forEach(System.out::println);

        String leadJson = objectMapper.writeValueAsString(leadContainer);
        System.out.println(leadJson);
    }

    @Test
    void mappingLeadTest() throws IOException {
        objectMapper.findAndRegisterModules();
        InputStream stream = mappingTest.class.getResourceAsStream("/singleLead.json");
        Lead lead = objectMapper.readValue(stream, Lead.class);
        assert(lead != null);

        String leadJson = objectMapper.writeValueAsString(lead);
        System.out.println(leadJson);
    }
}
