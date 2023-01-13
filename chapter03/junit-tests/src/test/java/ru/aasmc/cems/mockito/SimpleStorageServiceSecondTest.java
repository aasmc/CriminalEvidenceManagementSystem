package ru.aasmc.cems.mockito;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.aasmc.cems.dao.Storage;
import ru.aasmc.cems.repos.StorageRepo;
import ru.aasmc.cems.services.simpleimpl.SimpleStorageService;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

/**
 * How to use mockito with annotations.
 */
public class SimpleStorageServiceSecondTest {

    static final Long STORAGE_ID = 1L;

    @Mock
    private StorageRepo mockRepo;

    @InjectMocks
    private SimpleStorageService storageService;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findByIdPositive() {
        var storage = new Storage();
        storage.setId(STORAGE_ID);

        when(mockRepo.findById(any(Long.class))).thenReturn(Optional.of(storage));

        var result = storageService.findById(STORAGE_ID);
        assertNotNull(result);
        assertEquals(storage.getId(), result.getId());
    }
}
