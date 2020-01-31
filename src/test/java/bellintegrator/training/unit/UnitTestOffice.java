package bellintegrator.training.unit;

import bellintegrator.training.exception.CustomNotFoundException;
import bellintegrator.training.model.Office;
import bellintegrator.training.model.mapper.MapperFacade;
import bellintegrator.training.model.mapper.MapperFacadeImpl;
import bellintegrator.training.repository.OfficeRepository;
import bellintegrator.training.view.OfficesView;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;

import java.util.Optional;

import static org.mockito.Mockito.mock;

public class UnitTestOffice {

    @Mock
    OfficeRepository officeRepository = mock(OfficeRepository.class);

    MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
    MapperFacade mapperFacade = new MapperFacadeImpl(mapperFactory);

    @Test
    public void mapperOfficeTest()  {

        OfficesView officesView = new OfficesView();

        officesView.id = 1L;
        officesView.name = "Gaz_Penza";
        officesView.address = "st. Moscow 1";
        officesView.phone = "89603212222";
        officesView.isActive = true;

        Office office = mapperFacade.map(officesView,Office.class);

        Assert.assertEquals(officesView.id,office.getId());
        Assert.assertEquals(officesView.name,office.getName());
        Assert.assertEquals(officesView.address,office.getAddress());
        Assert.assertEquals(officesView.phone,office.getPhone());
        Assert.assertEquals(officesView.isActive,office.getIsActive());
    }

    @Test(expected = CustomNotFoundException.class)
    public void getOfficeFailTest() {
        Long id = 1L;

        Optional<Office> office = officeRepository.findById(id);
        if (!office.isPresent()) {
            throw new CustomNotFoundException(String.format("Not found office with id is %d", id));
        }
    }
}
