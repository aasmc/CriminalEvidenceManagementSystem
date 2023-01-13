package ru.aasmc.cems.easymock;

import org.easymock.EasyMockRule;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.Rule;
import org.junit.Test;
import ru.aasmc.cems.repos.DetectiveRepo;
import ru.aasmc.cems.services.simpleimpl.SimpleDetectiveService;
import ru.aasmc.cems.util.Rank;

import java.util.Optional;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static ru.aasmc.cems.repos.util.TestObjectsBuilder.buildDetective;

public class SimpleDetectiveServiceTest {
    public static final Long DETECTIVE_ID = 1L;

    @Rule
    public EasyMockRule rule = new EasyMockRule(this);

    @Mock
    private DetectiveRepo mockRepo;

    @TestSubject
    private SimpleDetectiveService service = new SimpleDetectiveService(mockRepo);

    @Test
    public void findByIdPositive() {
        var simpleDetective = buildDetective("Sherlock", "Holmes", Rank.INSPECTOR, "TS1234");
        simpleDetective.setId(DETECTIVE_ID);
        expect(mockRepo.findById(DETECTIVE_ID)).andReturn(Optional.of(simpleDetective));
        replay(mockRepo);

        var detective = service.findById(DETECTIVE_ID);
        verify(mockRepo);
        assertNotNull(detective);
        assertEquals(detective.getId(), simpleDetective.getId());
    }

}
