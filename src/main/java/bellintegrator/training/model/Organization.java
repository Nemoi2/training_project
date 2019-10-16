package bellintegrator.training.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Version;
import javax.persistence.OneToMany;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.CascadeType;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Организация
 */
@Entity
@Table(name = "Organization")
public class Organization {

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
     * Иия организации
     */
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * Полное имя организации
     */
    @Column(name = "full_name", nullable = false)
    private String fullName;

    /**
     * Идентификационный номер налогоплательщика
     */
    @Column(name = "inn", nullable = false)
    private String inn;

    /**
     * Код причины постановки на учёт
     */
    @Column(name = "kpp", nullable = false)
    private String kpp;

    /**
     * Адрес
     */
    @Column(name = "address", nullable = false)
    private String address;

    /**
     * Телефон
     */
    @Column(name = "phone")
    private String phone;

    /**
     * Активна ли органиация?
     */
    @Column(name = "is_active")
    private Boolean isActive;

    @OneToMany(mappedBy = "organization",cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Office> offices;

    public Organization() {
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(final String fullName) {
        this.fullName = fullName;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(final String inn) {
        this.inn = inn;
    }

    public String getKpp() {
        return kpp;
    }

    public void setKpp(final String kpp) {
        this.kpp = kpp;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(final String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(final String phone) {
        this.phone = phone;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(final Boolean active) {
        isActive = active;
    }

    public Set<Office> getOffices() {
        if (offices == null) {
            offices = new HashSet<>();
        }
        return offices;
    }

    public void setOffices(final Set<Office> offices) {
        this.offices = offices;
        for(Office office: getOffices()) {
            office.setOrganization(this);
        }
    }

    public void addOffice(final Office office) {
        getOffices().add(office);
        office.setOrganization(this);
    }

    public void removeOffice(final Office office) {
        getOffices().remove(office);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Organization organization = (Organization) obj;
        return id == organization.id;
    }
}
