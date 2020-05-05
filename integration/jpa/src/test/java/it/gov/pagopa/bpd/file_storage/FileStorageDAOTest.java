package it.gov.pagopa.bpd.file_storage;

import eu.sia.meda.layers.connector.query.CriteriaQuery;
import it.gov.pagopa.bpd.common.BaseCrudJpaDAOTest;
import it.gov.pagopa.bpd.file_storage.model.FileStorage;
import lombok.Data;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.OffsetDateTime;
import java.util.function.Function;

public class FileStorageDAOTest extends BaseCrudJpaDAOTest<FileStorageDAO, FileStorage, Long> {

    @Autowired
    private FileStorageDAO fileStorageDAOMock;

    @Override
    protected CriteriaQuery<? super FileStorage> getMatchAlreadySavedCriteria() {
        FileStorageDAOTest.FileStorageCriteria criteriaQuery = new FileStorageDAOTest.FileStorageCriteria();
        criteriaQuery.setId(getStoredId());

        return criteriaQuery;
    }

    @Override
    protected FileStorageDAO getDao() {
        return fileStorageDAOMock;
    }

    @Override
    protected void setId(FileStorage entity, Long id) {
        entity.setId(id);
    }

    @Override
    protected Long getId(FileStorage entity) {
        return entity.getId();
    }

    @Override
    protected void alterEntityToUpdate(FileStorage entity) {
        entity.setStartDate(OffsetDateTime.now());
    }

    @Override
    protected Function<Integer, Long> idBuilderFn() {
        return (bias) -> null;
    }

    @Test
    public void getFile_ok() {
        OffsetDateTime startDate = getStoredEntity().getStartDate();
        String type = getStoredEntity().getType();
        getStoredEntity().setEndDate(null);
        final FileStorage entity = fileStorageDAOMock.getFile(startDate, type);

        Assert.assertNotNull(entity);
        Assert.assertEquals(getStoredEntity(), entity);
    }

    @Test
    public void getFile_ko() {
        OffsetDateTime endDate = getStoredEntity().getEndDate();
        String type = getStoredEntity().getType();
        final FileStorage entity = fileStorageDAOMock.getFile(endDate, type);

        Assert.assertNull(entity);
        Assert.assertNotEquals(getStoredEntity(), entity);
    }

    @Data
    private static class FileStorageCriteria implements CriteriaQuery<FileStorage> {
        private Long id;
    }

}