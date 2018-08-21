package com.jtthink.httpClient;

public interface IHttpService {

    public String get(HttpConfig httpConfig);

    public String post(String requestMsg, HttpConfig httpConfig);

}
