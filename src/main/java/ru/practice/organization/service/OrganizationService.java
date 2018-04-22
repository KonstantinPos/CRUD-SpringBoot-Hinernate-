package ru.practice.organization.service;

import ru.practice.ResponseView;
import ru.practice.organization.view.OrganizationView;

/**
 * Service для работы с Organization
 */
public interface OrganizationService {

    /**
     * @param organization
     */
    ResponseView save(OrganizationView organization);

    /**
     * @param organization
     */
    ResponseView update(OrganizationView organization);

    /**
     * @param view
     */
    ResponseView delete(OrganizationView view);

    /**
     * @return
     */
    ResponseView loadById(Long id);

    /**
     * @return
     */
    ResponseView list(OrganizationView organization);
}
