package bellintegrator.training.service;

import bellintegrator.training.view.EmployeeListView;
import bellintegrator.training.view.EmployeeView;
import bellintegrator.training.view.EmployeesView;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;

/**
 * Сервис
 */
@Validated
public interface EmployeeService {

    /**
     * Добавить нового работника в БД
     *
     * @param employeesView
     */
    void addEmployee(@Valid EmployeesView employeesView);

    /**
     * Обновить данные работника в БД
     *
     * @param employeeView
     */
    void updateEmployee(@Valid EmployeeView employeeView);

    /**
     * Получить данные работника
     *
     * @param id
     * @return {@EmployeeView}
     */
    EmployeeView getEmployee(Long id);

    /**
     * Получить список работников
     *
     * @param employeesView
     * @return {@List<EmployeeListView>}
     */
    List<EmployeeListView> employees(@Valid EmployeeListView employeesView);
}