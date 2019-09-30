package bellintegrator.training.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Version;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

/**
 * Тип документа
 */
@Entity
@Table(name = "Doc_Type")
public class DocumentType {

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
     * Иия документа
     */
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * Код документа
     */
    @Column(name = "doc_code")
    private String docCode;

    public DocumentType() {
    }

    public DocumentType(final Long id, final Integer version, final String name, final String docCode) {
        this.id = id;
        this.version = version;
        this.name = name;
        this.docCode = docCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getDocCode() {
        return docCode;
    }

    public void setDocCode(final String docCode) {
        this.docCode = docCode;
    }
}
