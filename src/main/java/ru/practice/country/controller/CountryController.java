package ru.practice.country.controller;

import ru.practice.country.view.CountryView;

import java.util.List;

/**
 * Controller для работы с Country
 */
public interface CountryController {

    /**
     * Get all countries
     *
     * @return JSON countries value
     */
    List<CountryView> countries();
}
