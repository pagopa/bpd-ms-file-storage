package it.gov.pagopa.bpd.file_storage.controller;

import eu.sia.meda.core.controller.StatelessController;
import it.gov.pagopa.bpd.file_storage.command.FileStorageService;
import it.gov.pagopa.bpd.file_storage.model.FileStorage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.time.OffsetDateTime;

@RestController
@Slf4j
public class BpdFileStorageControllerImpl extends StatelessController implements BpdFileStorageController {

    private final FileStorageService fileStorageService;

    @Autowired
    public BpdFileStorageControllerImpl(FileStorageService fileStorageService) {
        this.fileStorageService = fileStorageService;
    }


    @Override
    public ResponseEntity<InputStreamResource> getTermsAndConditions() throws FileNotFoundException {
        logger.debug("Start get T&C Report");
        FileStorage file = fileStorageService.getFile(OffsetDateTime.now(), "TC");
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=" + file.getFileName());

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(new ByteArrayInputStream(file.getFile())));
    }
}
