package bellintegrator.training.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Version;
import javax.persistence.OneToMany;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.GenerationType;
import javax.persistence.CascadeType;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Офис
 */
@Entity
@Table(name = "Office")
public class Office {

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
     * Иия офиса
     */
    @Column(name = "name")
    private String name;

    /**
     * Адрес офиса
     */
    @Column(name = "address")
    private String address;

    /**
     * Телефон
     */
    @Column(name = "phone")
    private String phone;

    /**
     * Активен ли офис?
     */
    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "org_id",nullable = false)
    private Long orgId;


    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "office_id")
    private Set<Employee> employeeSet;

    public Office() {
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

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(final Long orgId) {
        this.orgId = orgId;
    }

    public Set<Employee> getEmployeeSet() {
        if (employeeSet == null) {
            employeeSet = new HashSet<>();
        }
        return employeeSet;
    }

    public void setEmployeeSet(final Set<Employee> employeeSet) {
        this.employeeSet = employeeSet;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Office office = (Office) obj;
        return id == office.id;
    }
}
