package it.gov.pagopa.bpd.file_storage.dao.controller;

import eu.sia.meda.core.controller.StatelessController;
import it.gov.pagopa.bpd.file_storage.dao.command.FileStorageService;
import it.gov.pagopa.bpd.file_storage.dao.model.FileStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.time.OffsetDateTime;

@RestController
public class BpdFileStorageControllerImpl extends StatelessController implements BpdFileStorageController {

    private final FileStorageService fileStorageService;

    @Autowired
    public BpdFileStorageControllerImpl(FileStorageService fileStorageService) {
        this.fileStorageService = fileStorageService;
    }


    @Override
    public ResponseEntity<InputStreamResource> getTermsAndConditions() {
        if (logger.isDebugEnabled()) {
            logger.debug("BpdFileStorageControllerImpl.getTermsAndConditions");
        }
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
