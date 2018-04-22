package ru.practice.office.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.practice.ResponseView;
import ru.practice.office.controller.OfficeController;
import ru.practice.office.service.OfficeService;
import ru.practice.office.view.OfficeView;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * {@inheritDoc}
 */
@RestController
@RequestMapping(value = "/api", produces = APPLICATION_JSON_VALUE)
public class OfficeControllerImpl implements OfficeController {

    private final OfficeService officeService;

    @Autowired
    public OfficeControllerImpl(OfficeService officeService) {
        this.officeService = officeService;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @RequestMapping(value = "/office/save", method = {POST})
    public ResponseView office(@RequestBody OfficeView office) {
        return officeService.save(office);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @RequestMapping(value = "/office/list/", method = {POST})
    public ResponseView offices(@RequestBody OfficeView office) {
        return officeService.listAll(office);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @RequestMapping(value = "/office/update", method = {POST})
    public ResponseView update(OfficeView office) {
        return officeService.update(office);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @RequestMapping(value = "/office/delete", method = {POST})
    public ResponseView delete(@RequestBody OfficeView office) {
        return officeService.delete(office);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @RequestMapping(value = "/office/{id}", method = {GET})
    public ResponseView loadById(@PathVariable("id") Long id) {
        return officeService.loadById(id);
    }
}