package ru.practice.office.controller;

import ru.practice.ResponseView;
import ru.practice.office.view.OfficeView;

/**
 * Controller для работы с Country
 */
public interface OfficeController {

    /**
     * Add office
     *
     * @param office
     */
    ResponseView office(OfficeView office);

    /**
     * Get all offices
     *
     * @return JSON offices value
     */
    ResponseView offices(OfficeView office);

    /**
     * Update office
     *
     * @param office
     */
    ResponseView update(OfficeView office);

    /**
     * Delete office
     *
     * @param office
     */
    ResponseView delete(OfficeView office);

    /**
     * Get office
     *
     * @param id
     * @return JSON office value
     */
    ResponseView loadById(Long id);
}
