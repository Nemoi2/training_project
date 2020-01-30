package bellintegrator.training.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
@Data
@EqualsAndHashCode(of = "id")
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


    public void addOffice(final Office office) {
        getOffices().add(office);
        office.setOrganization(this);
    }

    public void removeOffice(final Office office) {
        getOffices().remove(office);
    }

}
