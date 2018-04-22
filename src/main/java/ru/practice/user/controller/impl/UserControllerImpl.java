package ru.practice.user.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.practice.ResponseView;
import ru.practice.user.controller.UserController;
import ru.practice.user.service.UserService;
import ru.practice.user.view.UserView;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * {@inheritDoc}
 */
@RestController
@RequestMapping(value = "/api", produces = APPLICATION_JSON_VALUE)
public class UserControllerImpl implements UserController {

    private final UserService userService;

    @Autowired
    public UserControllerImpl(UserService userService) {
        this.userService = userService;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @RequestMapping(value = "/user/save", method = {POST})
    public ResponseView user(UserView user) {
        return userService.save(user);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @RequestMapping(value = "/user/list", method = {POST})
    public ResponseView users(@RequestBody UserView user) {
        return userService.listAll(user);
    }

    @Override
    @RequestMapping(value = "/user/update", method = {POST})
    public ResponseView update(UserView user) {
        return userService.update(user);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @RequestMapping(value = "/user/delete", method = {POST})
    public ResponseView delete(@RequestBody UserView user) {
        return userService.delete(user);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @RequestMapping(value = "/user/{id}", method = {GET})
    public ResponseView loadById(@PathVariable("id") Long id) {
        return userService.loadById(id);
    }
}
