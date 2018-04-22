package ru.practice.office.dao;

import ru.practice.office.model.Office;

import java.util.List;

/**
 * Dao для работы с Office
 */
public interface OfficeDao {

    /**
     * Сохранить Office
     *
     * @param office
     */
    void save(Office office);

    /**
     * Получить все объекты Office
     *
     * @return
     */
    List<Office> list(Long organization_id, String name, String phone, byte is_active);

    /**
     * Получить Office по идентификатору
     *
     * @param id
     * @return
     */
    Office loadById(Long id);

    /**
     * Обновить Office по идентификатору
     *
     * @param office
     */
    Office update(Office office);

    /**
     * Удалить Office по идентификатору
     *
     * @param id
     */
    void delete(Long id);
}
