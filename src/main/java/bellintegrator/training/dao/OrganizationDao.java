package bellintegrator.training.dao;


import bellintegrator.training.model.Organization;

import java.util.Set;

/**
 * DAO для работы с Organization
 */
public interface OrganizationDao {
    /**
     * Получить все объекты Organization
     *
     * @return
     */
    Set<Organization> allOrganization();

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
}
