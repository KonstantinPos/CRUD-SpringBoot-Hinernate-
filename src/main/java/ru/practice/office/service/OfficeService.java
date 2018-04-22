package ru.practice.office.service;

import ru.practice.ResponseView;
import ru.practice.office.view.OfficeView;

/**
 * Service для работы с Office
 */
public interface OfficeService {

    /**
     * @param office
     */
    ResponseView save(OfficeView office);

    /**
     * @param office
     */
    ResponseView update(OfficeView office);

    /**
     * @param officeView
     */
    ResponseView delete(OfficeView officeView);

    /**
     * @return
     */
    ResponseView loadById(Long id);

    /**
     * @return
     */
    ResponseView listAll(OfficeView officeView);
}
