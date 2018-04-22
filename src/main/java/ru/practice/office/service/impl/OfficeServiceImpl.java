package ru.practice.office.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practice.ResponseView;
import ru.practice.office.dao.OfficeDao;
import ru.practice.office.model.Office;
import ru.practice.office.service.OfficeService;
import ru.practice.office.view.OfficeView;

import java.util.ArrayList;
import java.util.List;

/**
 * {@inheritDoc}
 */
@Service
@Scope(proxyMode = ScopedProxyMode.INTERFACES)
public class OfficeServiceImpl implements OfficeService {
    private final Logger log = LoggerFactory.getLogger(OfficeServiceImpl.class);
    private final OfficeDao officeDao;

    @Autowired
    public OfficeServiceImpl(OfficeDao officeDao) {
        this.officeDao = officeDao;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public ResponseView save(OfficeView view) {
        try {
            officeDao.save(responseOffice(view));
            return new ResponseView();
        } catch (Exception ex) {
            return new ResponseView("Save office is not successful");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public ResponseView update(OfficeView view) {
        try {
            officeDao.update(responseOffice(view));
            return new ResponseView();
        } catch (Exception e) {
            return new ResponseView("Updating office is not successful");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public ResponseView delete(OfficeView view) {
        try {
            officeDao.delete(view.id);
            return new ResponseView();
        } catch (Exception e) {
            return new ResponseView("Deletion office is not successful");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public ResponseView loadById(Long id) {
        Office office = officeDao.loadById(id);
        try {
            return new ResponseView(responseView(office));
        } catch (NullPointerException e) {
            return new ResponseView("Office not found");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public ResponseView listAll(OfficeView officeView) {
        List<Office> all = officeDao.list(officeView.organization_id, officeView.name, officeView.phone, officeView.is_active);
        List<OfficeView> result = new ArrayList<>();
        for (Office office : all) {
            result.add(responseView(office));
        }
        return new ResponseView(result);
    }

    /**
     * Convert OfficeView in Office
     *
     * @return {@Office}
     */
    public Office responseOffice(OfficeView view) {
        Office office = new Office(view.name, view.address, view.phone, view.is_active);
        return office;
    }

    /**
     * Convert Office in OfficeView
     *
     * @return {@OfficeView}
     */
    public OfficeView responseView(Office office) {
        OfficeView view = new OfficeView();
        view.id = office.getId();
        view.name = office.getName();
        view.address = office.getAddress();
        view.phone = office.getPhone();
        view.is_active = office.isIs_active();
        return view;
    }
}
