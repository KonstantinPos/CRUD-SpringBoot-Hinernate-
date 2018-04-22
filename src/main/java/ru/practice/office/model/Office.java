package ru.practice.office.model;

import ru.practice.organization.model.Organization;
import ru.practice.user.model.User;

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
import javax.persistence.OneToMany;
import javax.persistence.Version;
import java.util.Set;

/**
 * Офис организации
 */
@Entity
public class Office {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Служебное поле hibernate
     */
    @Version
    private Integer version;

    /**
     * Название
     */
    @Basic(optional = false)
    @Column(length = 50)
    private String name;

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
     * Связь с таблицей organization
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "organization_id")
    private Organization organization_id;

    /**
     * Связь с таблицей user
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "office", cascade = CascadeType.ALL)
    private Set<User> user;

    /**
     * Конструктор для hibernate
     */
    public Office() {
    }

    public Office(String name, String address, String phone, byte is_active, Organization organization_id) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.is_active = is_active;
        this.organization_id = organization_id;
    }

    public Office(String name, String address, String phone, byte is_active) {
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

    public Organization getOrganization() {
        return organization_id;
    }

    public void setOrganization(Organization organization) {
        this.organization_id = organization_id;
    }

    public Set<User> getUser() {
        return user;
    }

    public void setUser(Set<User> user) {
        this.user = user;
    }

}
