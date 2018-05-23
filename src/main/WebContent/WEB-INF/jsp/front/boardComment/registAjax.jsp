<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%
	String msg = (String) request.getAttribute("msg");
	out.print(msg);
%>
