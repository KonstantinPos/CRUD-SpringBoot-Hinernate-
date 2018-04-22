package ru.practice.country.view;

/**
 * View для работы с Country
 */
public class CountryView {
    public String id;
    public String name;
    public String code;

    @Override
    public String toString() {
        return "id:" + id + "name:" + name + "code:" + code + "}";
    }
}
