package ru.practice.doctype.service;

import ru.practice.doctype.view.DocTypeView;

import java.util.List;

/**
 * Service для работы с DocType
 */
public interface DocTypeService {

    /**
     * DocType service method
     *
     * @return {@DocTypeView}
     */
    List<DocTypeView> docs();
}
