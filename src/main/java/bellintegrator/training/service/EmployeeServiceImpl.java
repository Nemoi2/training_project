package bellintegrator.training.service;

import bellintegrator.training.dao.EmployeeDao;
import bellintegrator.training.dao.OfficeDao;
import bellintegrator.training.exception.CustomNotFoundException;
import bellintegrator.training.model.Country;
import bellintegrator.training.model.Document;
import bellintegrator.training.model.DocumentType;
import bellintegrator.training.model.Employee;
import bellintegrator.training.model.Office;
import bellintegrator.training.model.mapper.MapperFacade;
import bellintegrator.training.view.EmployeeListView;
import bellintegrator.training.view.EmployeeView;
import bellintegrator.training.view.EmployeesView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final OfficeDao officeDao;
    private final EmployeeDao employeeDao;
    private final MapperFacade mapperFacade;

    @Autowired
    public EmployeeServiceImpl(final OfficeDao officeDao, final EmployeeDao employeeDao,
                             final MapperFacade mapperFacade) {
        this.officeDao = officeDao;
        this.employeeDao = employeeDao;
        this.mapperFacade = mapperFacade;
    }

    @Override
    @Transactional
    public void addEmployee(final EmployeesView view) {
        Employee employee = mapperFacade.map(view, Employee.class);

        if (view.officeId != null) {
            Office office = officeDao.loadByIdOffice(view.officeId);
            if (office == null) {
                throw new CustomNotFoundException("Not found office with id is " + view.officeId);
            }
            office.addEmployee(employee);
        }
        if (view.citizenshipCode != null) {
            Country country = employeeDao.loadByCodeCountry(view.citizenshipCode);
            if (country == null) {
                throw new CustomNotFoundException("Not found country with code is " + view.citizenshipCode);
            }
            employee.setCountry(country);
        }
        if (view.docCode != null) {
            DocumentType documentType = employeeDao.loadByCodeDocumentType(view.docCode);
            if (documentType == null) {
                throw new CustomNotFoundException("Not found documentType with code is " + view.docCode);
            }
            Document document = mapperFacade.map(view,Document.class);
            document.setDocumentType(documentType);
            document.setEmployee(employee);
            employee.setDocument(document);
        }
        employeeDao.saveEmployee(employee);
    }

    @Override
    @Transactional
    public void updateEmployee(final EmployeeView view) {
        Employee employee = employeeDao.loadByIdEmployee(view.id);
        if (employee == null) {
            throw new CustomNotFoundException("Not found user with id is " + view.id);
        }

        if (view.officeId != null) {
            Office office = officeDao.loadByIdOffice(view.officeId);
            if (office == null) {
                throw new CustomNotFoundException("Not found office with id is " + view.officeId);
            }
            employee.setOffice(office);
        }
        if (view.citizenshipCode != null) {
            Country country = employeeDao.loadByCodeCountry(view.citizenshipCode);
            if (country == null) {
                throw new CustomNotFoundException("Not found country with id is " + view.citizenshipCode);
            }
            employee.setCountry(country);
        }
        mapperFacade.map(view,employee);

        if (employee.getDocument() != null) {
            mapperFacade.map(view, employee.getDocument());
        }else {
            Document document = mapperFacade.map(view, Document.class);
            document.setEmployee(employee);
            employee.setDocument(document);
        }
        employeeDao.saveEmployee(employee);
    }

    @Override
    @Transactional(readOnly = true)
    public EmployeeView getEmployee(final Long id) {
        Employee employee = employeeDao.loadByIdEmployee(id);

        if (employee == null) {
            throw new CustomNotFoundException("Not found user with id is " + id);
        }
        EmployeeView view = mapperFacade.map(employee,EmployeeView.class);

        if (employee.getDocument() != null) {
        mapperFacade.map(employee.getDocument(),view);
            if (employee.getDocument().getDocumentType() != null) {
                mapperFacade.map(employee.getDocument().getDocumentType(),view);
            }
        }
        if (employee.getCountry() != null) {
            mapperFacade.map(employee.getCountry(), view);
        }
        return view;
    }

    @Override
    @Transactional
    public List<EmployeeListView> employees(final EmployeeListView view) {
        List<Employee> employees = employeeDao.loadEmployees(view.officeId, view.firstName, view.secondName,
                view.middleName, view.position, view.docCode, view.citizenshipCode);
        return mapperFacade.mapAsList(employees, EmployeeListView.class);
    }
}