package ru.netology;

public class Request {
    private final String method;
    private final String headers;
    private String body;
    public Request(String method, String headers, String body){
        this.method = method;
        this.headers = headers;
        this.body = body;
    }
    public Request(String method, String headers){
        this.method = method;
        this.headers = headers;
    }
    public String getMethod() {
        return method;
    }

    public String getBody() {
        if (body != null){
            return body;
        } else {
            return "";
        }
    }

    public String getHeaders() {
        return headers;
    }
}
