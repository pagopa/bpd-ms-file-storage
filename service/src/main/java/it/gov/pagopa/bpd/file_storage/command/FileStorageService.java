package it.gov.pagopa.bpd.file_storage.command;


import it.gov.pagopa.bpd.file_storage.model.FileStorage;

import java.time.OffsetDateTime;

public interface FileStorageService {

    FileStorage getFile(OffsetDateTime todayDate, String type);
}
