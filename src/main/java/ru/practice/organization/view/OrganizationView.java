package ru.practice.organization.view;

/**
 * View для работы с Organization
 */
public class OrganizationView {
    public Long id;
    public String name;
    public String full_name;
    public String inn;
    public String kpp;
    public String address;
    public String phone;
    public byte is_active;

    @Override
    public String toString() {
        return "{id:" + id + "name:" + name + "full_name:" + full_name + "inn:" + inn + "kpp:" + kpp + "address:" + address + "phone:" + phone + "is_active:" + is_active + "}";
    }
}
