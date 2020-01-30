package bellintegrator.training.dao;

import bellintegrator.training.model.DocumentType;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * DAO для работы с DocumentType
 */
public interface DocumentTypeDao extends JpaRepository<DocumentType,Long> {

    /**
     * Получить DocumentType по идентификатору
     *
     * @param docCode
     * @return {@DocumentType}
     */
    DocumentType findByDocCode(Long docCode);
}
