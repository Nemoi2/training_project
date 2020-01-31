package bellintegrator.training.repository;

import bellintegrator.training.model.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository для работы с Organization
 */
@Repository
public interface OrganizationRepository extends JpaRepository<Organization,Long> , JpaSpecificationExecutor<Organization> {

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
}
