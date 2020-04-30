package it.gov.pagopa.bpd.file_storage.exception;

import it.gov.pagopa.bpd.common.exception.ResourceNotFoundException;

public class FileStorageNotFoundException extends ResourceNotFoundException {

    public FileStorageNotFoundException() {
        super("Exceeded max available payment instrument limit");
    }


}
