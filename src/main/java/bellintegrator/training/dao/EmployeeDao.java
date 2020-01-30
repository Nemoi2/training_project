package bellintegrator.training.dao;

import bellintegrator.training.model.Country;
import bellintegrator.training.model.DocumentType;
import bellintegrator.training.model.Employee;
import bellintegrator.training.model.Office;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

/**
 * DAO для работы с Employee
 */
public interface EmployeeDao extends JpaRepository<Employee,Long>, JpaSpecificationExecutor<Employee> {

    /**
     * Получить Employee по идентификатору
     *
     * @param id
     * @return {@Employee}
     */
    Optional<Employee> findById(Long id);

/*    *//**
     * Получить DocumentType по идентификатору
     *
     * @param docCode
     * @return {@DocumentType}
     *//*
    DocumentType findByDocCode(Long docCode);

    *//**
     * Получить Country по идентификатору
     *
     * @param code
     * @return {@Country}
     *//*
    Country loadByCodeCountry (Long code);*/

    /**
     * Сохранить Employee
     *
     * @param employee
     */
    Employee save(Employee employee);

    /**
     * Получить список Employee
     *
     * @param officeId
     * @param firstName
     * @param secondName
     * @param middleName
     * @param position
     * @param docCode
     * @param citizenshipCode
     * @return {@List<Employee>}
     */
    List<Employee> loadEmployees(Long officeId, String firstName, String secondName, String middleName,
                                 String position, Long docCode, Long citizenshipCode);
}