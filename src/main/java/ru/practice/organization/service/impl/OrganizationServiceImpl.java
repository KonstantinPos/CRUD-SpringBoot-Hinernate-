package ru.practice.organization.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practice.ResponseView;
import ru.practice.organization.dao.OrganizationDao;
import ru.practice.organization.model.Organization;
import ru.practice.organization.service.OrganizationService;
import ru.practice.organization.view.OrganizationView;

import java.util.ArrayList;
import java.util.List;

/**
 * {@inheritDoc}
 */
@Service
public class OrganizationServiceImpl implements OrganizationService {
    private final OrganizationDao organizationDao;

    @Autowired
    public OrganizationServiceImpl(OrganizationDao organizationDao) {
        this.organizationDao = organizationDao;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public ResponseView save(OrganizationView view) {
        try {
            organizationDao.save(responseOrg(view));
            return new ResponseView();
        } catch (Exception ex) {
            return new ResponseView("Save organization is not successful");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public ResponseView update(OrganizationView view) {
        try {
            organizationDao.update(responseOrg(view));
            return new ResponseView();
        } catch (Exception ex) {
            return new ResponseView("Updating organization is not successful");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public ResponseView delete(OrganizationView view) {
        try {
            organizationDao.delete(view.id);
            return new ResponseView();
        } catch (Exception ex) {
            return new ResponseView("Deletion organization is not successful");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public ResponseView loadById(Long id) {
        Organization organization = organizationDao.loadById(id);
        try {
            return new ResponseView(responseView(organization));
        } catch (NullPointerException ex) {
            return new ResponseView("Organization not found");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public ResponseView list(OrganizationView organizationView) {
        List<OrganizationView> result = new ArrayList<>();
        List<Organization> all = organizationDao.list(organizationView.name, organizationView.inn, organizationView.is_active);
        for (Organization organization : all) {
            result.add(responseView(organization));
        }
        return new ResponseView(result);
    }

    /**
     * Convert OrganizationView in Organization
     *
     * @return {@Organization}
     */
    public Organization responseOrg(OrganizationView view) {
        Organization organization = new Organization(view.name, view.full_name, view.inn, view.kpp, view.address, view.phone, view.is_active);
        return organization;
    }

    /**
     * Convert Organization in OrganizationView
     *
     * @return {@OrganizationView}
     */
    public OrganizationView responseView(Organization organization) {
        OrganizationView view = new OrganizationView();
        view.id = organization.getId();
        view.name = organization.getName();
        view.full_name = organization.getFull_name();
        view.inn = organization.getInn();
        view.kpp = organization.getKpp();
        view.address = organization.getAddress();
        view.phone = organization.getPhone();
        view.is_active = organization.isIs_active();
        return view;
    }
}
