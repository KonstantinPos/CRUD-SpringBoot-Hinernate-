package ru.practice;


/**
 * View для отображения результатов и ошибок
 */
public class ResponseView {
    public String errorMessage;
    public String result;
    public Object data;

    public ResponseView() {
        result = "success";
    }

    public ResponseView(Object data) {
        result = "success";
        this.data = data;
    }

    public ResponseView(String errorMessage) {
        result = "error";
        this.errorMessage = errorMessage;
    }
}
