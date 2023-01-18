package ru.aasmc.cems.stubs;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.aasmc.cems.dao.Detective;
import ru.aasmc.cems.repos.NotFoundException;
import ru.aasmc.cems.repos.stub.StubCriminalCaseRepo;
import ru.aasmc.cems.services.simpleimpl.SimpleCriminalCaseService;
import ru.aasmc.cems.util.Rank;

import static org.junit.Assert.*;
import static ru.aasmc.cems.repos.util.TestObjectsBuilder.buildDetective;

public class SimpleCriminalCaseServiceTest {
    static final Long CASE_ID = 1L;
    final Detective detective = buildDetective("Sherlock", "Holmes", Rank.INSPECTOR, "TS1234");

    StubCriminalCaseRepo repo = new StubCriminalCaseRepo();

    SimpleCriminalCaseService service = new SimpleCriminalCaseService(repo);

    @Before
    public void setUp(){
        repo.init();

        //create object to be tested
        service = new SimpleCriminalCaseService(repo);
    }

    //positive test, we know that a Case with ID=1 exists
    @Test
    public void findByIdPositive() {
        var criminalCase = service.findById(CASE_ID);
        assertNotNull(criminalCase);
    }

    //negative test, we know that a Case with ID=99 does not exist
    @Test(expected = NotFoundException.class)
    public void findByIdNegative() {
        var criminalCase = service.findById(99L);
        assertNull(criminalCase);
    }

    //negative test, we know that a Case with ID=99 does not exist
    @Test
    public void findByIdNegativeWithLambda() {
        assertThrows(NotFoundException.class, () -> service.findById(99L));
    }

    //positive test, we know that cases for this detective exist and how many
    @Test
    public void findByLeadPositive() {
        var result =  service.findByLeadInvestigator(detective);
        assertEquals(2, result.size());
    }

    //negative test, we know that cases for this detective do not exist
    @Test
    public void findByLeadNegative() {
        var detective = buildDetective("Jake", "Peralta", Rank.JUNIOR, "TS1122");
        var result =  service.findByLeadInvestigator(detective);
        assertNull(result);
    }

    //positive case, deleting existing case record
    @Test
    public void deleteByIdPositive() {
        service.deleteById(CASE_ID);

        try {
            var criminalCase = service.findById(CASE_ID);
            assertNull(criminalCase);
        } catch (NotFoundException nfe){
            assertTrue(nfe.getMessage().contains("Entity with id 1 could not be processed because it does not exist"));
        }
    }

    //negative case, attempt to delete non-existing case
    @Test(expected = NotFoundException.class)
    public void deleteByIdNegative() {
        service.deleteById(99L);
    }

    @After
    public void tearDown(){
        repo.clear();
    }
}
