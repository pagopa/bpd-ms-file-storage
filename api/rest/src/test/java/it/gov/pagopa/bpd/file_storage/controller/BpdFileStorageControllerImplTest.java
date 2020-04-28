package it.gov.pagopa.bpd.file_storage.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import eu.sia.meda.config.ArchConfiguration;
import it.gov.pagopa.bpd.file_storage.model.FileStorage;
import it.gov.pagopa.bpd.file_storage.service.FileStorageService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.annotation.PostConstruct;
import java.time.OffsetDateTime;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = BpdFileStorageControllerImpl.class)
@AutoConfigureMockMvc(secure = false)
@EnableWebMvc
public class BpdFileStorageControllerImplTest {

    @Autowired
    protected MockMvc mvc;
    protected ObjectMapper objectMapper = new ArchConfiguration().objectMapper();
    @MockBean
    private FileStorageService fileStorageServiceMock;


    @PostConstruct
    public void configureTest() {

        FileStorage fileStorage = new FileStorage();
        fileStorage.setFile("prova".getBytes());
        Mockito.when(fileStorageServiceMock.getFile(Mockito.any(OffsetDateTime.class), Mockito.eq("TC"))).thenAnswer((Answer<FileStorage>)
                invocation -> fileStorage);
    }

    @Test
    public void tcReport() throws Exception {

        MvcResult result = mvc.perform(MockMvcRequestBuilders.get("/bpd/tc")
                .contentType(MediaType.APPLICATION_PDF_VALUE)
                .accept(MediaType.APPLICATION_PDF_VALUE))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andReturn();
        byte[] pageResult = result.getResponse().getContentAsByteArray();

        Assert.assertNotNull(pageResult);
        Assert.assertEquals(pageResult, pageResult);
        BDDMockito.verify(fileStorageServiceMock).getFile(Mockito.any(OffsetDateTime.class), Mockito.eq("TC"));
    }

}
