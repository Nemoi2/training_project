package bellintegrator.training.service;

import bellintegrator.training.dao.CountryDao;
import bellintegrator.training.dao.DocumentTypeDao;
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
import java.util.Optional;

import static bellintegrator.training.dao.specification.BaseSpecification.*;
import static org.springframework.data.jpa.domain.Specification.where;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final OfficeDao officeDao;
    private final EmployeeDao employeeDao;
    private final DocumentTypeDao documentTypeDao;
    private final CountryDao countryDao;
    private final MapperFacade mapperFacade;

    @Autowired
    public EmployeeServiceImpl(final OfficeDao officeDao, final EmployeeDao employeeDao,
                               final DocumentTypeDao documentTypeDao, final CountryDao countryDao,
                               final MapperFacade mapperFacade) {
        this.officeDao = officeDao;
        this.employeeDao = employeeDao;
        this.documentTypeDao = documentTypeDao;
        this.countryDao = countryDao;
        this.mapperFacade = mapperFacade;
    }

    @Override
    @Transactional
    public void addEmployee(final EmployeesView view) {
        Employee employee = mapperFacade.map(view, Employee.class);

        if (view.officeId != null) {
            Optional<Office> officeOptional = officeDao.findById(view.officeId);
            if (!officeOptional.isPresent()) {
                throw new CustomNotFoundException(String.format("Not found office with id is %d", view.officeId));
            }
            officeOptional.get().addEmployee(employee);
        }
        if (view.citizenshipCode != null) {
            Country country = countryDao.findByCitizenshipCode(view.citizenshipCode);
            if (country == null) {
                throw new CustomNotFoundException("Not found country with code is " + view.citizenshipCode);
            }
            employee.setCountry(country);
        }
        if (view.docCode != null) {
            DocumentType documentType = documentTypeDao.findByDocCode(view.docCode);
            if (documentType == null) {
                throw new CustomNotFoundException("Not found documentType with code is " + view.docCode);
            }
            Document document = mapperFacade.map(view,Document.class);
            document.setDocumentType(documentType);
            document.setEmployee(employee);
            employee.setDocument(document);
        }
        employeeDao.save(employee);
    }

    @Override
    @Transactional
    public void updateEmployee(final EmployeeView view) {
        Optional<Employee>  optionalEmployee = employeeDao.findById(view.id);
        if (!optionalEmployee.isPresent()) {
            throw new CustomNotFoundException(String.format("Not found organization with id is %d", view.id));
        }

        Employee employee = optionalEmployee.get();

        if (view.officeId != null) {
            Optional<Office> officeOptional = officeDao.findById(view.officeId);
            if (!officeOptional.isPresent()) {
                throw new CustomNotFoundException(String.format("Not found office with id is %d", view.officeId));
            }
            employee.setOffice(officeOptional.get());
        }
        if (view.citizenshipCode != null) {
            Country country = countryDao.findByCitizenshipCode(view.citizenshipCode);
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
        employeeDao.save(employee);
    }

    @Override
    @Transactional(readOnly = true)
    public EmployeeView getEmployee(final Long id) {
        Optional<Employee>  optionalEmployee = employeeDao.findById(id);
        if (!optionalEmployee.isPresent()) {
            throw new CustomNotFoundException(String.format("Not found organization with id is %d", id));
        }
        Employee employee = optionalEmployee.get();
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
        List<Employee> employees = employeeDao.findAll(where(hasOfficeId(view.officeId)
                .and(employeeFirstName(view.firstName))
                .and(employeeSecondName(view.secondName))
                .and(employeeMiddleName(view.middleName))
                .and(employeePosition(view.position))
                .and(employeeDocCode(view.docCode))
                .and(employeeCitizenshipCode(view.citizenshipCode))));
        return mapperFacade.mapAsList(employees, EmployeeListView.class);
    }
}