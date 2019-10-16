package bellintegrator.training.dao;

import bellintegrator.training.model.Country;
import bellintegrator.training.model.DocumentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class HandbookDaoImpl implements HandbookDao {

    private final EntityManager em;

    @Autowired
    public HandbookDaoImpl(final EntityManager em) {
        this.em = em;
    }

    @Override
    public List<DocumentType> loadDocumentType() {
        String queryString = String.format("SELECT h FROM %s h", DocumentType.class.getSimpleName());
        TypedQuery<DocumentType> query = em.createQuery(queryString, DocumentType.class);
        return query.getResultList();
    }

    @Override
    public List<Country> loadCountry() {
        String queryString = String.format("SELECT h FROM %s h", Country.class.getSimpleName());
        TypedQuery<Country> query = em.createQuery(queryString, Country.class);
        return query.getResultList();
    }
}
