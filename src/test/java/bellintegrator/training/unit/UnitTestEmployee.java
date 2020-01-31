package bellintegrator.training.unit;

/*
import bellintegrator.training.repository.EmployeeRepository;
import bellintegrator.training.repository.EmployeeDaoImpl;
import bellintegrator.training.exception.CustomNotFoundException;
import bellintegrator.training.model.Employee;
import bellintegrator.training.model.mapper.MapperFacade;
import bellintegrator.training.model.mapper.MapperFacadeImpl;
import bellintegrator.training.view.EmployeeView;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.mock;

public class UnitTestEmployee {

    @Mock
    EmployeeRepository employeeDao = mock(EmployeeDaoImpl.class);

    MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();

    MapperFacade mapperFacade = new MapperFacadeImpl(mapperFactory);

    @Test
    public void mapperEmployeeTest() throws Exception {

        EmployeeView employeeView = new EmployeeView();

        employeeView.id = 1L;
        employeeView.firstName = "Kozlov";
        employeeView.position = "admin";
        employeeView.phone = "89603212222";
        employeeView.isIdentified = true;

        Employee employee = mapperFacade.map(employeeView,Employee.class);

        Assert.assertEquals(employeeView.id,employee.getId());
        Assert.assertEquals(employeeView.firstName,employee.getFirstName());
        Assert.assertEquals(employeeView.position,employee.getPosition());
        Assert.assertEquals(employeeView.phone,employee.getPhone());
        Assert.assertEquals(employeeView.isIdentified,employee.getIsIdentified());
    }

    @Test(expected = CustomNotFoundException.class)
    public void getEmployeeFailTest() {
        Long id = 1L;

        Employee employee = employeeDao.loadByIdEmployee(id);
        if (employee == null) {
            throw new CustomNotFoundException("Not found employee with id is " + id);
        }
    }
}
*/
