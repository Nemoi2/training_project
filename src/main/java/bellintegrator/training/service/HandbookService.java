package bellintegrator.training.service;

import bellintegrator.training.view.DocView;

import java.util.List;

/**
 * Сервис
 */
public interface HandbookService {

    /**
     * Получить список документов
     *
     * @return {@List<DocView>}
     */
    List<DocView> docs();

    /**
     * Получить список стран
     *
     * @return {@List<DocView>}
     */
    List<DocView> contries();
}
