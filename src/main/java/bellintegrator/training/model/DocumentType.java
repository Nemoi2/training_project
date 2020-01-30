package bellintegrator.training.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Version;

/**
 * Тип документа
 */
@Data
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
}
