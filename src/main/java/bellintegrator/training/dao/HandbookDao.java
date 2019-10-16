package bellintegrator.training.dao;

import bellintegrator.training.model.Country;
import bellintegrator.training.model.DocumentType;

import java.util.List;

/**
 * DAO для работы с Handbook
 */
public interface HandbookDao {

    /**
     * Получить список DocumentType
     *
     * @return {@List<DocumentType>}
     */
    List<DocumentType> loadDocumentType ();

    /**
     * Получить список Country
     *
     * @return {@List<Country>}
     */
    List<Country> loadCountry ();
}
