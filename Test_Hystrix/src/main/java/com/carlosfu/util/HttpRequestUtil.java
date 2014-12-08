package com.carlosfu.util;

import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HttpRequestUtil {
    public static final String USER_AGENT = "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.2; .NET CLR 1.1.4322)";
    private static final Logger logger = LoggerFactory.getLogger(HttpRequestUtil.class);

    public static String get(String url, String encoder, int timeout) {
        HttpClient httpClient = new HttpClient();
        HttpConnectionManagerParams managerParams = httpClient.getHttpConnectionManager().getParams();

        managerParams.setConnectionTimeout(timeout); // 设置连接超时时间(单位毫秒)
        managerParams.setSoTimeout(timeout); // 设置读数据超时时间(单位毫秒)

        GetMethod getMethod = new GetMethod(url);
        getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
        getMethod.getParams().setParameter(HttpMethodParams.USER_AGENT, USER_AGENT);
        getMethod.getParams().setParameter("http.protocol.cookie-policy", CookiePolicy.BROWSER_COMPATIBILITY);
        try {
            int statusCode = httpClient.executeMethod(getMethod);
            if (statusCode != HttpStatus.SC_OK) {
                throw new HttpException("HttpRequest.statusCode = " + statusCode + " is error. url:" + url);
            }
            // byte[] arr = getMethod.getResponseBody();
            // return new String(arr, encoder);

            InputStream inputStream = getMethod.getResponseBodyAsStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, encoder));
            StringBuilder stringBuffer = new StringBuilder();
            String str;
            while ((str = br.readLine()) != null) {
                stringBuffer.append(str);
            }
            return stringBuffer.toString();

        } catch (Exception e) {
            logger.error(e.getMessage() + ". url:" + url, e);
            return null;
        } finally {
            getMethod.releaseConnection();
        }
    }

    public static String get(String url, String encoder) {
        HttpClient httpClient = new HttpClient();
        HttpConnectionManagerParams managerParams = httpClient.getHttpConnectionManager().getParams();

        managerParams.setConnectionTimeout(1000 * 3); // 设置连接超时时间(单位毫秒)
        managerParams.setSoTimeout(1000 * 3); // 设置读数据超时时间(单位毫秒)

        GetMethod getMethod = new GetMethod(url);
        getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
        getMethod.getParams().setParameter(HttpMethodParams.USER_AGENT, USER_AGENT);
        getMethod.getParams().setParameter("http.protocol.cookie-policy", CookiePolicy.BROWSER_COMPATIBILITY);
        try {
            int statusCode = httpClient.executeMethod(getMethod);
            if (statusCode != HttpStatus.SC_OK) {
                throw new HttpException("HttpRequest.statusCode = " + statusCode + " is error. url:" + url);
            }
            // byte[] arr = getMethod.getResponseBody();
            // return new String(arr, encoder);

            InputStream inputStream = getMethod.getResponseBodyAsStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, encoder));
            StringBuilder stringBuffer = new StringBuilder();
            String str;
            while ((str = br.readLine()) != null) {
                stringBuffer.append(str);
            }
            return stringBuffer.toString();

        } catch (Exception e) {
            logger.error(e.getMessage() + ". url:" + url, e);
            return null;
        } finally {
            getMethod.releaseConnection();
        }
    }

    public static String post(String url, String encoder, Map<String, Object> paramMap) {
        HttpClient httpClient = new HttpClient();
        HttpConnectionManagerParams managerParams = httpClient.getHttpConnectionManager().getParams();

        managerParams.setConnectionTimeout(1000 * 2); // 设置连接超时时间(单位毫秒)
        managerParams.setSoTimeout(1000 * 2); // 设置读数据超时时间(单位毫秒)

        PostMethod postMethod = new PostMethod(url);
        postMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
        postMethod.getParams().setParameter(HttpMethodParams.USER_AGENT, USER_AGENT);
        postMethod.getParams().setParameter("http.protocol.cookie-policy", CookiePolicy.BROWSER_COMPATIBILITY);
        postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, encoder);

        ArrayList<NameValuePair> pairList = new ArrayList<NameValuePair>();
        for (Map.Entry<String, Object> entry : paramMap.entrySet()) {
            String key = entry.getKey();
            String value = String.valueOf(entry.getValue());
            pairList.add(new NameValuePair(key, value));
        }
        postMethod.setRequestBody(pairList.toArray(new NameValuePair[pairList.size()]));

        try {
            int statusCode = httpClient.executeMethod(postMethod);
            if (statusCode != HttpStatus.SC_OK) {
                throw new HttpException("HttpRequest.statusCode = " + statusCode + " is error ");
            }
            InputStream inputStream = postMethod.getResponseBodyAsStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, encoder));
            StringBuilder stringBuffer = new StringBuilder();
            String str;
            while ((str = br.readLine()) != null) {
                stringBuffer.append(str);
            }
            return stringBuffer.toString();

        } catch (Exception e) {
            logger.error(e.getMessage() + ". url:" + url, e);
            return null;
        } finally {
            postMethod.releaseConnection();
        }
    }

    public static List<String> getList(String url, String encoder) {
        HttpClient httpClient = new HttpClient();
        HttpConnectionManagerParams managerParams = httpClient.getHttpConnectionManager().getParams();

        managerParams.setConnectionTimeout(500 * 2); // 设置连接超时时间(单位毫秒)
        managerParams.setSoTimeout(500 * 2); // 设置读数据超时时间(单位毫秒)

        GetMethod getMethod = new GetMethod(url);
        getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
        getMethod.getParams().setParameter(HttpMethodParams.USER_AGENT, USER_AGENT);
        getMethod.getParams().setParameter("http.protocol.cookie-policy", CookiePolicy.BROWSER_COMPATIBILITY);
        try {
            int statusCode = httpClient.executeMethod(getMethod);
            if (statusCode != HttpStatus.SC_OK) {
                throw new HttpException("HttpRequest.statusCode = " + statusCode + " is error ");
            }

            InputStream inputStream = getMethod.getResponseBodyAsStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, encoder));
            List<String> strs = new ArrayList<String>();
            String str;
            while ((str = br.readLine()) != null) {
                strs.add(str);
            }
            return strs;

        } catch (Exception e) {
            logger.error(e.getMessage() + ". url:" + url, e);
            return null;
        } finally {
            getMethod.releaseConnection();
        }
    }

}
