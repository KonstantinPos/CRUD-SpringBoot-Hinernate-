package ru.practice.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestFormat {

    public static String NameForTest() {
        DateFormat format = new SimpleDateFormat("dd.MM HH:mm:ss:SS");
        return "test - " + format.format(new Date());
    }

}
