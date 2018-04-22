package ru.practice.country.service;

import ru.practice.country.view.CountryView;

import java.util.List;

/**
 * Service для работы с Country
 */
public interface CountryService {

    /**
     * Country service method
     *
     * @return {@CountryView}
     */
    List<CountryView> countries();
}
