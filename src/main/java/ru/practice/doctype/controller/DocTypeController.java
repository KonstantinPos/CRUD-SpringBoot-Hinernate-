package ru.practice.doctype.controller;

import ru.practice.doctype.view.DocTypeView;

import java.util.List;

/**
 * Controller для работы с DocType
 */
public interface DocTypeController {

    /**
     * Get all DocTypes
     *
     * @return JSON DocTypes value
     */
    List<DocTypeView> docs();
}
