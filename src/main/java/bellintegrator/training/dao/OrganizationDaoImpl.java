package bellintegrator.training.dao;

import bellintegrator.training.model.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class OrganizationDaoImpl implements OrganizationDao {

    private final EntityManager em;

    @Autowired
    public OrganizationDaoImpl(final EntityManager em) {
        this.em = em;
    }

    @Override
    public Organization loadByIdOrganization(final Long id) {
        return em.find(Organization.class, id);
    }

    @Override
    public void saveOrganization(final Organization organization) {
        em.persist(organization);
    }

    @Override
    public List<Organization> loadOrganizations(final String name, final String inn, final Boolean isActive) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Organization> criteriaQuery = criteriaBuilder.createQuery(Organization.class);

        Root<Organization> root = criteriaQuery.from(Organization.class);

        if (inn != null ) {
            criteriaQuery.select(root).where(root.get("name").in(name), root.get("inn").in(inn),
                    root.get("isActive").in(isActive));
        }else {
            criteriaQuery.select(root).where(root.get("name").in(name), root.get("isActive").in(isActive));
        }

        TypedQuery<Organization> query = em.createQuery(criteriaQuery);
        return query.getResultList();
    }
}
