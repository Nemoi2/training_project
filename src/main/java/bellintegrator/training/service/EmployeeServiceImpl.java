package bellintegrator.training.service;

import bellintegrator.training.repository.CountryRepository;
import bellintegrator.training.repository.DocumentTypeRepository;
import bellintegrator.training.repository.EmployeeRepository;
import bellintegrator.training.repository.OfficeRepository;
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

import static bellintegrator.training.repository.specification.BaseSpecification.*;
import static org.springframework.data.jpa.domain.Specification.where;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final OfficeRepository officeRepository;
    private final EmployeeRepository employeeRepository;
    private final DocumentTypeRepository documentTypeRepository;
    private final CountryRepository countryRepository;
    private final MapperFacade mapperFacade;

    @Autowired
    public EmployeeServiceImpl(final OfficeRepository officeRepository, final EmployeeRepository employeeRepository,
                               final DocumentTypeRepository documentTypeRepository, final CountryRepository countryRepository,
                               final MapperFacade mapperFacade) {
        this.officeRepository = officeRepository;
        this.employeeRepository = employeeRepository;
        this.documentTypeRepository = documentTypeRepository;
        this.countryRepository = countryRepository;
        this.mapperFacade = mapperFacade;
    }

    @Override
    @Transactional
    public void addEmployee(final EmployeesView view) {
        Employee employee = mapperFacade.map(view, Employee.class);

        if (view.officeId != null) {
            Optional<Office> officeOptional = officeRepository.findById(view.officeId);
            if (!officeOptional.isPresent()) {
                throw new CustomNotFoundException(String.format("Not found office with id is %d", view.officeId));
            }
            officeOptional.get().addEmployee(employee);
        }
        if (view.citizenshipCode != null) {
            Country country = countryRepository.findByCitizenshipCode(view.citizenshipCode);
            if (country == null) {
                throw new CustomNotFoundException("Not found country with code is " + view.citizenshipCode);
            }
            employee.setCountry(country);
        }
        if (view.docCode != null) {
            DocumentType documentType = documentTypeRepository.findByDocCode(view.docCode);
            if (documentType == null) {
                throw new CustomNotFoundException("Not found documentType with code is " + view.docCode);
            }
            Document document = mapperFacade.map(view,Document.class);
            document.setDocumentType(documentType);
            document.setEmployee(employee);
            employee.setDocument(document);
        }
        employeeRepository.save(employee);
    }

    @Override
    @Transactional
    public void updateEmployee(final EmployeeView view) {
        Optional<Employee>  optionalEmployee = employeeRepository.findById(view.id);
        if (!optionalEmployee.isPresent()) {
            throw new CustomNotFoundException(String.format("Not found user with id is %d", view.id));
        }

        Employee employee = optionalEmployee.get();

        if (view.officeId != null) {
            Optional<Office> officeOptional = officeRepository.findById(view.officeId);
            if (!officeOptional.isPresent()) {
                throw new CustomNotFoundException(String.format("Not found office with id is %d", view.officeId));
            }
            employee.setOffice(officeOptional.get());
        }
        if (view.citizenshipCode != null) {
            Country country = countryRepository.findByCitizenshipCode(view.citizenshipCode);
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
        employeeRepository.save(employee);
    }

    @Override
    @Transactional(readOnly = true)
    public EmployeeView getEmployee(final Long id) {
        Optional<Employee>  optionalEmployee = employeeRepository.findById(id);
        if (!optionalEmployee.isPresent()) {
            throw new CustomNotFoundException(String.format("Not found user with id is %d", id));
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
        List<Employee> employees = employeeRepository.findAll(where(hasOfficeId(view.officeId)
                .and(employeeFirstName(view.firstName))
                .and(employeeSecondName(view.secondName))
                .and(employeeMiddleName(view.middleName))
                .and(employeePosition(view.position))
                .and(employeeDocCode(view.docCode))
                .and(employeeCitizenshipCode(view.citizenshipCode))));
        return mapperFacade.mapAsList(employees, EmployeeListView.class);
    }
}