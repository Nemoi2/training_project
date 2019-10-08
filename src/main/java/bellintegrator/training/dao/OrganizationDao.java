package bellintegrator.training.dao;


import bellintegrator.training.model.Organization;

import java.util.List;

/**
 * DAO для работы с Organization
 */
public interface OrganizationDao {

    /**
     * Получить Organization по идентификатору
     *
     * @param id
     * @return
     */
    Organization loadByIdOrganization(Long id);

    /**
     * Сохранить Organization
     *
     * @param organization
     */
    void saveOrganization(Organization organization);

    /**
     * Получить Organization по идентификатору
     *
     * @param name
     * @return
     */
    List<Organization> loadOrganizations(String name, String inn, Boolean isActive);
}
