package com.longxi.data.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 * @author longxi.cwl
 * @date 2017/11/04
 */
public class HttpUtils {

    /**
     * @param url
     * @return
     */
    public static String get(String url) {
        return get(url, "utf-8");
    }

    /**
     * @param url
     * @param params
     * @return
     */
    public static String post(String url, Map<String, String> params) {
        return post(url, params, "utf-8");
    }

    /**
     * @param url
     * @param encode
     * @return
     */
    public static String get(String url, String encode) {
        String result = null;
        CloseableHttpClient httpClient = createHttpClient();
        try {
            HttpGet httpGet = new HttpGet(url);
            CloseableHttpResponse response = httpClient.execute(httpGet);
            result = resolveResponse(response, encode);
        } catch (Exception e) {
            System.out.println(url + " get " + e.getMessage());
        } finally {
            closeHttpClient(httpClient, url);
        }
        return result;
    }

    /**
     * @param url
     * @param param
     * @param encode
     * @return
     */
    public static String post(String url, Map<String, String> param, String encode) {
        String result = null;
        CloseableHttpClient httpClient = createHttpClient();
        try {
            HttpPost httpPost = new HttpPost(url);
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            if (null != param) {
                for (String key : param.keySet()) {
                    nvps.add(new BasicNameValuePair(key, param.get(key)));
                }
            }
            httpPost.setEntity(new UrlEncodedFormEntity(nvps));
            CloseableHttpResponse response = httpClient.execute(httpPost);
            result = resolveResponse(response, encode);
        } catch (Exception e) {
            System.out.println(url + " post " + e.getMessage());
        } finally {
            closeHttpClient(httpClient, url);
        }
        return result;
    }

    /**
     * @return
     */
    private static CloseableHttpClient createHttpClient() {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        return httpClient;
    }

    /**
     * @param httpClient
     * @param url
     */
    private static void closeHttpClient(CloseableHttpClient httpClient, String url) {
        try {
            if (null != httpClient) {
                httpClient.close();
            }
        } catch (IOException e) {
            System.out.println(url + " post " + e.getMessage());
        }
    }

    /**
     * @param response
     * @return
     * @throws IOException
     */
    private static String resolveResponse(CloseableHttpResponse response, String encode) throws IOException {
        String result = null;
        if (null == response) {
            System.out.println("response is null");
            return result;
        }

        try {
            System.out.println("response statusLine is " + response.getStatusLine());
            HttpEntity entity = response.getEntity();
            result = EntityUtils.toString(entity, encode);
        } catch (Exception e) {
            System.out.println("resolveResponse exception " + e.getMessage());
        } finally {
            response.close();
        }
        return result;
    }
}
