package bellintegrator.training.service;

import bellintegrator.training.dao.OrganizationDao;
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

import static bellintegrator.training.dao.specification.BaseSpecification.*;
import static org.springframework.data.jpa.domain.Specification.where;

@Service
public class OrganizationServiceImpl implements OrganizationService {

    private final OrganizationDao organizationDao;
    private final MapperFacade mapperFacade;

    @Autowired
    public OrganizationServiceImpl(final OrganizationDao organizationDao, final MapperFacade mapperFacade) {
        this.organizationDao = organizationDao;
        this.mapperFacade = mapperFacade;
    }

    @Override
    @Transactional
    public void addOrganization(final OrganizationView view) {
        organizationDao.save(mapperFacade.map(view,Organization.class));
    }

    @Override
    @Transactional
    public void updateOrganization(final OrganizationView view) {
        Optional<Organization> organizationOptional = organizationDao.findById(view.id);
        if (organizationOptional.isPresent()) {
            Organization organization = organizationOptional.get();
            mapperFacade.map(view, organization);
            organizationDao.save(organization);
        } else {
            throw new CustomNotFoundException(String.format("Not found organization with id: %d", view.id));
        }
    }

    @Override
    @Transactional(readOnly = true)
    public OrganizationView getOrganization(final Long id) {
        Optional<Organization> organizationOptional = organizationDao.findById(id);
        if (!organizationOptional.isPresent()) {
            throw new CustomNotFoundException(String.format("Not found organization with id: %d", id));
        }
        return mapperFacade.map(organizationOptional.get(),OrganizationView.class);
    }

    @Override
    @Transactional
    public List<OrganizationsView> organizations(final OrganizationsView view) {
        List<Organization> organizations = organizationDao.findAll(where(hasName(view.name))
                .and(hasInn(view.inn))
                .and(hasIsActive(view.isActive)));
        return mapperFacade.mapAsList(organizations, OrganizationsView.class);
    }
}
