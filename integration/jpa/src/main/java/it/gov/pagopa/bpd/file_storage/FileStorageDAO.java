package it.gov.pagopa.bpd.file_storage;


import eu.sia.meda.connector.jpa.CrudJpaDAO;
import it.gov.pagopa.bpd.file_storage.model.FileStorage;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;

@Repository
public interface FileStorageDAO extends CrudJpaDAO<FileStorage, Long> {
    @Query(value = "select fs " +
            "from FileStorage fs " +
            "where :todayDate between " +
            "fs.startDate and fs.endDate " +
            "and fs.type = :type"
    )
    FileStorage getFile(@Param("todayDate") OffsetDateTime todayDate, String type);
}

