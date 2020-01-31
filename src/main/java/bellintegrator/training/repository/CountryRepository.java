package bellintegrator.training.repository;

import bellintegrator.training.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository для работы с Country
 */

@Repository
public interface CountryRepository extends JpaRepository<Country,Long> {

    /**
     * Получить Country по идентификатору
     *
     * @param citizenshipCode
     * @return {@Country}
     */
    Country findByCitizenshipCode (Long citizenshipCode);
}
