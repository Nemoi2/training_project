package bellintegrator.training.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Version;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.FetchType;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.sql.Date;

/**
 * Документ
 */
@Entity
@Table(name = "Document")
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * Служебное поле hibernate
     */
    @Version
    @Column(name = "version")
    private Integer version;

    /**
     * Уникальный номер документа
     */
    @Column(name = "number", nullable = false)
    private String number;

    /**
     * Дата документа
     */
    @Column(name = "doc_date")
    @Temporal(TemporalType.DATE)
    private Date docDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doc_type_id")
    private DocumentType documentType;

    public Document() {
    }

    public Document(final Long id, final Integer version, final String number, final Date docDate,
                    final Employee employee, final DocumentType documentType) {
        this.id = id;
        this.version = version;
        this.number = number;
        this.docDate = docDate;
        this.employee = employee;
        this.documentType = documentType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(final String number) {
        this.number = number;
    }

    public Date getDocDate() {
        return docDate;
    }

    public void setDocDate(final Date docDate) {
        this.docDate = docDate;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(final Employee employee) {
        this.employee = employee;
    }

    public DocumentType getDocumentType() {
        return documentType;
    }

    public void setDocumentType(final DocumentType documentType) {
        this.documentType = documentType;
    }
}
