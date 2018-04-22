package ru.practice.office.view;

/**
 * View для работы с Office
 */
public class OfficeView {
    public Long id;
    public String name;
    public String address;
    public String phone;
    public byte is_active;
    public Long organization_id;

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setIs_active(byte is_active) {
        this.is_active = is_active;
    }

    public void setOrganization_id(Long organization_id) {
        this.organization_id = organization_id;
    }

    @Override
    public String toString() {
        return "id:" + id + "name:" + name + "address:" + address + "phone:" + phone + "is_active:" + is_active + "organization_id:" + organization_id + "}";
    }
}
