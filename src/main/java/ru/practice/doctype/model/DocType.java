package ru.practice.doctype.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 * Справочник документов
 */
@Entity
@Table(name = "doc_type")
public class DocType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Служебное поле hibernate
     */
    @Version
    private Integer version;

    /**
     * Наименование документа
     */
    @Basic(optional = false)
    @Column(length = 150)
    private String name;

    /**
     * Номер документа
     */
    @Basic(optional = false)
    @Column(length = 3)
    private String code;

    /**
     * Конструктор для hibernate
     */
    public DocType() {
    }

    public DocType(String name, String code) {
        this.name = name;
        this.code = code;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
