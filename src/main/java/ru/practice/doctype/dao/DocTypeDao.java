package ru.practice.doctype.dao;

import ru.practice.doctype.model.DocType;

import java.util.List;

/**
 * Dao для работы с Doc_type
 */
public interface DocTypeDao {

    /**
     * Получить все объекты DocType
     *
     * @return
     */
    List<DocType> listAll();
}
