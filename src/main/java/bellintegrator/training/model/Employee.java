package bellintegrator.training.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Version;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.FetchType;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Сотрудник
 */
@Entity
@Table(name = "Employee")
public class Employee {

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
     * Фамилия сотрудника
     */
    @Column(name = "first_name", nullable = false)
    private String firstName;

    /**
     * Имя сотрудника
     */
    @Column(name = "second_name")
    private String secondName;

    /**
     * Отчество сотрудника
     */
    @Column(name = "middle_name")
    private String middleName;

    /**
     * Должность сотрудника
     */
    @Column(name = "post", nullable = false)
    private String position;

    /**
     * Телефон
     */
    @Column(name = "phone")
    private String phone;

    /**
     * Идентифицирован?
     */
    @Column(name = "is_identified")
    private Boolean isIdentified;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "office_id")
    private Office office;

    @OneToMany(mappedBy = "employee",cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Document> documentSet;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id")
    private Country country;

    public Employee() {
    }

    public Employee(final Long id, final Integer version, final String firstName, final String secondName,
                    final String middleName, final String position, final String phone, final Boolean isIdentified,
                    final Office office, final Set<Document> documentSet, final Country country) {
        this.id = id;
        this.version = version;
        this.firstName = firstName;
        this.secondName = secondName;
        this.middleName = middleName;
        this.position = position;
        this.phone = phone;
        this.isIdentified = isIdentified;
        this.office = office;
        this.documentSet = documentSet;
        this.country = country;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(final String secondName) {
        this.secondName = secondName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(final String middleName) {
        this.middleName = middleName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(final String position) {
        this.position = position;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(final String phone) {
        this.phone = phone;
    }

    public Boolean getIdentified() {
        return isIdentified;
    }

    public void setIdentified(final Boolean identified) {
        isIdentified = identified;
    }

    public Office getOffice() {
        return office;
    }

    public void setOffice(final Office office) {
        this.office = office;
    }

    public Set<Document> getDocumentSet() {
        if (documentSet == null) {
            documentSet = new HashSet<>();
        }
        return documentSet;
    }

    public void setDocumentSet(final Set<Document> documentSet) {
        this.documentSet = documentSet;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(final Country country) {
        this.country = country;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Employee employee = (Employee) obj;
        return id == employee.id;
    }
}
