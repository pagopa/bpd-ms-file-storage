package it.gov.pagopa.bpd.file_storage.service;

import it.gov.pagopa.bpd.file_storage.FileStorageDAO;
import it.gov.pagopa.bpd.file_storage.model.FileStorage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

/**
 * See {@link FileStorageService}
 */
@Service
@Slf4j
class FileStorageServiceImpl implements FileStorageService {

    private final FileStorageDAO fileStorageDAO;

    @Autowired
    public FileStorageServiceImpl(FileStorageDAO fileStorageDAO) {
        this.fileStorageDAO = fileStorageDAO;
    }

    @Override
    public FileStorage getFile(OffsetDateTime todayDate, String type) {
        return fileStorageDAO.getFile(todayDate, type);
    }
}
