package bellintegrator.training.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Version;

/**
 * Страны
 */
@Data
@Entity
@Table(name = "Country")
public class Country {

    @Id
    @Column(name = "citizenship_code")
    private Long citizenshipCode;

    /**
     * Служебное поле hibernate
     */
    @Version
    @Column(name = "version")
    private Integer version;

    /**
     * Иия страны гражданина
     */
    @Column(name = "citizenship_name")
    private String citizenshipName;
}
