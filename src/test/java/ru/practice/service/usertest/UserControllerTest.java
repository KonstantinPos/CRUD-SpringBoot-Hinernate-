package ru.practice.service.usertest;

import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ru.practice.Application;
import ru.practice.service.TestFormat;
import ru.practice.user.model.User;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import static org.hamcrest.Matchers.is;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * UserTestController для работы с User
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
@WebAppConfiguration(value = "src/main/resources")
@AutoConfigureTestEntityManager
public class UserControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private EntityManager em;

    private MockMvc mockMvc;
    private String success = "success";
    private String error = "error";

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    /**
     * UserList для работы с User
     */
    @Test
    public void userList() throws Exception {
        Long office = 1L;
        String position = "менеджер";
        JSONObject body = new JSONObject();
        body.put("position", position);
        body.put("office", office);

        mockMvc.perform(post("/api/user/list/").contentType(APPLICATION_JSON_UTF8).content(body.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result", is(success)))
                .andExpect(jsonPath("$.data[0].position", is(position)));
    }

    /**
     * LoadUserById для работы с User
     */
    @Test
    public void loadUserById() throws Exception {
        Long id = 1L;
        mockMvc.perform(get("/api/user/" + id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result", is(success)))
                .andExpect(jsonPath("$.data.id", is(id)));
    }

    /**
     * UpdateUser для работы с User
     */
    @Test
    public void updateUser() throws Exception {
        Long id = 1L;
        String firstName = TestFormat.NameForTest();
        String lastName = TestFormat.NameForTest();
        JSONObject body = new JSONObject();
        body.put("firstName", firstName);
        body.put("lastName", lastName);
        body.put("id", id);

        mockMvc.perform(post("/api/user/update/").contentType(APPLICATION_JSON_UTF8).content(body.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result", is(success)));
        User user = (User) em.
                createNativeQuery("SELECT * FROM user WHERE id=" + id, User.class).getSingleResult();
        Assert.assertEquals(firstName, user.getFirst_name());
        Assert.assertEquals(lastName, user.getSecond_name());
    }

    /**
     * DeleteUser для работы с User
     */
    @Test
    public void deleteUser() throws Exception {
        Long id = 1L;
        JSONObject body = new JSONObject();
        body.put("id", id);

        mockMvc.perform(post("/api/user/delete/").contentType(APPLICATION_JSON_UTF8).content(body.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result", is(success)));
        try {
            User user = (User) em.
                    createNativeQuery("SELECT * FROM user WHERE id=" + id, User.class).getSingleResult();
            Assert.fail("User not deleted");
        } catch (NoResultException ex) {
        }
    }

    /**
     * SaveUser для работы с User
     */
    @Test
    public void saveUser() throws Exception {
        String firstName = TestFormat.NameForTest();
        String position = "консультант";
        JSONObject body = new JSONObject();
        body.put("firstName", firstName);
        body.put("position", position);

        mockMvc.perform(post("/api/user/save/").contentType(APPLICATION_JSON_UTF8).content(body.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result", is(success)));
        User user = (User) em.
                createNativeQuery("SELECT * FROM user ORDER BY 1 DESC LIMIT 1", User.class).getSingleResult();
        Assert.assertEquals(firstName, user.getFirst_name());
        Assert.assertEquals(position, user.getPosition());
    }
}
