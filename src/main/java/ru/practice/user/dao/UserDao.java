package ru.practice.user.dao;

import ru.practice.office.model.Office;
import ru.practice.user.model.User;

import java.util.List;

/**
 * Dao для работы с User
 */
public interface UserDao {

    /**
     * Сохранить User
     *
     * @param user
     */
    void save(User user);

    /**
     * Получить все объекты User
     *
     * @return
     */
    List<User> list(Office office_id, String first_name, String second_name, String middle_name, String position, String docCode, String citizenshipCode);

    /**
     * Получить User по идентификатору
     *
     * @param id
     * @return
     */
    User loadById(Long id);

    /**
     * Обновить User по идентификатору
     *
     * @param user
     */
    User update(User user);

    /**
     * Удалить User по идентификатору
     *
     * @param id
     */
    void delete(Long id);
}
