package bellintegrator.training.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Version;

/**
 * Тип документа
 */
@Entity
@Table(name = "Doc_Type")
public class DocumentType {

    @Id
    @Column(name = "doc_code")
    private Long docCode;

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
    private String name;

    public DocumentType() {
    }

    public DocumentType(final Long docCode, final String name) {
        this.docCode = docCode;
        this.name = name;
    }

    public Long getDocCode() {
        return docCode;
    }

    public void setDocCode(final Long docCode) {
        this.docCode = docCode;
    }

    public String getName() {
        return name;
    }

    public void setName(final String docName) {
        this.name = name;
    }
}
