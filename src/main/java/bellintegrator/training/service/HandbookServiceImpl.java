package bellintegrator.training.service;

import bellintegrator.training.repository.CountryRepository;
import bellintegrator.training.repository.DocumentTypeRepository;
import bellintegrator.training.model.Country;
import bellintegrator.training.model.DocumentType;
import bellintegrator.training.model.mapper.MapperFacade;
import bellintegrator.training.view.DocView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class HandbookServiceImpl implements HandbookService {

    private final DocumentTypeRepository documentTypeRepository;
    private final CountryRepository countryRepository;
    private final MapperFacade mapperFacade;

    @Autowired
    public HandbookServiceImpl(final DocumentTypeRepository documentTypeRepository, final CountryRepository countryRepository,
                               final MapperFacade mapperFacade) {
        this.documentTypeRepository = documentTypeRepository;
        this.countryRepository = countryRepository;
        this.mapperFacade = mapperFacade;
    }

    @Override
    @Transactional
    public List<DocView> docs() {
        List<DocumentType> documentTypes = documentTypeRepository.findAll();
        return mapperFacade.mapAsList(documentTypes, DocView.class);
    }

    @Override
    @Transactional
    public List<DocView> countries() {
        List<Country> countries = countryRepository.findAll();
        return mapperFacade.mapAsList(countries, DocView.class);
    }
}
