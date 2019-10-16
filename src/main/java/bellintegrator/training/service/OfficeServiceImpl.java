package bellintegrator.training.service;

import bellintegrator.training.dao.OfficeDao;
import bellintegrator.training.dao.OrganizationDao;
import bellintegrator.training.exception.CustomNotFoundException;
import bellintegrator.training.model.Office;
import bellintegrator.training.model.Organization;
import bellintegrator.training.model.mapper.MapperFacade;
import bellintegrator.training.view.OfficesView;
import bellintegrator.training.view.OfficeView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OfficeServiceImpl implements OfficeService {

    private final OfficeDao officeDao;
    private final OrganizationDao organizationDao;
    private final MapperFacade mapperFacade;

    @Autowired
    public OfficeServiceImpl(final OfficeDao officeDao, final OrganizationDao organizationDao,
                             final MapperFacade mapperFacade) {
        this.officeDao = officeDao;
        this.organizationDao = organizationDao;
        this.mapperFacade = mapperFacade;
    }

    @Override
    @Transactional
    public void addOffice(final OfficesView view) {
        Organization organization = organizationDao.loadByIdOrganization(view.orgId);
        Office office =  mapperFacade.map(view,Office.class);
        organization.addOffice(office);
        officeDao.saveOffice(office);
    }

    @Override
    @Transactional
    public void updateOffice(final OfficeView view) {
        Office office = officeDao.loadByIdOffice(view.id);
        if (office == null) {
            throw new CustomNotFoundException("Not found office with id is " + view.id);
        }
        mapperFacade.map(view,office);
        officeDao.saveOffice(office);
    }

    @Override
    @Transactional(readOnly = true)
    public OfficeView getOffice(final Long id) {
        Office office = officeDao.loadByIdOffice(id);
        if (office == null) {
            throw new CustomNotFoundException("Not found office with id is " + id);
        }
        return mapperFacade.map(office,OfficeView.class);
    }

    @Override
    @Transactional
    public List<OfficesView> offices(final OfficesView view) {
        List<Office> offices = officeDao.loadOffices(view.orgId, view.name, view.phone, view.isActive);
        return mapperFacade.mapAsList(offices, OfficesView.class);
    }
}
