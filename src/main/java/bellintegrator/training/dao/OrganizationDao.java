package bellintegrator.training.dao;

import bellintegrator.training.model.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

/**
 * DAO для работы с Organization
 */
public interface OrganizationDao extends JpaRepository<Organization,Long> , JpaSpecificationExecutor<Organization> {

    /**
     * Получить Organization по идентификатору
     *
     * @param id
     * @return {@Organization}
     */
    Optional<Organization> findById (Long id);

    /**
     * Сохранить Organization
     *
     * @param organization
     * @return
     */
    Organization save(Organization organization);

    /**
     * Получить Organization по идентификатору
     *
     * @param name
     * @param inn
     * @param isActive
     * @return {@List<Organization>}
     */
}
