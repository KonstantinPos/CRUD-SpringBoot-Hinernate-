package ru.practice.doctype.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.practice.doctype.controller.DocTypeController;
import ru.practice.doctype.service.DocTypeService;
import ru.practice.doctype.view.DocTypeView;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * {@inheritDoc}
 */
@RestController
@RequestMapping(value = "/api", produces = APPLICATION_JSON_VALUE)
public class DocTypeControllerImpl implements DocTypeController {
    private final DocTypeService docTypeService;

    @Autowired
    public DocTypeControllerImpl(DocTypeService docTypeService) {
        this.docTypeService = docTypeService;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @RequestMapping(value = "/doc", method = {GET})
    public List<DocTypeView> docs() {
        return docTypeService.docs();
    }
}
