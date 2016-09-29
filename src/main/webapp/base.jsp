<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"
    pageEncoding="UTF-8"%>
<%
	String scheme = request.getScheme();
	String serverName = request.getServerName();
	String contextPath = request.getContextPath();
	int port = request.getServerPort();
	
	//网站的访问跟路径
	String baseURL = scheme + "://" + serverName + ":"+ port + contextPath;

	request.setAttribute("baseURL", baseURL);
	System.out.println("baseURL=" + baseURL);
%>