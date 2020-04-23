package it.gov.pagopa.bpd.file_storage.dao.controller;

import io.swagger.annotations.Api;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.FileNotFoundException;


@Api(tags = "Bonus Pagamenti Digitali File Storage Controller")
@RequestMapping("/bpd")
public interface BpdFileStorageController {

    @RequestMapping(value = "/tc", method = RequestMethod.GET, produces = MediaType.APPLICATION_PDF_VALUE)
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<InputStreamResource> getTermsAndConditions() throws FileNotFoundException;

}
