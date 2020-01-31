package bellintegrator.training.unit;

import bellintegrator.training.model.mapper.MapperFacade;
import bellintegrator.training.model.mapper.MapperFacadeImpl;
import bellintegrator.training.repository.CountryRepository;
import bellintegrator.training.repository.DocumentTypeRepository;
import bellintegrator.training.service.HandbookService;
import bellintegrator.training.service.HandbookServiceImpl;
import bellintegrator.training.view.DocView;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.mockito.Mockito.mock;

public class UnitTestHandbook {

    @Mock
    CountryRepository countryRepository = mock(CountryRepository.class);
    @Mock
    DocumentTypeRepository documentTypeRepository = mock(DocumentTypeRepository.class);

    MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
    MapperFacade mapperFacade = new MapperFacadeImpl(mapperFactory);

    HandbookService handbookService = new HandbookServiceImpl(documentTypeRepository,countryRepository,mapperFacade);

    @Test
    public void docsTest() throws Exception {

        DocView docView = new DocView();
        docView.name = "military ID";
        docView.code = 7L;

        handbookService.docs();
        Mockito.verify(documentTypeRepository,Mockito.times(1))
                .findAll();
    }

    @Test
    public void countriesTest() throws Exception {

        DocView docView = new DocView();
        docView.name = "Russian Federation";
        docView.code = 643L;

        handbookService.countries();
        Mockito.verify(countryRepository,Mockito.times(1))
                .findAll();
    }
}
