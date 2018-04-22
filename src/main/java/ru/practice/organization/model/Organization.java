package ru.practice.organization.model;

import ru.practice.office.model.Office;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Version;
import java.util.Set;

/**
 * Организация
 */
@Entity
public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Служебное поле hibernate
     */
    @Version
    private Integer version;

    /**
     * Сокращенное название
     */
    @Basic(optional = false)
    @Column(length = 100)
    private String name;

    /**
     * Полное название
     */
    @Basic(optional = false)
    @Column(length = 150)
    private String full_name;

    /**
     * ИНН
     */
    @Basic(optional = false)
    @Column(length = 12)
    private String inn;

    /**
     * КПП
     */
    @Basic(optional = false)
    @Column(length = 9)
    private String kpp;

    /**
     * Адресс
     */
    @Basic(optional = false)
    @Column(length = 100)
    private String address;

    /**
     * Контактный номер телефона
     */
    @Basic(optional = false)
    @Column(length = 12)
    private String phone;

    /**
     * Статус
     */
    @Basic(optional = false)
    @Column(length = 5)
    private byte is_active;

    /**
     * Связь с таблицей office
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "organization_id", cascade = CascadeType.ALL)
    private Set<Office> offices;

    /**
     * Конструктор для hibernate
     */
    public Organization() {
    }

    public Organization(String name, String full_name, String inn, String kpp, String address, String phone, byte is_active) {
        this.name = name;
        this.full_name = full_name;
        this.inn = inn;
        this.kpp = kpp;
        this.address = address;
        this.phone = phone;
        this.is_active = is_active;
    }

    public Organization(Long id, String name, byte is_active) {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public String getKpp() {
        return kpp;
    }

    public void setKpp(String kpp) {
        this.kpp = kpp;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public byte isIs_active() {
        return is_active;
    }

    public void setIs_active(byte is_active) {
        this.is_active = is_active;
    }

    public Set<Office> getOffices() {
        return offices;
    }

    public void setOffices(Set<Office> offices) {
        this.offices = offices;
    }


    @Override
    public String toString() {
        return "Organization{" +
                "id=" + id +
                ", version=" + version +
                ", name='" + name + '\'' +
                ", full_name='" + full_name + '\'' +
                ", inn='" + inn + '\'' +
                ", kpp='" + kpp + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", is_active=" + is_active +
                ", offices=" + offices +
                '}';
    }
}
