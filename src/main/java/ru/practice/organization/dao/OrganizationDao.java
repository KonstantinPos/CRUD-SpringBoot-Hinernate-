package ru.practice.organization.dao;

import ru.practice.organization.model.Organization;

import java.util.List;

/**
 * Dao для работы с Organization
 */
public interface OrganizationDao {

    /**
     * Сохранить Organization
     *
     * @param organization
     */
    void save(Organization organization);

    /**
     * Получить объекты id,name,is_active Organization
     *
     * @param name,inn,is_active
     * @return
     */
    List<Organization> list(String name, String inn, byte is_active);

    /**
     * Получить Organization по идентификатору
     *
     * @param id
     * @return
     */
    Organization loadById(Long id);

    /**
     * Обновить Organization по идентификатору
     *
     * @param organization
     */
    void update(Organization organization);

    /**
     * Удалить Organization по идентификатору
     *
     * @param id
     */
    void delete(Long id);
}
