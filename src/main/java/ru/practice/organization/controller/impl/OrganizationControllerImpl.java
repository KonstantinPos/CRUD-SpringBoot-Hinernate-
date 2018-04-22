package ru.practice.organization.controller.impl;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.practice.ResponseView;
import ru.practice.organization.controller.OrganizationController;
import ru.practice.organization.service.OrganizationService;
import ru.practice.organization.view.OrganizationView;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * {@inheritDoc}
 */
@RestController
@RequestMapping(value = "/api", produces = APPLICATION_JSON_VALUE)
public class OrganizationControllerImpl implements OrganizationController {

    private final OrganizationService organizationService;

    public OrganizationControllerImpl(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @RequestMapping(value = "/organization/save", method = {POST})
    public ResponseView save(@RequestBody OrganizationView organization) {
        return organizationService.save(organization);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @RequestMapping(value = "/organization/update", method = {POST})
    public ResponseView update(@RequestBody OrganizationView organization) {
        return organizationService.update(organization);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @RequestMapping(value = "/organization/delete", method = {POST})
    public ResponseView delete(@RequestBody OrganizationView organization) {
        return organizationService.delete(organization);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @RequestMapping(value = "/organization/{id}", method = {GET})
    public ResponseView loadById(@PathVariable("id") Long id) {
        return organizationService.loadById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @RequestMapping(value = "/organization/list", method = {POST})
    public ResponseView organizations(@RequestBody OrganizationView organization) {
        return organizationService.list(organization);
    }
}