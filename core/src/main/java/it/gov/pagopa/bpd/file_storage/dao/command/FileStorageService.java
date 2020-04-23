package it.gov.pagopa.bpd.file_storage.dao.command;


import it.gov.pagopa.bpd.file_storage.dao.model.FileStorage;

import java.time.OffsetDateTime;

public interface FileStorageService {

    FileStorage getFile(OffsetDateTime todayDate, String type);
}
