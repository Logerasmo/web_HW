package ru.netology;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Request {
    private final String[] requestLine;
    private final List<String> headers;
    private String body;
    private final URLEncodedUtils urlEncodedUtils = new URLEncodedUtils();
    public Request(String[] requestLine, List<String> headers, String body){
        this.requestLine = requestLine;
        this.headers = headers;
        this.body = body;
    }
    public Request(String[] requestLine, List<String> headers){
        this.requestLine = requestLine;
        this.headers = headers;
    }
    public String[] getRequestLine() {
        return requestLine;
    }

    public Optional<String> getBody() {
        return Optional.of(body);
    }

    public List<String> getHeaders() {
        return headers;
    }
    public List<NameValuePair> getQueryParam(String name){
        return URLEncodedUtils.parse(requestLine[1], StandardCharsets.UTF_8).stream().filter(x -> x.getName().equals(name)).collect(Collectors.toList());
    }
    public List<NameValuePair> getQueryParams(){
        return URLEncodedUtils.parse(requestLine[1], StandardCharsets.UTF_8);
    }
}
