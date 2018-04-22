package ru.practice.service.countrytest;


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
import ru.practice.country.dao.impl.CountryDaoImpl;
import ru.practice.country.model.Country;
import ru.practice.country.service.impl.CountryServiceImpl;
import ru.practice.country.view.CountryView;

import java.util.List;

/**
 * TestService для Country
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
@WebAppConfiguration(value = "src/main/resources")
@AutoConfigureTestEntityManager
public class CountryServiceTest {

    @Autowired
    private CountryDaoImpl countryDao;

    CountryServiceImpl countryService;
    String success;
    String error;

    @Before
    public void initService() {
        countryService = new CountryServiceImpl(countryDao);
        success = "success";
        error = "error";
    }

    /**
     * Получить все объекты Country
     */
    @Test
    @Transactional
    public void list() {
        String countryName = "Украина";
        CountryView view = new CountryView();
        view.name = countryName;
        List listCountry = countryService.countries();
        Assert.assertEquals(1, listCountry.size());
        Country country = (Country) listCountry.get(0);
        Assert.assertEquals(countryName, country.getName());
    }
}
