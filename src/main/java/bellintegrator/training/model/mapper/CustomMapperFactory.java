package bellintegrator.training.model.mapper;

import bellintegrator.training.model.Country;
import bellintegrator.training.model.DocumentType;
import bellintegrator.training.view.DocView;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Service;

/**
 * Фабрика для создания MapperFactory.
 * 
 */
@Service
public class CustomMapperFactory implements FactoryBean<MapperFactory> {
    @Override
    public MapperFactory getObject() {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder()
                .constructorResolverStrategy(null)
                .build();

        mapperFactory.classMap(DocumentType.class, DocView.class)
                .field("name", "name")
                .field("docCode", "code")
                .register();

        mapperFactory.classMap(Country.class, DocView.class)
                .field("citizenshipName", "name")
                .field("citizenshipCode", "code")
                .register();

        return mapperFactory;
    }

    @Override
    public Class<?> getObjectType() {
        return MapperFactory.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
