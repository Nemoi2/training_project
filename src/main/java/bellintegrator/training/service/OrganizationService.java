package bellintegrator.training.service;

import bellintegrator.training.view.OrganizationsView;
import bellintegrator.training.view.OrganizationView;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;

/**
 * Сервис
 */
@Validated
public interface OrganizationService {

    /**
     * Добавить новую организацию в БД
     *
     * @param organizationView
     */
    void addOrganization(@Valid OrganizationView organizationView);

    /**
     * Обновить организацию в БД
     *
     * @param organizationView
     */
    void updateOrganization(@Valid OrganizationView organizationView);

    /**
     * Получить организацию
     *
     * @return {@OrganizationView}
     */
    OrganizationView getOrganization(Long id);

    /**
     * Получить список организаций
     *
     * @return {@OrganizationView}
     */
    List<OrganizationsView> organizations (OrganizationsView organizationsView);
}
