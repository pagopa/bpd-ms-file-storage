package it.gov.pagopa.bpd.file_storage.connector.jpa.model;


import it.gov.pagopa.bpd.common.connector.jpa.model.BaseEntity;
import it.gov.pagopa.bpd.file_storage.connector.jpa.FileStorageDAO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.time.OffsetDateTime;

/**
 * See {@link FileStorageDAO}
 */
@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"}, callSuper = false)
@Table(name = "bpd_file_storage")
public class FileStorage extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    @Basic(optional = false)
    private Long id;

    @Column(name = "start_date_t")
    private OffsetDateTime startDate;

    @Column(name = "end_date_t")
    private OffsetDateTime endDate;

    @Column(name = "type_t")
    private String type;

    @Column(name = "filename_t")
    private String fileName;

    @Lob
    @Type(type = "org.hibernate.type.BinaryType")
    @Column(name = "file_clob")
    private byte[] file;

}
