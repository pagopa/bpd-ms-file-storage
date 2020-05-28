package it.gov.pagopa.bpd.file_storage.exception;

import it.gov.pagopa.bpd.common.exception.ResourceNotFoundException;
import it.gov.pagopa.bpd.file_storage.connector.jpa.model.FileStorage;

import java.time.OffsetDateTime;

public class FileStorageNotFoundException extends ResourceNotFoundException {

    public FileStorageNotFoundException(String type, OffsetDateTime date) {
        super(String.format("Unable to find %s with type %s in date %s", FileStorage.class.getSimpleName(), type, date));
    }

}
