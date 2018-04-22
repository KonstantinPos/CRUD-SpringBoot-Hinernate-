package ru.practice.user.controller;

import ru.practice.ResponseView;
import ru.practice.user.view.UserView;

/**
 * Controller для работы с User
 */
public interface UserController {

    /**
     * Add user
     *
     * @param user
     */
    ResponseView user(UserView user);

    /**
     * Get all users
     *
     * @return JSON users value
     */
    ResponseView users(UserView user);

    /**
     * Update organization
     *
     * @param user
     */
    ResponseView update(UserView user);

    /**
     * Delete user
     *
     * @param user
     */
    ResponseView delete(UserView user);

    /**
     * Get organization
     *
     * @param id
     * @return JSON user value
     */
    ResponseView loadById(Long id);
}
