package com.jack.rms.common.core;

import org.apache.log4j.Logger;

public class RmsException extends Exception {
	
	    private static final long serialVersionUID = -6202759933628287239L;

	    private static final String DEFAULT_ERROR_CODE = "-10000";

	    private static Logger logger = Logger.getLogger(RmsException.class);

	    private String errorCode;

	    private String errorMsg;

	    private Object errorObj;

	    public String getErrorCode() {
	        return errorCode;
	    }

	    public void setErrorCode(String errorCode) {
	        this.errorCode = errorCode;
	    }

	    public String getErrorMsg() {
	        return errorMsg;
	    }

	    public void setErrorMsg(String errorMsg) {
	        this.errorMsg = errorMsg;
	    }

	    public Object getErrorObj() {
	        return errorObj;
	    }

	    public void setErrorObj(Object errorObj) {
	        this.errorObj = errorObj;
	    }

	    /**
	     * 默认构造，一般用于获取动态方法堆栈
	     */
	    public RmsException() {
	        super();
	        logger.warn("操作执行异常", this);
	    }

	    /**
	     * 根据异常信息构造异常
	     *
	     * @param message
	     */
	    public RmsException(String message) {
	        super(message);
	        this.errorMsg = message;
	        this.errorCode = DEFAULT_ERROR_CODE;
	        logger.warn("操作执行异常", this);
	    }

	    public RmsException(String message, String errorCode) {
	        super(message);
	        this.errorMsg = message;
	        this.errorCode = errorCode;
	        logger.warn("操作执行异常", this);
	    }

	    public RmsException(String message, String errorCode, Object errorObj) {
	        super(message);
	        this.errorMsg = message;
	        this.errorCode = errorCode;
	        this.errorObj = errorObj;
	        logger.warn("操作执行异常", this);
	    }
	}
