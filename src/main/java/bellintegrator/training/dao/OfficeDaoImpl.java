package bellintegrator.training.dao;

import bellintegrator.training.model.Office;
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

        Predicate predicate = criteriaBuilder.equal(root.get("orgId"),orgId);

        if (name != null ) {

            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal( root.get("name"), name));
        }
        if (phone != null) {

            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal( root.get("phone"), phone));
        }
        
        predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("isActive"),isActive));

        criteriaQuery.where(predicate);
        criteriaQuery.select(root);

        TypedQuery<Office> query = em.createQuery(criteriaQuery);
        return query.getResultList();
    }
}
