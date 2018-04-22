package ru.practice.user.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practice.ResponseView;
import ru.practice.country.view.CountryView;
import ru.practice.doctype.view.DocTypeView;
import ru.practice.user.dao.UserDao;
import ru.practice.user.model.User;
import ru.practice.user.service.UserService;
import ru.practice.user.view.UserView;

import java.util.ArrayList;
import java.util.List;

/**
 * {@inheritDoc}
 */
@Service
public class UserServiceImpl implements UserService {
    private final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public ResponseView save(UserView view) {
        try {
            userDao.save(responseUser(view));
            return new ResponseView();
        } catch (Exception e) {
            return new ResponseView("Save user is not successful");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public ResponseView update(UserView view) {
        try {
            userDao.update(responseUser(view));
            return new ResponseView();
        } catch (Exception e) {
            return new ResponseView("Update user is not successful");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public ResponseView delete(UserView view) {
        try {
            userDao.delete(view.id);
            return new ResponseView();
        } catch (Exception e) {
            return new ResponseView("Delete user is not successful");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public ResponseView loadById(Long id) {
        User user = userDao.loadById(id);
        try {

            return new ResponseView(responseView(user));
        } catch (NullPointerException e) {
            return new ResponseView("Office not found");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public ResponseView listAll(UserView userView) {
        CountryView countryView = new CountryView();
        DocTypeView docTypeView = new DocTypeView();
        List<User> all = userDao.list(userView.office_id, userView.first_name, userView.second_name, userView.middle_name, userView.position, countryView.code, docTypeView.code);
        List<UserView> result = new ArrayList<>();
        for (User user : all) {
            result.add(responseView(user));
        }
        return new ResponseView(result);
    }

    /**
     * Convert UserView in User
     *
     * @return {@User}
     */
    public User responseUser(UserView view) {
        User user = new User(view.first_name, view.second_name, view.middle_name, view.position, view.phone, view.doc_date, view.is_identified, view.citizenship_country_id, view.doc_type_id, view.office_id);
        return user;
    }

    /**
     * Convert User in UserView
     *
     * @return {@UserView}
     */
    public UserView responseView(User user) {
        UserView view = new UserView();
        view.id = user.getId();
        view.first_name = user.getFirst_name();
        view.second_name = user.getSecond_name();
        view.middle_name = user.getMiddle_name();
        view.position = user.getPosition();
        view.phone = user.getPosition();
        view.doc_date = user.getDoc_date();
        view.is_identified = user.getIs_identified();
        view.citizenship_country_id = user.getCountry();
        view.doc_type_id = user.getDoc_type();
        view.office_id = user.getOffice();
        return view;
    }
}
