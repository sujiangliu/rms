package com.jack.rms.common;
//package com.jack.rms.util;
//
//import org.apache.http.HttpResponse;
//import org.apache.http.NameValuePair;
//import org.apache.http.client.HttpClient;
//import org.apache.http.client.entity.UrlEncodedFormEntity;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.client.params.CookiePolicy;
//import org.apache.http.client.params.HttpClientParams;
//import org.apache.http.impl.client.DefaultHttpClient;
//import org.apache.http.message.BasicNameValuePair;
//import org.apache.http.params.HttpConnectionParams;
//import org.apache.http.params.HttpParams;
//import org.apache.http.protocol.HTTP;
//import org.apache.http.util.EntityUtils;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
//
///****************************************************************************************
// * 请求发送工具类
// *
// * @author yujialin
// *
// */
//public class HttpRequester {
//
//    private final static int DEFAULT_CONNECTIONTIMEOUT = 15000;
//    private final static int DEFAULT_READTIMEOUT = 15000;
//
//    /*********************************************
//     * 以post方式发送请求
//     * @param url
//     * @param paramMap
//     * @param encode
//     * @return
//     */
//    public static String post( String url, Map<String, String> paramMap, String encode ) {
//
//        return post( url, paramMap, encode, DEFAULT_CONNECTIONTIMEOUT, DEFAULT_READTIMEOUT);
//    }
//
//    /***********************************
//     *
//     * @param url
//     * @param paramMap
//     * @return
//     */
//    public static String post( String url, Map<String, String> paramMap ) {
//
//        return post( url, paramMap, "UTF-8", DEFAULT_CONNECTIONTIMEOUT, DEFAULT_READTIMEOUT);
//    }
//
//    /*************************
//     *
//     * @param url
//     * @return
//     */
//    public static String post( String url ) {
//
//        return post( url, null, "UTF-8", DEFAULT_CONNECTIONTIMEOUT, DEFAULT_READTIMEOUT);
//    }
//
//    /**********************************
//     *
//     * @param url
//     * @param encode
//     * @return
//     */
//    public static String post( String url, String encode ) {
//
//        return post( url, null, encode, DEFAULT_CONNECTIONTIMEOUT, DEFAULT_READTIMEOUT);
//    }
//
//    /***********************************
//     *
//     * @param url
//     * @param connectiontimeout
//     * @param readtimeout
//     * @return
//     */
//    public static String post( String url, int connectiontimeout, int readtimeout ) {
//
//        return post( url, null, "UTF-8", connectiontimeout, readtimeout);
//    }
//
//    /************************************
//     *
//     * @param url
//     * @param encode
//     * @param connectiontimeout
//     * @param readtimeout
//     * @return
//     */
//    public static String post( String url, String encode, int connectiontimeout, int readtimeout ) {
//
//        return post( url, null, encode, connectiontimeout, readtimeout);
//    }
//
//    /********************************************
//     *
//     * @param url
//     * @param paramMap
//     * @param encode
//     * @param connectiontimeout
//     * @param readtimeout
//     * @return
//     */
//    public static String post( String url, Map<String, String> paramMap, String encode, int connectiontimeout, int readtimeout ) {
//        String resultStr = null;
//
//        HttpClient httpclient = new DefaultHttpClient();
//        try {
//            HttpPost httppost = new HttpPost(url);
//            if ( paramMap != null ) {
//                List<NameValuePair> formparams = new ArrayList<NameValuePair>();// 用于存放请求参数
//                for (Map.Entry<String, String> entry : paramMap.entrySet()) {
//                    formparams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
//                }
//                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams,encode);
//                httppost.setEntity(entity);
//            }
//            httppost.setHeader("Connection", "close");
//            HttpParams params = httpclient.getParams();
//            HttpConnectionParams.setConnectionTimeout(params, connectiontimeout);
//            HttpConnectionParams.setSoTimeout(params, readtimeout);
//
//            HttpResponse httpResponse = httpclient.execute(httppost);// 发送post 请求
//            if (httpResponse.getStatusLine().getStatusCode() == 200) {
//                resultStr = EntityUtils.toString(httpResponse.getEntity(), encode);
//                if ( resultStr != null ) {
//                    resultStr = resultStr.trim();
//                }
//            } else {
//                resultStr = httpResponse.getStatusLine().toString();
//                System.out.println( "请求失败，返回：" + resultStr );
//            }
//
//            EntityUtils.consume(httpResponse.getEntity());
//        } catch ( Exception e ) {
//            e.printStackTrace();
//        } finally {
//            if ( httpclient != null ) {
//                httpclient.getConnectionManager().shutdown();
//            }
//        }
//
//        return resultStr;
//    }
//
//    public static String get( String url ) {
//
//        return get(url, "UTF-8");
//    }
//
//    /****************************
//     *
//     * @Description: get方法
//     * @author yujialin
//     * @date May 31, 2011 3:47:39 PM
//     *
//     * @param url
//     * @param encode
//     * @return
//     */
//    public static String get( String url, String encode ) {
//        String resultStr = null;
//
//        System.out.println(url);
//
//        HttpClient httpclient = new DefaultHttpClient();
//
//        try {
//            HttpGet httpget = new HttpGet(url);
//            httpget.setHeader("Connection", "close");
//            HttpParams params = httpclient.getParams();
////			params.setParameter(CoreProtocolPNames.USER_AGENT, UA);
//            HttpConnectionParams.setConnectionTimeout(params, 5000);
//            HttpConnectionParams.setSoTimeout(params, 5000);
//            HttpClientParams.setCookiePolicy(params, CookiePolicy.IGNORE_COOKIES);
//            HttpResponse httpResponse = httpclient.execute(httpget);// 请求
//            if (httpResponse.getStatusLine().getStatusCode() == 200) {
//                resultStr = EntityUtils.toString(httpResponse.getEntity(), encode);
//                if ( resultStr != null ) {
//                    resultStr = resultStr.trim();
//                }
//            } else {
//                resultStr = httpResponse.getStatusLine().toString();
//                System.out.println( "请求失败，返回：" + resultStr );
//            }
//
//            EntityUtils.consume(httpResponse.getEntity());
//        } catch ( Exception e ) {
//            e.printStackTrace();
//        } finally {
//            if ( httpclient != null ) {
//                httpclient.getConnectionManager().shutdown();
//            }
//        }
//
//        return resultStr;
//    }
//}
