package bellintegrator.training.dao;

import bellintegrator.training.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryDao extends JpaRepository<Country,Long> {

    /**
     * Получить Country по идентификатору
     *
     * @param citizenshipCode
     * @return {@Country}
     */
    Country findByCitizenshipCode (Long citizenshipCode);
}
