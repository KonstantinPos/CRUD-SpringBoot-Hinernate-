package ru.practice.service.organizationtest;

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
import ru.practice.organization.model.Organization;
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
 * TestController для работы с Organization
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
@WebAppConfiguration(value = "src/main/resources")
@AutoConfigureTestEntityManager
public class OrganisationControllerTest {

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
     * SaveOrganization для работы с Organization
     */
    @Test
    public void saveOrganization() throws Exception {
        String name = TestFormat.NameForTest();
        JSONObject body = new JSONObject();
        body.put("name", name);

        mockMvc.perform(post("/api/organization/save").contentType(APPLICATION_JSON).content(body.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result", is(success)));
        Organization newOrganization = (Organization) em.
                createNativeQuery("SELECT * FROM organization ORDER BY id DESC LIMIT 1", Organization.class).getResultList().get(0);
        Assert.assertEquals(name, newOrganization.getName());
    }

    /**
     * LoadByOrganizationId для работы с Organization
     */
    @Test
    public void loadByOrganizationId() throws Exception {
        Long organizationId = 1L;
        mockMvc.perform(get("/api/organization/" + organizationId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result", is(success)))
                .andExpect(jsonPath("$.data.name", is("ПАО Сбербанк")))
                .andExpect(jsonPath("$.data.full_name", is("Публичное акционерное общество Сбербанк")))
                .andExpect(jsonPath("$.data.inn", is("7707083893")))
                .andExpect(jsonPath("$.data.kpp", is("773601001")))
                .andExpect(jsonPath("$.data.address", is("ул. Вавилова, д. 19")));
    }

    /**
     * ListOrganization для работы с Organization
     */
    @Test
    public void listOrganization() throws Exception {
        String name = "ПАО Сбербанк";
        Long organizationId = 1L;
        JSONObject body = new JSONObject();
        body.put("name", name);
        mockMvc.perform(post("/api/organization/list").contentType(APPLICATION_JSON).content(body.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result", is(success)))
                .andExpect(jsonPath("$.data[0].id", is(organizationId)))
                .andExpect(jsonPath("$.data[0].name", is(name)));
    }

    /**
     * UpdateOrganization для работы с Organization
     */
    @Test
    public void updateOrganization() throws Exception {
        Long organizationId = 1L;
        String name = TestFormat.NameForTest();
        JSONObject body = new JSONObject();
        body.put("id", organizationId);
        body.put("name", name);

        mockMvc.perform(post("/api/organization/update").contentType(APPLICATION_JSON).content(body.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result", is(success)));

        Organization organization = (Organization) em
                .createNativeQuery("SELECT * FROM organization WHERE id=1", Organization.class).getResultList().get(0);
        Assert.assertEquals(name, organization.getName());
    }

    /**
     * DeleteOrganization для работы с Organization
     */
    @Test
    public void deleteOrganization() throws Exception {
        Long organizationId = 1L;
        JSONObject body = new JSONObject();
        body.put("id", organizationId);

        mockMvc.perform(post("/api/organization/delete").contentType(APPLICATION_JSON).content(body.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result", is(success)));
        try {
            Organization afterDeleting = (Organization) em
                    .createNativeQuery("SELECT * FROM organizations WHERE id=" + organizationId, Organization.class).getSingleResult();
            Assert.fail("Organization not deleted");
        } catch (NoResultException ex) {
        }
    }
}
