package bellintegrator.training.repository;

import bellintegrator.training.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * DAO для работы с Employee
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long>, JpaSpecificationExecutor<Employee> {

    /**
     * Получить Employee по идентификатору
     *
     * @param id
     * @return {@Employee}
     */
    Optional<Employee> findById(Long id);

    /**
     * Сохранить Employee
     *
     * @param employee
     * @return {@Employee}
     */
    Employee save(Employee employee);
}