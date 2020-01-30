package bellintegrator.training.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Version;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.MapsId;
import javax.persistence.JoinColumn;
import javax.persistence.FetchType;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/**
 * Документ
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Document")
public class Document {

    @Id
    @Column(name = "employee_id")
    private Long id;

    /**
     * Служебное поле hibernate
     */
    @Version
    @Column(name = "version")
    private Integer version;

    /**
     * Иия документа
     */
    @Column(name = "name")
    private String docName;

    /**
     * Уникальный номер документа
     */
    @Column(name = "number")
    private String docNumber;

    /**
     * Дата документа
     */
    @Column(name = "doc_date")
    @Temporal(TemporalType.DATE)
    private Date docDate;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "doc_type_id")
    private DocumentType documentType;
}
