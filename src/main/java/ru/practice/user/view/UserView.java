package ru.practice.user.view;

import ru.practice.country.model.Country;
import ru.practice.doctype.model.DocType;
import ru.practice.office.model.Office;

import java.sql.Date;

/**
 * View для работы с User
 */
public class UserView {
    public Long id;
    public String first_name;
    public String second_name;
    public String middle_name;
    public String position;
    public String phone;
    public Date doc_date;
    public boolean is_identified;
    public Country citizenship_country_id;
    public DocType doc_type_id;
    public Office office_id;

    @Override
    public String toString() {
        return "{id:" + id + "first_name:" + first_name + "second_name:" + second_name + "middle_name:" + middle_name + ":position:" + position + "phone:" + phone + "doc_date:" + doc_date + "is_identified:" + is_identified + "citizenship_country_id:" + citizenship_country_id + "doc_type_id:" + doc_type_id + "office_id:" + office_id + "}";
    }
}
