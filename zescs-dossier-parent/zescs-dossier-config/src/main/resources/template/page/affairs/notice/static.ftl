<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<#if datas?exists>
		<ul>
		<#list datas as n>
		  <li><a href="<c:url value="/affairs/notice/preview.action" />?noticeId=${n.noticeId}" target="_blank" title="${n.describe!}">${n.title!}</a><span class="notice_content_right_date">[${n.createDate?string("yyyy-MM-dd")}]</span></li>
		</#list>
		</ul>
	</#if>
</body>
</html>