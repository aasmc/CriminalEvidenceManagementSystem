package ru.aasmc.cems.jmock;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Before;
import org.junit.Test;
import ru.aasmc.cems.dao.Evidence;
import ru.aasmc.cems.repos.EvidenceRepo;
import ru.aasmc.cems.services.simpleimpl.SimpleEvidenceService;
import ru.aasmc.cems.util.CaseStatus;
import ru.aasmc.cems.util.CaseType;
import ru.aasmc.cems.util.Rank;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static ru.aasmc.cems.repos.util.TestObjectsBuilder.buildCase;
import static ru.aasmc.cems.repos.util.TestObjectsBuilder.buildDetective;

public class SimpleEvidenceServiceTest {
    static final Long EVIDENCE_ID = 1L;

    private EvidenceRepo mockRepo;

    private Mockery mockery = new JUnit4Mockery();

    private SimpleEvidenceService service;

    @Before
    public void setup() {
        mockRepo = mockery.mock(EvidenceRepo.class);

        service = new SimpleEvidenceService();
        service.setRepo(mockRepo);
    }

    @Test
    public void testCreateEvidence() {
        var detective = buildDetective("Sherlock", "Holmes", Rank.INSPECTOR, "TS1234");
        var criminalCase = buildCase(detective, CaseType.FELONY, CaseStatus.UNDER_INVESTIGATION);

        var evidence = new Evidence();
        evidence.setNumber("123445464");
        evidence.setItemName("Red Bloody Knife");
        evidence.setId(EVIDENCE_ID);
        evidence.setCriminalCase(criminalCase);

        mockery.checking(new Expectations() {
            {
                allowing(mockRepo).findById(EVIDENCE_ID);
                will(returnValue(Optional.of(evidence)));
            }
        });

        var result = service.findById(EVIDENCE_ID);
        mockery.assertIsSatisfied();
        assertNotNull(result);
        assertEquals(result.getId(), evidence.getId());
        assertEquals(result.getNumber(), evidence.getNumber());
    }
}




















