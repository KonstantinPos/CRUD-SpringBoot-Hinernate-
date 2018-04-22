package ru.practice.user.model;

import ru.practice.country.model.Country;
import ru.practice.doctype.model.DocType;
import ru.practice.office.model.Office;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Version;
import java.sql.Date;

/**
 * Сотрудники
 */
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Служебное поле hibernate
     */
    @Version
    private Integer version;

    /**
     * Имя
     */
    @Basic(optional = false)
    @Column(length = 50)
    private String first_name;

    /**
     * Отчество
     */
    @Basic(optional = false)
    @Column(length = 50)
    private String second_name;

    /**
     * Фамилия
     */
    @Basic(optional = false)
    @Column(length = 50)
    private String middle_name;

    /**
     * Должность
     */
    @Basic(optional = false)
    @Column(length = 100)
    private String position;

    /**
     * Контактный номер телефона
     */
    @Basic(optional = false)
    @Column(length = 12)
    private String phone;

    /**
     * Дата выдачи документа
     */
    @Basic(optional = false)
    @Column(length = 10)
    private Date doc_date;

    /**
     * Статус
     */
    @Basic(optional = false)
    @Column(length = 5)
    private boolean is_identified;

    /**
     * Связь с таблицей country
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "citizenship_country_id")
    private Country country;

    /**
     * Связь с таблицей doc_type
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "doc_type_id")
    private DocType doc_type;

    /**
     * Связь с таблицей office
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "office_id")
    private Office office;

    /**
     * Конструктор для hibernate
     */
    public User() {
    }

    public User(String first_name, String second_name, String middle_name, String position, String phone, Date doc_date, boolean is_identified, Country country, DocType doc_type, Office office_id) {
        this.first_name = first_name;
        this.second_name = second_name;
        this.middle_name = middle_name;
        this.position = position;
        this.phone = phone;
        this.doc_date = doc_date;
        this.is_identified = is_identified;
        this.country = country;
        this.doc_type = doc_type;
        this.office = office_id;
    }

    public Long getId() {
        return id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getSecond_name() {
        return second_name;
    }

    public void setSecond_name(String second_name) {
        this.second_name = second_name;
    }

    public String getMiddle_name() {
        return middle_name;
    }

    public void setMiddle_name(String middle_name) {
        this.middle_name = middle_name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean getIs_identified() {
        return is_identified;
    }

    public void setIs_identified(boolean is_identified) {
        this.is_identified = is_identified;
    }

    public Date getDoc_date() {
        return doc_date;
    }

    public void setDoc_date(Date doc_date) {
        this.doc_date = doc_date;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public DocType getDoc_type() {
        return doc_type;
    }

    public void setDoc_type(DocType doc_type) {
        this.doc_type = doc_type;
    }

    public Office getOffice() {
        return office;
    }

    public void setOffice(Office office) {
        this.office = office;
    }


}
