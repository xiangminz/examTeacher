package com.jtthink.httpClient;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;


public class HttpServiceImpl implements IHttpService{


    private static HttpClient getHttpClient() {

        HttpClient httpClient = new DefaultHttpClient();
        httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 30000);
        httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 30000);

        return httpClient;
    }

    public void shutdowm(HttpClient httpClient){
        httpClient.getConnectionManager().shutdown();
    }


    public String get(HttpConfig httpConfig)  {

        HttpClient httpClient = null;
        String url = "http://"+httpConfig.getServerIp()+":"+httpConfig.getServerPort()+"/"+
                httpConfig.getUrl();
        HttpGet httpGet = new HttpGet(url);
        HttpResponse response = null;
        String content = "";

        try{
            httpClient = getHttpClient();
            response = getHttpClient().execute(httpGet);
            System.out.println(response);

            if(response.getStatusLine().getStatusCode() == 200){
                content = EntityUtils.toString(response.getEntity(), "UTF-8");
            }

        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            if(httpClient != null){
                shutdowm(httpClient);
            }
        }

        return content;
    }

    public String post(String requestMsg, HttpConfig httpConfig)  {

        HttpClient httpClient = null;
        String url = "http://"+httpConfig.getServerIp()+":"+httpConfig.getServerPort()+"/"+
                httpConfig.getUrl();
        HttpResponse response = null;
        String content = "";

        try{
            httpClient = getHttpClient();

            HttpPost post = new HttpPost(url);
            //post.addHeader("Content-Type","text/xml;charset=UTF-8");
            post.addHeader("Content-Type", "application/json");
            StringEntity entity = new StringEntity(requestMsg);
            entity.setChunked(false);
            post.setEntity(entity);

            response = httpClient.execute(post);

            if(response.getStatusLine().getStatusCode() == 200){
                content = EntityUtils.toString(response.getEntity(), "UTF-8");
            }

        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            if(httpClient != null){
                shutdowm(httpClient);
            }
        }

        return content;


    }

}
