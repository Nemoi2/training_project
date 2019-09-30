package bellintegrator.training.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Version;

/**
 * Страны
 */
@Entity
@Table(name = "Country")
public class Country {

    @Id
    @Column(name = "id")
    private Long id;

    /**
     * Служебное поле hibernate
     */
    @Version
    @Column(name = "version")
    private Integer version;

    /**
     * Иия страны гражданина
     */
    @Column(name = "citizenship_name", nullable = false)
    private String citizenshipName;

    /**
     * Код страны гражданина
     */
    @Column(name = "citizenship_code")
    private String citizenshipCode;

    public Country() {
    }

    public Country(final Long id, final Integer version, final String citizenshipName, final String citizenshipCode) {
        this.id = id;
        this.version = version;
        this.citizenshipName = citizenshipName;
        this.citizenshipCode = citizenshipCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCitizenshipName() {
        return citizenshipName;
    }

    public void setCitizenshipName(final String citizenshipName) {
        this.citizenshipName = citizenshipName;
    }

    public String getCitizenshipCode() {
        return citizenshipCode;
    }

    public void setCitizenshipCode(final String citizenshipCode) {
        this.citizenshipCode = citizenshipCode;
    }
}
