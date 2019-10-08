package bellintegrator.training.dao;


import bellintegrator.training.model.Office;
import bellintegrator.training.model.Organization;

import java.util.List;

/**
 * DAO для работы с Office
 */
public interface OfficeDao {

    /**
     * Получить Office по идентификатору
     *
     * @param id
     * @return
     */
    Office loadByIdOffice(Long id);

    /**
     * Сохранить Office
     *
     * @param office
     */
    void saveOffice(Office office);

    /**
     * Получить Office по идентификатору
     *
     * @param name
     * @return
     */
    List<Office> loadOffices(Long orgId , String name, String phone, Boolean isActive);
}

