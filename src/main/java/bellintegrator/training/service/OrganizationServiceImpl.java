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
        organizationDao.saveOrganization(mapperFacade.map(view,Organization.class));
    }

    @Override
    @Transactional
    public void updateOrganization(final OrganizationView view) {
        Organization organization = organizationDao.loadByIdOrganization(view.id);
        if (organization == null) {
            throw new CustomNotFoundException("Not found organization with id is " + view.id);
        }
        mapperFacade.map(view,organization);
        organizationDao.saveOrganization(organization);
    }

    @Override
    @Transactional(readOnly = true)
    public OrganizationView getOrganization(final Long id) {
        Organization organization = organizationDao.loadByIdOrganization(id);
        if (organization == null) {
            throw new CustomNotFoundException("Not found organization with id is " + id);
        }
        return mapperFacade.map(organization,OrganizationView.class);
    }

    @Override
    @Transactional
    public List<OrganizationsView> organizations(OrganizationsView view) {
        List<Organization> organizations = organizationDao.loadOrganizations(view.name, view.inn, view.isActive);
        return mapperFacade.mapAsList(organizations, OrganizationsView.class);
    }
}
