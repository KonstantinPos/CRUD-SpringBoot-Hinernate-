package ru.practice.service.countrytest;

import org.json.JSONObject;
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

import javax.persistence.EntityManager;

import static org.hamcrest.Matchers.is;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * CountryControllerTest для работы с Country
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
@WebAppConfiguration(value = "src/main/resources")
@AutoConfigureTestEntityManager
public class CountryControllerTest {

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
     * ListCountry для работы с Country
     */
    @Test
    public void listCountry() throws Exception {
        String name = "Украина";
        Long id = 1L;
        String code = "380";
        JSONObject body = new JSONObject();
        body.put("name", name);
        mockMvc.perform(post("/api/country").contentType(APPLICATION_JSON).content(body.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result", is(success)))
                .andExpect(jsonPath("$.data[0].id", is(id)))
                .andExpect(jsonPath("$.data[0].name", is(name)))
                .andExpect(jsonPath("$.data[0].code", is(code)));
    }
}
