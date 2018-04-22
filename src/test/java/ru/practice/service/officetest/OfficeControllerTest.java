package ru.practice.service.officetest;

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
import ru.practice.office.model.Office;
import ru.practice.service.TestFormat;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import static org.hamcrest.Matchers.is;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * OfficeControllerTest для работы с Office
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
@WebAppConfiguration(value = "src/main/resources")
@AutoConfigureTestEntityManager
public class OfficeControllerTest {

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
     * OfficeList для работы с Office
     */
    @Test
    public void officeList() throws Exception {
        Long organizationId = 1L;
        String phone = "84957397739";
        JSONObject body = new JSONObject();
        body.put("phone", phone);

        mockMvc.perform(post("/api/office/list/" + organizationId).contentType(APPLICATION_JSON).content(body.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result", is(success)))
                .andExpect(jsonPath("$.data[0].organization.id", is(organizationId)))
                .andExpect(jsonPath("$.data[0].phone", is(phone)));
    }

    /**
     * LoadOfficeById для работы с Office
     */
    @Test
    public void loadOfficeById() throws Exception {
        Long officeId = 1L;
        String name = "Площадь Ильича";
        mockMvc
                .perform(get("/api/office/" + officeId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result", is(success)))
                .andExpect(jsonPath("$.data.id", is(officeId)))
                .andExpect(jsonPath("$.data.name", is(name)));
    }

    /**
     * UpdateOffice для работы с Office
     */
    @Test
    public void updateOffice() throws Exception {
        Long officeId = 1L;
        String name = TestFormat.NameForTest();
        JSONObject body = new JSONObject();
        body.put("id", officeId);
        body.put("name", name);
        mockMvc.perform(post("/api/office/update").contentType(APPLICATION_JSON).content(body.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result", is(success)));
        Office office = (Office) em
                .createNativeQuery("SELECT * FROM office WHERE id =" + officeId, Office.class).getSingleResult();
        Assert.assertEquals(name, office.getName());
    }

    /**
     * DeleteOffice для работы с Office
     */
    @Test
    public void deleteOffice() throws Exception {
        Long officeId = 1L;
        JSONObject body = new JSONObject();
        body.put("id", officeId);
        mockMvc.perform(post("/api/office/delete").contentType(APPLICATION_JSON).content(body.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result", is(success)));
        try {
            Office office = (Office) em
                    .createNativeQuery("SELECT * FROM office WHERE id =" + officeId, Office.class).getSingleResult();
            Assert.fail("Office not deleted");
        } catch (NoResultException ex) {
        }
    }

    /**
     * SaveOffice для работы с Office
     */
    @Test
    public void saveOffice() throws Exception {
        Long officeId = 1L;
        String name = TestFormat.NameForTest();
        JSONObject body = new JSONObject();
        body.put("organizationId", officeId);
        body.put("name", name);

        mockMvc.perform(post("/api/office/save").contentType(APPLICATION_JSON).content(body.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result", is(success)));

        Office newOffice = (Office) em.
                createNativeQuery("SELECT * FROM offices ORDER BY id DESC LIMIT 1", Office.class).getSingleResult();
        Assert.assertEquals(name, newOffice.getName());
    }
}
