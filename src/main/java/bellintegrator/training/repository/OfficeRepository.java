package bellintegrator.training.repository;

import bellintegrator.training.model.Office;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository для работы с Office
 */
@Repository
public interface OfficeRepository extends JpaRepository<Office,Long>, JpaSpecificationExecutor<Office> {

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
     * @return {@Office}
     */
    Office save(Office office);
}

