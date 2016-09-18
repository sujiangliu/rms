//package com.edol.util;
//
//import org.apache.commons.lang.StringUtils;
//
//import java.util.StringTokenizer;
//
//import javax.servlet.http.HttpServletRequest;
//
//public class SaleUtil {
//
//	public static String getIp(HttpServletRequest request) {
//		String ip = request.getHeader("x-forwarded-for");
//		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//			ip = request.getHeader("Proxy-Client-IP");
//		}
//		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//			ip = request.getHeader("WL-Proxy-Client-IP");
//		}
//		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//			ip = request.getHeader("HTTP_CLIENT_IP");
//		}
//		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
//		}
//		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//			ip = request.getRemoteAddr();
//		}
//		// 多级反向代理
//		if (null != ip && !"".equals(ip.trim())) {
//			StringTokenizer st = new StringTokenizer(ip, ",");
//			if (st.countTokens() > 1) {
//				return st.nextToken();
//			}
//		}
//		return ip;
//	}
//
//    /**
//     * 检测是否有emoji字符
//     * @param source
//     * @return 一旦含有就抛出
//     */
//    public static boolean containsEmoji(String source) {
//        if (StringUtils.isBlank(source)) {
//            return false;
//        }
//
//        int len = source.length();
//
//        for (int i = 0; i < len; i++) {
//            char codePoint = source.charAt(i);
//
//            if (isEmojiCharacter(codePoint)) {
//                //do nothing，判断到了这里表明，确认有表情字符
//                return true;
//            }
//        }
//
//        return false;
//    }
//
//    private static boolean isEmojiCharacter(char codePoint) {
//        return (codePoint == 0x0) ||
//                (codePoint == 0x9) ||
//                (codePoint == 0xA) ||
//                (codePoint == 0xD) ||
//                ((codePoint >= 0x20) && (codePoint <= 0xD7FF)) ||
//        ((codePoint >= 0xE000) && (codePoint <= 0xFFFD)) ||
//        ((codePoint >= 0x10000) && (codePoint <= 0x10FFFF));
//    }
//}
