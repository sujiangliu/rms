package com.jack.rms.common.core;


import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.jack.rms.model.User;

public class BaseController {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * The action execution was successful.
     */
    public static final String SUCCESS = "success";

    /**
     * The action execution was a fail.
     */
    public static final String FAIL = "fail";

    /**
     * The Remote execution was a error
     */
    public static final String ERROR = "error";

    /**
     * 成功的Status Code
     */
    private static final int RESCODE_OK = 200;
    /**
     * 失败的Status Code
     */
    private static final int RESCODE_FAIL = 201;

    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(Exception ex, HttpServletRequest request) {
        StringBuffer logBuf = new StringBuffer();
        logBuf.append(request.getRequestURL());
        logBuf.append(",");
        logBuf.append(request.getMethod());
        logBuf.append(",");
        logBuf.append("paramMap:");
        logBuf.append(this.getRequestMap(request).toString());
        logger.error(logBuf.toString(), ex);

        if (ex instanceof RmsException) {
        	RmsException se = (RmsException) ex;
            return new ModelAndView(new MappingJackson2JsonView(), getFailResult(se.getMessage(), Integer.parseInt(se.getErrorCode()), se.getErrorObj()));
        }

        return new ModelAndView(new MappingJackson2JsonView(), getFailResult("系统错误", RESCODE_FAIL));
    }

    protected Map<String, Object> getFailResult(String msg, int code) {
        return getResult(false, code, msg, Collections.EMPTY_MAP);
    }

    protected Map<String, Object> getFailResult(String msg, int code, Object obj) {
        return getResult(false, code, msg, obj);
    }

    protected Map<String, Object> getFailResult(String msg) {
        return getResult(false, RESCODE_FAIL, msg, Collections.EMPTY_MAP);
    }

    protected Map<String, Object> getResult(boolean isOk, int resCode, String errorMsg, Object obj) {
        return createJson(isOk, resCode, errorMsg, obj);
    }

    protected Map<String, Object> createJson(boolean isOk, int resCode, String errorMsg, Object obj) {
        Map<String, Object> jsonMap = new HashMap<String, Object>();
        jsonMap.put("result", isOk ? "ok" : "fail");
        jsonMap.put("rescode", resCode);
        jsonMap.put("msg", errorMsg);
        jsonMap.put("data", obj);
        return jsonMap;
    }

    protected boolean isAjax(HttpServletRequest request) {
        return "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
    }

    protected Map<String, Object> getSuccessResult() {
        return getResult(true, RESCODE_OK, "操作成功！", Collections.EMPTY_MAP);
    }

    protected Map<String, Object> getSuccessResult(Object obj) {
        return getResult(true, RESCODE_OK, "操作成功", obj);
    }

    protected String getRealPath(String path) {
        ServletContext app = getSessionContext().getServletContext();
        if (null != app) {
            String root = app.getRealPath(String.valueOf(File.separatorChar));
            return root + path;
        }
        return path;
    }

    protected HttpSession getSessionContext() {
        return ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).getRequest().getSession(false);
    }

    public Map<String, String> getRequestMap(HttpServletRequest request) {
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

    public String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("X-REAL-IP");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        return ip;
    }
    
    public User getCurrUser() {
    	HttpSession session = getSessionContext();
    	if (null == session) {
    		return null;
    	}
    	Object o = session.getAttribute("user");
    	if (null == o) {
    		return null;
    	}
    	return (User) o;
    }
}

