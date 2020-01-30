package bellintegrator.training.dao.specification;

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
}