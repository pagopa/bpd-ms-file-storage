package it.gov.pagopa.bpd.file_storage.service;


import it.gov.pagopa.bpd.file_storage.connector.jpa.model.FileStorage;

import java.time.OffsetDateTime;

/**
 * A service to manage the Business Logic related to FileStorage
 */
public interface FileStorageService {

    FileStorage getFile(OffsetDateTime todayDate, String type);
}
