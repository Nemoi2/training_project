package bellintegrator.training.service;

import bellintegrator.training.dao.OrganizationDao;
import bellintegrator.training.model.Organization;
import bellintegrator.training.model.mapper.MapperFacade;
import bellintegrator.training.view.OrganizationView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


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
        mapperFacade.map(view,organization);
        organizationDao.saveOrganization(organization);
    }

    @Override
    @Transactional(readOnly = true)
    public OrganizationView getOrganization(final Long id) {
        Organization organization = organizationDao.loadByIdOrganization(id);
        return mapperFacade.map(organization,OrganizationView.class);
    }
}
