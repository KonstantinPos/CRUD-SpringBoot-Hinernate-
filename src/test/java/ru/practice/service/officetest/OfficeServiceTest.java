package ru.practice.service.officetest;

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
import ru.practice.office.dao.OfficeDao;
import ru.practice.office.model.Office;
import ru.practice.office.service.impl.OfficeServiceImpl;
import ru.practice.office.view.OfficeView;

import java.util.List;


/**
 * TestService для работы с Office
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
@WebAppConfiguration(value = "src/main/resources")
@AutoConfigureTestEntityManager
public class OfficeServiceTest {

    @Autowired
    private OfficeDao officeDao;

    OfficeServiceImpl officeService;
    String success;
    String error;

    @Before
    public void initService() {
        officeService = new OfficeServiceImpl(officeDao);
        success = "success";
        error = "error";
    }

    /**
     * OfficesList для работы с Office
     */
    @Test
    public void OfficesList() {
        Long organizationId = 1L;
        String phone = "84957397739";
        OfficeView view = new OfficeView();
        view.id = organizationId;
        view.phone = phone;
        List listOfOffices = (List) officeService.listAll(view).data;
        Assert.assertEquals(1, listOfOffices.size());
        Office office = (Office) listOfOffices.get(0);
        Assert.assertEquals(phone, office.getPhone());
    }

    /**
     * GetOfficeById для работы с Office
     */
    @Test
    public void getOfficeById() {
        Long officeId = 1L;
        OfficeView offices = (OfficeView) officeService.loadById(1L).data;
        Assert.assertEquals(officeId, offices.id);
    }

    /**
     * UpdateOffice для работы с Office
     */
    @Test
    @Transactional
    public void updateOffice() {
        Long officeId = 1L;
        String address = "ул. Большая Андроньевская, д. 6";
        OfficeView view = new OfficeView();
        view.id = officeId;
        view.address = address;
        String responseResult = officeService.update(view).result;
        Assert.assertEquals(success, responseResult);
    }

    /**
     * DeleteOffice для работы с Office
     */
    @Test
    @Transactional
    public void deleteOffice() {
        Long officeId = 2L;
        OfficeView view = new OfficeView();
        view.id = officeId;
        String result = officeService.delete(view).result;
        Assert.assertEquals(success, result);
    }

    /**
     * SaveOffice для работы с Office
     */
    @Test
    @Transactional
    public void saveOffice() {
        Long organizationId = 1L;
        String name = "Площадь Ильича";
        OfficeView view = new OfficeView();
        view.id = organizationId;
        view.name = name;
        String responseResult = officeService.save(view).result;
        Assert.assertEquals(success, responseResult);
    }
}
