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
     * @param id
     * @return {@OfficeView}
     */
    OfficeView getOffice(Long id);

    /**
     * Получить список офисов
     *
     * @param officesView
     * @return {@List<OfficesView>}
     */
    List<OfficesView> offices(@Valid OfficesView officesView);
}

