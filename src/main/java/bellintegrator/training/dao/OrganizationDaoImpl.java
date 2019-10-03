package bellintegrator.training.dao;

import bellintegrator.training.model.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Set;

@Repository
public class OrganizationDaoImpl implements OrganizationDao {

    private final EntityManager em;

    @Autowired
    public OrganizationDaoImpl(final EntityManager em) {
        this.em = em;
    }

    @Override
    public Set<Organization> allOrganization() {
        return null;
    }

    @Override
    public Organization loadByIdOrganization(final Long id) {
        return em.find(Organization.class, id);
    }

    @Override
    public void saveOrganization(final Organization organization) {
        em.persist(organization);
    }
}
