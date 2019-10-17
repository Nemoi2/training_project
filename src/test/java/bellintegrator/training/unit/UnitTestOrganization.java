package bellintegrator.training.unit;

import bellintegrator.training.dao.OrganizationDao;
import bellintegrator.training.dao.OrganizationDaoImpl;
import bellintegrator.training.exception.CustomNotFoundException;
import bellintegrator.training.model.Organization;
import bellintegrator.training.model.mapper.MapperFacade;
import bellintegrator.training.model.mapper.MapperFacadeImpl;
import ma.glasnost.orika.MapperFactory;
import bellintegrator.training.service.OrganizationService;
import bellintegrator.training.service.OrganizationServiceImpl;
import bellintegrator.training.view.OrganizationView;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.mockito.Mockito.mock;

public class UnitTestOrganization {

    @Mock
    OrganizationDao organizationDao = mock(OrganizationDaoImpl.class);

    MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();

    MapperFacade mapperFacade = new MapperFacadeImpl(mapperFactory);

    OrganizationService organizationService = new OrganizationServiceImpl(organizationDao,mapperFacade);

    @Test
    public void addOrganizationTest() throws Exception {

        OrganizationView organizationView = new OrganizationView();

        organizationView.name = "Gazprom";
        organizationView.fullName = "OOO Gazprom";
        organizationView.inn = "123456789";
        organizationView.kpp = "987654321";
        organizationView.address = "city Peter";
        organizationView.phone = "89603212222";
        organizationView.isActive = true;

        organizationService.addOrganization(organizationView);

        Mockito.verify(organizationDao,Mockito.times(1))
                .saveOrganization(mapperFacade.map(organizationView,Organization.class));
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

        Organization organization = organizationDao.loadByIdOrganization(id);
        if (organization == null) {
            throw new CustomNotFoundException("Not found organization with id is " + id);
        }
    }
}
