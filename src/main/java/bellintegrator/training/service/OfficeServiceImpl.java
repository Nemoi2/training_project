package bellintegrator.training.service;

import bellintegrator.training.repository.OfficeRepository;
import bellintegrator.training.repository.OrganizationRepository;
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
import java.util.Optional;

import static bellintegrator.training.repository.specification.BaseSpecification.*;
import static org.springframework.data.jpa.domain.Specification.where;

@Service
public class OfficeServiceImpl implements OfficeService {

    private final OfficeRepository officeRepository;
    private final OrganizationRepository organizationRepository;
    private final MapperFacade mapperFacade;

    @Autowired
    public OfficeServiceImpl(final OfficeRepository officeRepository, final OrganizationRepository organizationRepository,
                             final MapperFacade mapperFacade) {
        this.officeRepository = officeRepository;
        this.organizationRepository = organizationRepository;
        this.mapperFacade = mapperFacade;
    }

    @Override
    @Transactional
    public void addOffice(final OfficesView view) {
        Optional<Organization> organizationOptional = organizationRepository.findById(view.orgId);
        if (!organizationOptional.isPresent()) {
            throw new CustomNotFoundException(String.format("Not found organization with id: %d", view.orgId));
        }
        Office office =  mapperFacade.map(view,Office.class);
        organizationOptional.get().addOffice(office);
        officeRepository.save(office);
    }

    @Override
    @Transactional
    public void updateOffice(final OfficeView view) {
        Optional<Office> officeOptional = officeRepository.findById(view.id);
        if (officeOptional.isPresent()) {
            Office office = officeOptional.get();
            mapperFacade.map(view,office);
            officeRepository.save(office);
        }else {
            throw new CustomNotFoundException(String.format("Not found office with id is %d", view.id));
        }
    }

    @Override
    @Transactional(readOnly = true)
    public OfficeView getOffice(final Long id) {
        Optional<Office> officeOptional = officeRepository.findById(id);
        if (!officeOptional.isPresent()) {
            throw new CustomNotFoundException(String.format("Not found office with id is %d", id));
        }
        return mapperFacade.map( officeOptional.get(),OfficeView.class);
    }

    @Override
    @Transactional
    public List<OfficesView> offices(final OfficesView view) {
        List<Office> offices = officeRepository.findAll(where(hasOrgId(view.orgId))
                .and(officeName(view.name))
                .and(officePhone(view.phone))
                .and(officeActive(view.isActive)));
        return mapperFacade.mapAsList(offices, OfficesView.class);
    }
}
