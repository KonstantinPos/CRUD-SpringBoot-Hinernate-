package ru.practice.organization.controller;

import ru.practice.ResponseView;
import ru.practice.organization.view.OrganizationView;

/**
 * Controller для работы с Organization
 */
public interface OrganizationController {

    /**
     * Add organization
     *
     * @param organization
     */
    ResponseView save(OrganizationView organization);

    /**
     * Update organization
     *
     * @param organization
     */
    ResponseView update(OrganizationView organization);

    /**
     * Delete organization
     *
     * @param organization
     */
    ResponseView delete(OrganizationView organization);

    /**
     * Get organization
     *
     * @param id
     * @return JSON organization value
     */
    ResponseView loadById(Long id);

    /**
     * Get all organizations
     *
     * @return JSON organizations value
     */
    ResponseView organizations(OrganizationView organization);
}