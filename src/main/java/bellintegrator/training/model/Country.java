package bellintegrator.training.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Version;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

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

    @OneToMany(mappedBy = "country",cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Employee> employees;

    public Country() {
    }

    public Country(final Long id, final Integer version, final String citizenshipName, final String citizenshipCode,
                   final Set<Employee> employees) {
        this.id = id;
        this.version = version;
        this.citizenshipName = citizenshipName;
        this.citizenshipCode = citizenshipCode;
        this.employees = employees;
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

    public Set<Employee> getEmployees() {
        if (employees == null) {
            employees = new HashSet<>();
        }
        return employees;
    }

    public void setEmployees(final Set<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Country country = (Country) obj;
        return id == country.id;
    }
}
