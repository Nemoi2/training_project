package bellintegrator.training.dao;

import bellintegrator.training.model.Office;
import bellintegrator.training.model.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

/**
 * DAO для работы с Office
 */
public interface OfficeDao extends JpaRepository<Office,Long>, JpaSpecificationExecutor<Office> {

    /**
     * Получить Office по идентификатору
     *
     * @param id
     * @return {@Office}
     */
    Optional<Office> findById(Long id);

    /**
     * Сохранить Office
     *
     * @param office
     */
    Office save(Office office);

    /**
     * Получить Office по идентификатору
     *
     * @param orgId
     * @param name
     * @param phone
     * @param isActive
     * @return {@List<Office>}
     */
}

