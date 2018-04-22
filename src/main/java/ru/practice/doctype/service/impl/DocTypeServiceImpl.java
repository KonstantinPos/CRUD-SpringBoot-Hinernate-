package ru.practice.doctype.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practice.doctype.dao.DocTypeDao;
import ru.practice.doctype.model.DocType;
import ru.practice.doctype.service.DocTypeService;
import ru.practice.doctype.view.DocTypeView;

import java.util.ArrayList;
import java.util.List;

/**
 * {@inheritDoc}
 */
@Service
public class DocTypeServiceImpl implements DocTypeService {
    private final Logger log = LoggerFactory.getLogger(DocTypeServiceImpl.class);
    private final DocTypeDao docTypeDao;

    @Autowired
    public DocTypeServiceImpl(DocTypeDao docTypeDao) {
        this.docTypeDao = docTypeDao;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public List<DocTypeView> docs() {
        List<DocType> all = docTypeDao.listAll();
        List<DocTypeView> result = new ArrayList<>();
        for (DocType doctype : all) {
            result.add(responseView(doctype));
        }
        return result;
    }

    /**
     * Convert DocType in DocTypeView
     *
     * @return {@DocTypeView}
     */
    public DocTypeView responseView(DocType docType) {
        DocTypeView view = new DocTypeView();
        view.id = String.valueOf(docType.getId());
        view.name = docType.getName();
        view.code = docType.getCode();
        return view;
    }
}
