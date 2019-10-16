package bellintegrator.training.service;

import bellintegrator.training.dao.HandbookDao;
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

    private final HandbookDao handbookDao;
    private final MapperFacade mapperFacade;

    @Autowired
    public HandbookServiceImpl(final HandbookDao handbookDao, final MapperFacade mapperFacade) {
        this.handbookDao = handbookDao;
        this.mapperFacade = mapperFacade;
    }

    @Override
    @Transactional
    public List<DocView> docs() {
        List<DocumentType> documentTypes = handbookDao.loadDocumentType();
        return mapperFacade.mapAsList(documentTypes, DocView.class);
    }

    @Override
    @Transactional
    public List<DocView> contries() {
        List<Country> countries = handbookDao.loadCountry();
        return mapperFacade.mapAsList(countries, DocView.class);
    }
}
