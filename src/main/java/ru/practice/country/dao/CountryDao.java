package ru.practice.country.dao;

import ru.practice.country.model.Country;

import java.util.List;

/**
 * Dao для работы с Country
 */
public interface CountryDao {

    /**
     * Получить все объекты Country
     *
     * @return
     */
    List<Country> listAll();
}
