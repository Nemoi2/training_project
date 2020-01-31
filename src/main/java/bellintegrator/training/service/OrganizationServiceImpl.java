package bellintegrator.training.service;

import bellintegrator.training.repository.OrganizationRepository;
import bellintegrator.training.exception.CustomNotFoundException;
import bellintegrator.training.model.Organization;
import bellintegrator.training.model.mapper.MapperFacade;
import bellintegrator.training.view.OrganizationsView;
import bellintegrator.training.view.OrganizationView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static bellintegrator.training.repository.specification.BaseSpecification.*;
import static org.springframework.data.jpa.domain.Specification.where;

@Service
public class OrganizationServiceImpl implements OrganizationService {

    private final OrganizationRepository organizationRepository;
    private final MapperFacade mapperFacade;

    @Autowired
    public OrganizationServiceImpl(final OrganizationRepository organizationRepository, final MapperFacade mapperFacade) {
        this.organizationRepository = organizationRepository;
        this.mapperFacade = mapperFacade;
    }

    @Override
    @Transactional
    public void addOrganization(final OrganizationView view) {
        organizationRepository.save(mapperFacade.map(view,Organization.class));
    }

    @Override
    @Transactional
    public void updateOrganization(final OrganizationView view) {
        Optional<Organization> organizationOptional = organizationRepository.findById(view.id);
        if (organizationOptional.isPresent()) {
            Organization organization = organizationOptional.get();
            mapperFacade.map(view, organization);
            organizationRepository.save(organization);
        } else {
            throw new CustomNotFoundException(String.format("Not found organization with id: %d", view.id));
        }
    }

    @Override
    @Transactional(readOnly = true)
    public OrganizationView getOrganization(final Long id) {
        Optional<Organization> organizationOptional = organizationRepository.findById(id);
        if (!organizationOptional.isPresent()) {
            throw new CustomNotFoundException(String.format("Not found organization with id: %d", id));
        }
        return mapperFacade.map(organizationOptional.get(),OrganizationView.class);
    }

    @Override
    @Transactional
    public List<OrganizationsView> organizations(final OrganizationsView view) {
        List<Organization> organizations = organizationRepository.findAll(where(hasName(view.name))
                .and(hasInn(view.inn))
                .and(hasIsActive(view.isActive)));
        return mapperFacade.mapAsList(organizations, OrganizationsView.class);
    }
}
