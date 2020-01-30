package bellintegrator.training.dao.specification;


import bellintegrator.training.model.Document;
import bellintegrator.training.model.Employee;
import bellintegrator.training.model.Office;
import bellintegrator.training.model.Organization;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Objects;

public interface BaseSpecification<T> {

    static Specification<Organization> hasName(String name) {
        return (organization, cq, cb) -> cb.equal(organization.get("name"), name);
    }

    static Specification<Organization> hasInn(String inn) {
        if (Objects.nonNull(inn)) {
            return (organization, cq, cb) -> cb.equal(organization.get("inn"), inn);
        }
        return null;
    }
    static Specification<Organization> hasIsActive(Boolean isActive) {
        return (organization, cq, cb) -> cb.equal(organization.get("isActive"),  isActive);
    }

    static Specification<Office> hasOrgId(Long orgId) {
        return (office, cq, cb) -> cb.equal(office.get("organization"), orgId);
    }

    static Specification<Office> officeName(String name) {
        if (Objects.nonNull(name)) {
            return (office, cq, cb) -> cb.equal(office.get("name"), name);
        }
        return null;
    }

    static Specification<Office> officePhone(String phone) {
        if (Objects.nonNull(phone)) {
            return (office, cq, cb) -> cb.equal(office.get("phone"), phone);
        }
        return null;
    }
    static Specification<Office> officeActive(Boolean isActive) {
        return (Office, cq, cb) -> cb.equal(Office.get("isActive"),  isActive);
    }

    static Specification<Employee> hasOfficeId(Long officeId) {
        return (employee, cq, cb) -> cb.equal(employee.get("office"), officeId);
    }

    static Specification<Employee> employeeFirstName(String firstName) {
        if (Objects.nonNull(firstName)) {
            return (employee, cq, cb) -> cb.equal(employee.get("firstName"), firstName);
        }
        return null;
    }

    static Specification<Employee> employeeSecondName(String secondName) {
        if (Objects.nonNull(secondName)) {
            return (employee, cq, cb) -> cb.equal(employee.get("secondName"), secondName);
        }
        return null;
    }
    static Specification<Employee> employeeMiddleName(String middleName) {
        if (Objects.nonNull(middleName)) {
            return (employee, cq, cb) -> cb.equal(employee.get("middleName"), middleName);
        }
        return null;
    }
    static Specification<Employee> employeePosition(String position) {
        if (Objects.nonNull(position)) {
            return (employee, cq, cb) -> cb.equal(employee.get("position"), position);
        }
        return null;
    }
    static Specification<Employee> employeeDocCode(Long docCode) {
        if (Objects.nonNull(docCode)) {
            return (employee, cq, cb) -> {
                Root<Document> docRoot = cq.from(Document.class);
                Predicate joinPredicate = cb.equal(employee.get("document"), docRoot.get("employee"));
               return cb.and(joinPredicate, cb.equal( docRoot.get("documentType"), docCode));
            };
        }
        return null;
    }
    static Specification<Employee> employeeCitizenshipCode(Long citizenshipCode) {
        if (Objects.nonNull(citizenshipCode)) {
            return (employee, cq, cb) -> cb.equal(employee.get("country"), citizenshipCode);
        }
        return null;
    }
}