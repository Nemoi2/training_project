package bellintegrator.training.dao;

import bellintegrator.training.model.Country;
import bellintegrator.training.model.DocumentType;
import bellintegrator.training.model.Employee;

import java.util.List;

/**
 * DAO для работы с Employee
 */
public interface EmployeeDao {

    /**
     * Получить Employee по идентификатору
     *
     * @param id
     * @return {@Employee}
     */
    Employee loadByIdEmployee(Long id);

    /**
     * Получить DocumentType по идентификатору
     *
     * @param code
     * @return {@DocumentType}
     */
    DocumentType loadByCodeDocumentType(Long code);

    /**
     * Получить Country по идентификатору
     *
     * @param code
     * @return {@Country}
     */
    Country loadByCodeCountry (Long code);

    /**
     * Сохранить Employee
     *
     * @param employee
     */
    void saveEmployee(Employee employee);

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