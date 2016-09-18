//package com.jack.rms.util;
//
//import com.google.gson.Gson;
//import com.google.gson.reflect.TypeToken;
//import com.wxap.WXConfig;
//import com.wxap.client.TenpayHttpClient;
//import com.wxap.util.*;
//import com.wxap.util.MD5Util;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.UnsupportedEncodingException;
//import java.net.URLEncoder;
//import java.security.MessageDigest;
//import java.security.NoSuchAlgorithmException;
//import java.util.*;
//
///**
// * Created by yujialin on 15-1-15.
// */
//public class WechatUtil {
//
//    public static Map<String, String> sign(String jsapi_ticket, String url) {
//        Map<String, String> ret = new HashMap<String, String>();
//        String nonce_str = create_nonce_str();
//        String timestamp = create_timestamp();
////        String nonce_str = "82693e11-b9bc-448e-892f-f5289f46cd0f";
////        String timestamp = "1419835025";
//        String signature = "";
//
//        //注意这里参数名必须全部小写，且必须有序
//        String string1 = "jsapi_ticket=" + jsapi_ticket + "&noncestr=" + nonce_str + "&timestamp=" + timestamp + "&url=" + url;
//        System.out.println(string1);
//
//        try
//        {
//            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
//            crypt.reset();
//            crypt.update(string1.getBytes("UTF-8"));
//            signature = byteToHex(crypt.digest());
//        }
//        catch (NoSuchAlgorithmException e)
//        {
//            e.printStackTrace();
//        }
//        catch (UnsupportedEncodingException e)
//        {
//            e.printStackTrace();
//        }
//
//        ret.put("url", url);
//        ret.put("jsapi_ticket", jsapi_ticket);
//        ret.put("nonceStr", nonce_str);
//        ret.put("timestamp", timestamp);
//        ret.put("signature", signature);
//
//        return ret;
//    }
//
//    private static String byteToHex(final byte[] hash) {
//        Formatter formatter = new Formatter();
//        for (byte b : hash)
//        {
//            formatter.format("%02x", b);
//        }
//        String result = formatter.toString();
//        formatter.close();
//        return result;
//    }
//
//    private static String create_nonce_str() {
//        return UUID.randomUUID().toString();
//    }
//
//    private static String create_timestamp() {
//        return Long.toString(System.currentTimeMillis() / 1000);
//    }
//
//    // 特殊字符处理
//    public static String UrlEncode(String src) throws UnsupportedEncodingException {
//        return URLEncoder.encode(src, "GBK").replace("+", "%20");
//    }
//
//    // 获取package带参数的签名包
//    public static String genPackage(SortedMap<String, String> packageParams)
//            throws UnsupportedEncodingException {
//        String sign = createSign(packageParams);
//
//        StringBuffer sb = new StringBuffer();
//        Set es = packageParams.entrySet();
//        Iterator it = es.iterator();
//        while (it.hasNext()) {
//            Map.Entry entry = (Map.Entry) it.next();
//            String k = (String) entry.getKey();
//            String v = (String) entry.getValue();
//            sb.append(k + "=" + UrlEncode(v) + "&");
//        }
//
//        // 去掉最后一个&
//        String packageValue = sb.append("sign=" + sign).toString();
//        System.out.println("packageValue=" + packageValue);
//        return packageValue;
//    }
//
//    /**
//     * 创建md5摘要,规则是:按参数名称a-z排序,遇到空值的参数不参加签名。
//     */
//    public static String createSign(SortedMap<String, String> packageParams) {
//        StringBuffer sb = new StringBuffer();
//        Set es = packageParams.entrySet();
//        Iterator it = es.iterator();
//        while (it.hasNext()) {
//            Map.Entry entry = (Map.Entry) it.next();
//            String k = (String) entry.getKey();
//            String v = (String) entry.getValue();
//            if (null != v && !"".equals(v) && !"sign".equals(k)
//                    && !"key".equals(k)) {
//                sb.append(k + "=" + v + "&");
//            }
//        }
//        sb.append("key=" + WXConfig.partner_key);
//        System.out.println("md5 sb:" + sb);
//        String sign = com.wxap.util.MD5Util.MD5Encode(sb.toString(), "GBK")
//                .toUpperCase();
//
//        return sign;
//    }
//
//    // 提交预支付
//    public static String sendPrepay(SortedMap packageParams, String token) {
//        String prepayid = "";
//        // 转换成json
//        Gson gson = new Gson();
//		/* String postData =gson.toJson(packageParams); */
//        String postData = "{";
//        Set es = packageParams.entrySet();
//        Iterator it = es.iterator();
//        while (it.hasNext()) {
//            Map.Entry entry = (Map.Entry) it.next();
//            String k = (String) entry.getKey();
//            String v = (String) entry.getValue();
//            if (k != "appkey") {
//                if (postData.length() > 1)
//                    postData += ",";
//                postData += "\"" + k + "\":\"" + v + "\"";
//            }
//        }
//        postData += "}";
//        // 设置链接参数
//        String requestUrl = "https://api.weixin.qq.com/pay/genprepay" + "?access_token=" + token;
//        System.out.println("post url=" + requestUrl);
//        System.out.println("post data=" + postData);
//        TenpayHttpClient httpClient = new TenpayHttpClient();
//        httpClient.setReqContent(requestUrl);
//        String resContent = "";
//        if (httpClient.callHttpPost(requestUrl, postData)) {
//            resContent = httpClient.getResContent();
//            System.out.println("resContent:" + resContent);
//            Map<String, String> map = gson.fromJson(resContent,
//                    new TypeToken<Map<String, String>>() {
//                    }.getType());
//            if ("0".equals(map.get("errcode"))) {
//                prepayid = map.get("prepayid");
//            } else {
//                System.out.println("get token err ,info =" + map.get("errmsg"));
//            }
//            // 设置debug info
//            System.out.println("res json=" + resContent);
//        }
//        return prepayid;
//    }
//
//    /**
//     * 是否财付通签名,规则是:按参数名称a-z排序,遇到空值的参数不参加签名。
//     *
//     * @return boolean
//     */
//    public static boolean isTenpaySign(String partner_key, SortedMap parameters, HttpServletRequest request, HttpServletResponse response) {
//        StringBuffer sb = new StringBuffer();
//        Set es = parameters.entrySet();
//        Iterator it = es.iterator();
//        while (it.hasNext()) {
//            Map.Entry entry = (Map.Entry) it.next();
//            String k = (String) entry.getKey();
//            String v = (String) entry.getValue();
//            if (!"sign".equals(k) && null != v && !"".equals(v)) {
//                // if(!k.equals("PcacheTime")){
//                sb.append(k + "=" + v + "&");
//                // }
//            }
//        }
//
//        sb.append("key=" + partner_key);
//
//        // 算出摘要
//        String enc = TenpayUtil.getCharacterEncoding(request, response);
//        String sign = MD5Util.MD5Encode(sb.toString(), enc).toLowerCase();
//
//        String tenpaySign = (parameters.get("sign") == null ? "" : (String) parameters.get("sign")).toLowerCase();
//
//        // debug信息
////        this.setDebugInfo(sb.toString() + " => sign:" + sign + " tenpaySign:" + tenpaySign);
//
//        return tenpaySign.equals(sign);
//    }
//}
