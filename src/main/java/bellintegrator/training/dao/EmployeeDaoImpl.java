package bellintegrator.training.dao;

import bellintegrator.training.model.Country;
import bellintegrator.training.model.Document;
import bellintegrator.training.model.DocumentType;
import bellintegrator.training.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {

    private final EntityManager em;

    @Autowired
    public EmployeeDaoImpl(final EntityManager em) {
        this.em = em;
    }

    @Override
    public Employee loadByIdEmployee(final Long id) {
        return em.find(Employee.class, id);
    }

    @Override
    public DocumentType loadByCodeDocumentType(final Long code) {
        return em.find(DocumentType.class, code);
    }

    @Override
    public Country loadByCodeCountry(final Long code) {
        return em.find(Country.class, code);
    }

    @Override
    public void saveEmployee(final Employee employee) {
        em.persist(employee);
    }

    @Override
    public List<Employee> loadEmployees(final Long officeId, final String firstName, final String secondName,
                                        final String middleName, final String position , final Long docCode,
                                        final Long citizenshipCode) {

        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);

        Root<Employee> root = criteriaQuery.from(Employee.class);

        Predicate predicate = criteriaBuilder.equal(root.get("office"),officeId);

        if (firstName != null ) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal( root.get("firstName"), firstName));
        }
        if (secondName != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal( root.get("secondName"), secondName));
        }
        if (middleName != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal( root.get("middleName"), middleName));
        }
        if (position != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal( root.get("position"), position));
        }
        if (docCode != null) {
            Root<Document> docRoot = criteriaQuery.from(Document.class);

            Predicate joinPredicate = criteriaBuilder.equal(root.get("document"), docRoot.get("employee"));

            joinPredicate = criteriaBuilder.and(joinPredicate, criteriaBuilder.equal( docRoot.get("documentType"),
                    docCode));
            predicate = criteriaBuilder.and(predicate, joinPredicate);
        }
        if (citizenshipCode != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal( root.get("country"), citizenshipCode));
        }

        criteriaQuery.where(predicate);
        criteriaQuery.select(root);

        TypedQuery<Employee> query = em.createQuery(criteriaQuery);
        return query.getResultList();
    }
}
