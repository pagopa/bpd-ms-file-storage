package it.gov.pagopa.bpd.file_storage.exception;

import it.gov.pagopa.bpd.common.exception.ResourceNotFoundException;
import it.gov.pagopa.bpd.file_storage.model.FileStorage;

public class FileStorageNotFoundException extends ResourceNotFoundException {

    public FileStorageNotFoundException(String type) {
        super(FileStorage.class, type);
    }

}

