package bellintegrator.training.service;

import bellintegrator.training.view.OfficesView;
import bellintegrator.training.view.OfficeView;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;

/**
 * Сервис
 */
@Validated
public interface OfficeService {

    /**
     * Добавить новый офис в БД
     *
     * @param officesView
     */
    void addOffice(@Valid OfficesView officesView);

    /**
     * Обновить офис в БД
     *
     * @param officeView
     */
    void updateOffice(@Valid OfficeView officeView);

    /**
     * Получить офис
     *
     * @return {@OrganizationView}
     */
    OfficeView getOffice(Long id);

    /**
     * Получить список офисов
     *
     * @return {@OrganizationView}
     */
    List<OfficesView> offices(OfficesView officesView);
}

