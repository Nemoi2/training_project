package bellintegrator.training.unit;

import bellintegrator.training.exception.CustomNotFoundException;
import bellintegrator.training.model.Organization;
import bellintegrator.training.model.mapper.MapperFacade;
import bellintegrator.training.model.mapper.MapperFacadeImpl;
import bellintegrator.training.repository.OrganizationRepository;
import bellintegrator.training.service.OrganizationService;
import bellintegrator.training.service.OrganizationServiceImpl;
import bellintegrator.training.view.OrganizationView;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Optional;

import static org.mockito.Mockito.mock;

public class UnitTestOrganization {

    @Mock
    OrganizationRepository organizationRepository = mock(OrganizationRepository.class);

    MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();

    MapperFacade mapperFacade = new MapperFacadeImpl(mapperFactory);

    OrganizationService organizationService = new OrganizationServiceImpl(organizationRepository,mapperFacade);

    @Test
    public void addOrganizationTest() {

        OrganizationView organizationView = new OrganizationView();

        organizationView.name = "Gazprom";
        organizationView.fullName = "OOO Gazprom";
        organizationView.inn = "123456789";
        organizationView.kpp = "987654321";
        organizationView.address = "city Peter";
        organizationView.phone = "89603212222";
        organizationView.isActive = true;

        organizationService.addOrganization(organizationView);

        Mockito.verify(organizationRepository,Mockito.times(1))
                .save(mapperFacade.map(organizationView, Organization.class));
    }

    @Test
    public void mapperOrganizationTest() throws Exception {

        OrganizationView organizationView = new OrganizationView();

        organizationView.id = 1L;
        organizationView.name = "Gazprom";
        organizationView.fullName = "OOO Gazprom";
        organizationView.inn = "123456789";
        organizationView.kpp = "987654321";
        organizationView.address = "city Peter";
        organizationView.phone = "89603212222";
        organizationView.isActive = true;

        Organization organization = mapperFacade.map(organizationView,Organization.class);

        Assert.assertEquals(organizationView.id,organization.getId());
        Assert.assertEquals(organizationView.name,organization.getName());
        Assert.assertEquals(organizationView.fullName,organization.getFullName());
        Assert.assertEquals(organizationView.inn,organization.getInn());
        Assert.assertEquals(organizationView.kpp,organization.getKpp());
        Assert.assertEquals(organizationView.address,organization.getAddress());
        Assert.assertEquals(organizationView.phone,organization.getPhone());
        Assert.assertEquals(organizationView.isActive,organization.getIsActive());
    }

    @Test(expected = CustomNotFoundException.class)
    public void getOrganizationFailTest() {
        Long id = 1L;

        Optional<Organization> organization = organizationRepository.findById(id);
        if (!organization.isPresent()) {
            throw new CustomNotFoundException(String.format("Not found organization with id is %d",id));
        }
    }
}
