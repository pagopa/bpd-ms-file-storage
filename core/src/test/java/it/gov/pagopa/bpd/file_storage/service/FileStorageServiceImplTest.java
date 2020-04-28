package it.gov.pagopa.bpd.file_storage.service;

import it.gov.pagopa.bpd.file_storage.FileStorageDAO;
import it.gov.pagopa.bpd.file_storage.model.FileStorage;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.mockito.exceptions.verification.junit.ArgumentsAreDifferent;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.OffsetDateTime;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = FileStorageServiceImpl.class)
public class FileStorageServiceImplTest {

    private final OffsetDateTime DATE = OffsetDateTime.now();

    @MockBean
    private FileStorageDAO fileStorageDAOMock;
    @Autowired
    private FileStorageService fileStorageServiceMock;


    @Before
    public void initTest() {

        FileStorage fileStorage = new FileStorage();
        fileStorage.setFile("prova".getBytes());
        fileStorage.setType("prova");
        Mockito.when(fileStorageDAOMock.getFile(Mockito.eq(DATE), Mockito.eq(fileStorage.getType()))).thenAnswer((Answer<FileStorage>)
                invocation -> fileStorage);
    }

    @Test
    public void getFile() {
        FileStorage file = fileStorageServiceMock.getFile(DATE, "prova");

        Assert.assertNotNull(file);
        BDDMockito.verify(fileStorageDAOMock).getFile(DATE, "prova");
    }

    @Test(expected = ArgumentsAreDifferent.class)
    public void getFile_KO() {
        FileStorage file = fileStorageServiceMock.getFile(OffsetDateTime.parse("1970-01-01T00:00:00.000Z"), "prova");

        Assert.assertNull(file);
        BDDMockito.verify(fileStorageDAOMock).getFile(DATE, "prova");
    }
}
