package bellintegrator.training.dao;

import bellintegrator.training.model.Office;
import bellintegrator.training.model.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

@Repository
public class OfficeDaoImpl implements OfficeDao {

    private final EntityManager em;

    @Autowired
    public OfficeDaoImpl(final EntityManager em) {
        this.em = em;
    }

    @Override
    public Office loadByIdOffice(final Long id) {
        return em.find(Office.class, id);
    }

    @Override
    public void saveOffice(final Office office) {
        em.persist(office);
    }

    @Override
    public List<Office> loadOffices(final Long orgId, final String name,
                                             final String phone, final Boolean isActive) {

        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Office> criteriaQuery = criteriaBuilder.createQuery(Office.class);

        Root<Office> root = criteriaQuery.from(Office.class);

        if (name != null ) {
            if (phone != null) {
                criteriaQuery.select(root).where(root.get("orgId").in(orgId), root.get("name").in(name),
                        root.get("phone").in(phone), root.get("isActive").in(isActive));
            } else {
                criteriaQuery.select(root).where(root.get("orgId").in(orgId), root.get("name").in(name),
                        root.get("isActive").in(isActive));
            }
        }else{
            if (phone != null) {
            criteriaQuery.select(root).where(root.get("orgId").in(orgId), root.get("phone").in(phone),
                    root.get("isActive").in(isActive));
            }else {
            criteriaQuery.select(root).where(root.get("orgId").in(orgId), root.get("isActive").in(isActive));
            }
        }

        TypedQuery<Office> query = em.createQuery(criteriaQuery);
        return query.getResultList();
    }
}
