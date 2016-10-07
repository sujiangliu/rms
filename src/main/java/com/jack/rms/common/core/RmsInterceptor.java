package com.jack.rms.common.core;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.jack.rms.model.User;

/**
 * Created by jack on 14-12-29.
 */
public class RmsInterceptor extends HandlerInterceptorAdapter {

//    @Autowired
//    private LogisticsUserService logisticsUserService;

	private Logger logger = Logger.getLogger(RmsInterceptor.class);
	
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String logstr = "request url:" + request.getRequestURL() + ", " + request.getMethod() + ", paramMap:" + getRequestMap(request);
        logger.info("http request：url=" + logstr);

        User user = (User) request.getSession().getAttribute("user");
        if (null == user) {
        	String url = request.getScheme() + "://" +
        			request.getServerName() + ":" +
        			request.getServerPort() + "/" +
        			request.getContextPath();
        	response.sendRedirect(url);
        	return false;
        }
//        String verify_code = request.getParameter("logistics_verify_code");
//        String user_id = request.getParameter("user_id");

//        if (!StringUtils.isBlank(verify_code) && !StringUtils.isBlank(user_id)) {
//            Map<String, Object> userMap = logisticsUserService.getById(user_id);
//            if (null != userMap && !"1".equals(userMap.get("status").toString())) {
//                PrintWriter out = response.getWriter();
//                out.print("reSignIn");
//                out.close();
//                return false;
//            }
//
//            if (verify_code.equals(com.jack.rms.util.MD5Util.MD5(user_id + Constants.LOGISTICS_USER_SECRET))) {
//                return super.preHandle(request, response, handler);
//            }
//        }

        return super.preHandle(request, response, handler);

//        PrintWriter out = response.getWriter();
//        out.print("reSignIn");
//        out.close();

//        return false;
    }

    private static Map<String, String> getRequestMap(HttpServletRequest request) {
        Map<String, String> resultMap = new HashMap<String, String>();
        Map<?, ?> requestMap = request.getParameterMap();
        for (Object key : requestMap.keySet()) {
            if ("logistics_verify_code".equals(key)) {
                continue;
            }

            resultMap.put(String.valueOf(key), request.getParameter(String.valueOf(key)) == null ? "" : request
                    .getParameter(String.valueOf(key)).trim());
        }
        return resultMap;
    }

}
