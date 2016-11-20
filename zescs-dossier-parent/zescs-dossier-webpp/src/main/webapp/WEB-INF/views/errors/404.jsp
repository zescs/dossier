<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="<c:url value="/resources/scripts/boot.js" />"></script>
<link rel="stylesheet" href="<c:url value="/resources/css/error.css" />" type="text/css"/>
</head>
<body>
<div style="width:600px;height:600px;margin:0 auto;overflow:hidden;" id="content_img">
	<img src="<c:url value="/resources/images/404-error-template.jpg" />" style="width:400px;height:300px;margin-top:20px;"/>
</div>
</body>
</html>