package ru.practice.user.service;

import ru.practice.ResponseView;
import ru.practice.user.view.UserView;

/**
 * Service для работы с User
 */
public interface UserService {

    /**
     * @param user
     */
    ResponseView save(UserView user);

    /**
     * @param user
     */
    ResponseView update(UserView user);

    /**
     * @param user
     */
    ResponseView delete(UserView user);

    /**
     * @return
     */
    ResponseView loadById(Long id);

    /**
     * @return
     */
    ResponseView listAll(UserView user);
}
