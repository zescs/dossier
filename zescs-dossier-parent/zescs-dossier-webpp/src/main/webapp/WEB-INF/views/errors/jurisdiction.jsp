<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="shortcut icon" href="<c:url value="/resources/images/favicon.ico" />" />
<script type="text/javascript" src="<c:url value="/resources/scripts/boot.js" />"></script>
<link rel="stylesheet" href="<c:url value="/resources/css/error.css" />" type="text/css"/>
<style type="text/css">
div#content_img{
	width:488px;
	height:300px;
	position:absolute; 
}
div.content_img_con {
	height: 300px;
	text-align:center;
	z-index: 2000;
	width:488px;
	border: 1px rgba(0, 0, 0, .4) solid;
	background-color: white;
	-webkit-border-radius: 6px;
	-moz-border-radius: 6px;
	border-radius: 6px;
	-webkit-box-shadow: 1px 1px 5px 2px rgba(0, 0, 0, 0.2);
	-moz-box-shadow: 1px 1px 5px 2px rgba(0, 0, 0, 0.2);
	box-shadow: 1px 1px 5px 2px rgba(0, 0, 0, 0.2);
	background:url(${ctx}/resources/images/jurisdiction.png) no-repeat;
}

#preview-pane .preview-container {
	width: 120px;
	height: 120px;
	overflow: hidden;
}
.re_cog{
	display: inline-block;
	width:360px;
	height:40px;
	position: absolute;
	top:106px;
	left:83px;
}
</style>
</head>
<body>
	<div id="content_img">
		<div class="content_img_con">
		 <a href="<c:url value="/user/admin/logout" />" target="_top" class="re_cog"></a>
		</div>
	</div>
	<script type="text/javascript">
		$(window).resize(function(){ 
			$('#content_img').css({ 
				position:'absolute', 
				left: ($(window).width() - $('#content_img').outerWidth())/2, 
				top: ($(window).height() - $('#content_img').outerHeight())/2 + $(document).scrollTop() 
			}); 
		});
		$(window).resize();
    </script>
</body>
</html>