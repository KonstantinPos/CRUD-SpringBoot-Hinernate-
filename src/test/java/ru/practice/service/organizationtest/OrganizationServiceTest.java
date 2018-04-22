package ru.practice.service.organizationtest;

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
import ru.practice.organization.dao.impl.OrganizationDaoImpl;
import ru.practice.organization.model.Organization;
import ru.practice.organization.service.impl.OrganizationServiceImpl;
import ru.practice.organization.view.OrganizationView;
import ru.practice.service.TestFormat;

import java.util.List;

/**
 * Test для Organization
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
@WebAppConfiguration(value = "src/main/resources")
@AutoConfigureTestEntityManager
public class OrganizationServiceTest {

    @Autowired
    public OrganizationDaoImpl organizationDao;

    OrganizationServiceImpl organizationService;
    String success;
    String error;

    @Before
    public void initService() {
        organizationService = new OrganizationServiceImpl(organizationDao);
        success = "success";
        error = "error";
    }

    /**
     * TestService для add organization
     */
    @Test
    @Transactional
    public void saveOrganization() {
        String organizationName = TestFormat.NameForTest();
        OrganizationView view = new OrganizationView();
        view.name = organizationName;
        String result = organizationService.save(view).result;
        Assert.assertEquals(success, result);
    }

    /**
     * Test для add organization
     */
    @Test
    public void loadById() {
        Long organizationId = 1L;
        OrganizationView view = (OrganizationView) organizationService.loadById(organizationId).data;
        Assert.assertEquals(organizationId, view.id);
    }

    /**
     * Test для getOrganizationLis
     */
    @Test
    public void getOrganizationList() {
        String organizationName = "ПАО Сбербанк";
        OrganizationView view = new OrganizationView();
        view.name = organizationName;
        List listOrganization = (List) organizationService.list(view).data;
        Assert.assertEquals(1, listOrganization.size());
        Organization organization = (Organization) listOrganization.get(0);
        Assert.assertEquals(organizationName, organization.getName());
    }

    /**
     * Test для update organization
     */
    @Test
    @Transactional
    public void updateOrganization() {
        OrganizationView view = (OrganizationView) organizationService.loadById(2L).data;
        OrganizationView view1 = view;
        String name = TestFormat.NameForTest();
        view1.name = name;
        String result = organizationService.update(view1).result;
        Assert.assertEquals(success, result);
    }

    /**
     * Test для delete organization
     */
    @Test
    @Transactional
    public void deleteOrganization() {
        Long organization = 2L;
        OrganizationView view = new OrganizationView();
        view.id = organization;
        String result = organizationService.delete(view).result;
        Assert.assertEquals(success, result);
    }
}
