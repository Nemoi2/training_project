package bellintegrator.training.dao.specification;

import bellintegrator.training.model.Office;
import bellintegrator.training.model.Organization;
import org.springframework.data.jpa.domain.Specification;

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
}