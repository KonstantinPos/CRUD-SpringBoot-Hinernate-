package ru.practice.country.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practice.country.dao.CountryDao;
import ru.practice.country.model.Country;
import ru.practice.country.service.CountryService;
import ru.practice.country.view.CountryView;

import java.util.ArrayList;
import java.util.List;

/**
 * {@inheritDoc}
 */
@Service
@Scope(proxyMode = ScopedProxyMode.INTERFACES)
public class CountryServiceImpl implements CountryService {
    private final Logger log = LoggerFactory.getLogger(CountryServiceImpl.class);
    private final CountryDao countryDao;

    @Autowired
    public CountryServiceImpl(CountryDao countryDao) {
        this.countryDao = countryDao;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public List<CountryView> countries() {
        List<CountryView> result = new ArrayList<>();
        List<Country> all = countryDao.listAll();
        for (Country country : all) {
            result.add(responseView(country));
        }
        return result;
    }

    /**
     * Convert Country in CountryView
     *
     * @return {@CountryView}
     */
    public CountryView responseView(Country country) {
        CountryView view = new CountryView();
        view.id = String.valueOf(country.getId());
        view.name = country.getName();
        view.code = country.getCode();
        return view;
    }
}
