package ru.practice.service.doctypetest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import ru.practice.Application;
import ru.practice.doctype.dao.impl.DocTypeDaoImpl;
import ru.practice.doctype.model.DocType;
import ru.practice.doctype.service.impl.DocTypeServiceImpl;
import ru.practice.doctype.view.DocTypeView;

import java.util.List;

/**
 * TestService для работы с Doc_type
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
@WebAppConfiguration(value = "src/main/resources")
@AutoConfigureTestEntityManager
public class DocTypeService {

    @Autowired
    private DocTypeDaoImpl docTypeDao;

    DocTypeServiceImpl docTypeService;
    String success;
    String error;

    @Before
    public void initService() {
        docTypeService = new DocTypeServiceImpl(docTypeDao);
        success = "success";
        error = "error";
    }

    /**
     * Получить все объекты Doc_type
     */
    @Test
    public void list() {
        String doctype = "Свидетельство о рождении";
        DocTypeView view = new DocTypeView();
        view.name = doctype;
        List listDocType = docTypeService.docs();
        Assert.assertEquals(1, listDocType.size());
        DocType docType = (DocType) listDocType.get(0);
        Assert.assertEquals(doctype, docType.getName());
    }
}
