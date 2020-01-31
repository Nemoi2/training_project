package bellintegrator.training.repository;

import bellintegrator.training.model.DocumentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository для работы с DocumentType
 */
@Repository
public interface DocumentTypeRepository extends JpaRepository<DocumentType,Long> {

    /**
     * Получить DocumentType по идентификатору
     *
     * @param docCode
     * @return {@DocumentType}
     */
    DocumentType findByDocCode(Long docCode);
}
