package ru.practice.service.usertest;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import ru.practice.Application;
import ru.practice.service.TestFormat;
import ru.practice.user.dao.impl.UserDaoImpl;
import ru.practice.user.model.User;
import ru.practice.user.service.impl.UserServiceImpl;
import ru.practice.user.view.UserView;

import java.util.List;


/**
 * TestService для работы с User
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
@WebAppConfiguration(value = "src/main/resources")
@AutoConfigureTestEntityManager
public class UserServiceTest {

    @Autowired
    private UserDaoImpl userDao;

    UserServiceImpl userService;
    String success;
    String error;

    @Before
    public void initService() {
        userService = new UserServiceImpl(userDao);
        success = "success";
        error = "error";
    }

    /**
     * UserSave для работы с User
     */
    @Test
    @Transactional
    public void userSave() {
        String name = TestFormat.NameForTest();
        UserView view = new UserView();
        view.first_name = name;
        String result = userService.save(view).result;
        Assert.assertEquals(success, result);
    }

    /**
     * UserLoadById для работы с User
     */
    @Test
    public void userLoadById() {
        Long organizationId = 1L;
        UserView view = (UserView) userService.loadById(organizationId).data;
        Assert.assertEquals(organizationId, view.id);
    }

    /**
     * UserList для работы с User
     */
    @Test
    public void userList() {
        String name = "Иван";
        UserView view = new UserView();
        view.first_name = name;
        List userList = (List) userService.listAll(view).data;
        Assert.assertEquals(1, userList.size());
        User user = (User) userList.get(0);
        Assert.assertEquals(name, user.getFirst_name());
    }

    /**
     * UpdateUser для работы с User
     */
    @Test
    @Transactional
    public void updateUser() {
        UserView view = (UserView) userService.loadById(2L).data;
        UserView view1 = view;
        String name = TestFormat.NameForTest();
        view1.first_name = name;
        String result = userService.update(view1).result;
        Assert.assertEquals(success, result);
    }

    /**
     * DeleteOrganization для работы с User
     */
    @Test
    @Transactional
    public void deleteOrganization() {
        Long id = 2L;
        UserView view = new UserView();
        view.id = id;
        String result = userService.delete(view).result;
        Assert.assertEquals(success, result);
    }
}
