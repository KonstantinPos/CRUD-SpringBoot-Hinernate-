package ru.practice.doctype.view;

/**
 * View для работы с DocType
 */
public class DocTypeView {
    public String id;
    public String name;
    public String code;

    @Override
    public String toString() {
        return "id:" + id + "name:" + name + "code:" + code + "}";
    }
}
