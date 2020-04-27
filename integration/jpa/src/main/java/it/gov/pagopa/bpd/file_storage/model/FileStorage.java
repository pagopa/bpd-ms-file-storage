package it.gov.pagopa.bpd.file_storage.model;


import it.gov.pagopa.bpd.common.model.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.time.OffsetDateTime;

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

    @Column(name = "fileName_t")
    private String fileName;

    @Lob
    @Type(type = "org.hibernate.type.BinaryType")
    @Column(name = "file_clob")
    private byte[] file;

}
