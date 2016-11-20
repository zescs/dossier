<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<title><fmt:message key="system.login.title"/></title>
<meta name="keywords" http-equiv="keywords" content="${search_keywords}" />
<meta name="description" http-equiv="description" content="${search_description}" />
<link rel="shortcut icon" href="<c:url value="/resources/images/favicon.ico" />" /> 
<link rel="stylesheet" href="<c:url value="/resources/css/login.css" />" type="text/css"/>
<%-- <link rel="stylesheet" href="<c:url value="/resources/scripts/supersized/css/supersized.css" />" type="text/css"/> --%>
<script type="text/javascript" src="<c:url value="/resources/scripts/boot.js" />"></script>
<%-- <script type="text/javascript" src="<c:url value="/resources/scripts/supersized/supersized.3.1.3.min.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/scripts/supersized/supersized-init.js" />"></script> --%>
</head>
<body>
<div id="loading"></div>
<!-- login-start -->
<div id="loginWin" class="mini-window" title="<fmt:message key="admin.login.title"/>"
		 style="width:580px;padding:0px;border:none;"
		 bodyStyle="padding:0px;border:none;" 
		 showModal="false"
		 allowDrag="false"
		 iconCls="icon-cus-group_green"
		 headerStyle="padding:3px 0px;"
		 >
	<div class="login_con" id="loginForm">
		<div class="login_top"></div>
		<div class="login_middle">
			<div class="login_input">
				<div class="from_input_con">
					<label><fmt:message key="user.login.userName"/></label>
					<div class="from_input_con_control">
						 <input id="userName" name="userName" 
						 class="mini-textbox" 
						 emptyText="<fmt:message key="admin.login.userName.message.emptyText"/>"
						 vtype="required"  
						 requiredErrorText="<fmt:message key="admin.login.userName.message.requiredErrorText"/>" 
						 errorMode="border"
						 style="width:180px;height:27px;"
						 borderStyle="border-radius:5px;height:25px;"
						 inputStyle="height:25px;padding:0px 3px;"
						 />
					</div>
				</div>
				<div class="from_input_con">
					<label><fmt:message key="user.login.password"/></label>
					<div class="from_input_con_control">
						  <input
						  		id="password"
						  		name="password" 
						  		class="mini-password" 
						  		emptyText="<fmt:message key="admin.login.password.message.emptyText"/>" 
						  		vtype="required" 
						  		requiredErrorText="<fmt:message key="admin.login.password.message.requiredErrorText"/>"
						  		errorMode="border"
								style="width:180px;height:27px;"
								borderStyle="border-radius:5px;height:25px;"
								inputStyle="height:25px;padding:0px 3px;"
						  	/>
					</div>
				</div>
				<div class="from_input_con">
					<label></label>
					<div class="from_input_con_control">
						<div class="securityCodeImg_con">
							<img id="securityCodeImg" src="<c:url value="/securityCode" />" style="display:none;" title="点击刷新"/>
						</div>
					</div>
				</div>
				<div class="from_input_con">
					<label><fmt:message key="user.login.securityCode"/></label>
					<div class="from_input_con_control">
						  <input id="securityCode" 
						  		 name="securityCode" 
						  		 class="mini-textbox" 
						  		 emptyText="<fmt:message key="admin.login.securityCode.message.emptyText"/>" 
						  		 vtype="required"
						  		 requiredErrorText="<fmt:message key="admin.login.securityCode.message.requiredErrorText"/>" 
						  		 onenter="onLoginClick"
						  		 errorMode="border"
								 style="width:180px;height:27px;"
								 borderStyle="border-radius:5px;height:25px;"
								 inputStyle="height:25px;padding:0px 3px;"
						  />
					</div>
				</div>
				<div class="from_input_con">
					<label></label>
					<div class="from_input_con_control">
						  <div id="state" name="state" class="mini-checkbox" checked="true" readOnly="false" text="<fmt:message key="user.login.removeUserName"/>" ></div>
					</div>
				</div>
			</div>
		</div>
		<div class="login_btn">
			<ul class="after">
				<li>
					 <a href="javascript:onLoginClick();" class="submit"><fmt:message key="user.login"/></a>
			   </li>
			</ul>
		</div>
		
	</div>
	<input id="modulus" class="mini-hidden" value="${requestScope.modulus}"/>
	<input id="exponent" class="mini-hidden" value="${requestScope.exponent}"/>
</div>
<!-- login-end -->
<script type="text/javascript">  
	mini.parse();
	var modulus = mini.get("#modulus").getValue();
	var exponent = mini.get("#exponent").getValue();
	var publicKey = RSAUtils.getKeyPair(exponent, '', modulus);        
	var securityCode = mini.get("#securityCode");
	var loginWin = mini.get('loginWin');
	loginWin.show();
	$(window).resize(function(){
	    loginWin.show();
	 });
	$(function() {
			var userName ="${cookie.userName.value}";
			if(userName){
				mini.get("userName").setValue(userName);
			}
			setTimeout(function(){
				  $("#securityCodeImg").attr("src",'<c:url value="/securityCode" />?random=' + new Date());
			      $("#securityCodeImg").css("display","block");
			},100);
	});
	$('#securityCodeImg').click(function() {
	   $(this).css("display","none");
	   setTimeout(function(){
		  $("#securityCodeImg").attr("src",'<c:url value="/securityCode" />?random=' + new Date());
	      $("#securityCodeImg").css("display","block");
	   },100);
	});
	function onLoginClick(e) {
	    var form = new mini.Form('#loginForm');
	    form.validate();
	    if (form.isValid() == false) return;
	    var messageId = mini.loading('登录中，请稍等......','提示');
		var password = mini.get('password');
		password.setValue(RSAUtils.encryptedString(publicKey, password.getValue()));
	   	$.ajax({
	   		url:'<c:url value="/login.action" />',
	   		type:'post',
	   		data:form.getData(true,false),
	   		success:function(data) {
	   			mini.hideMessageBox(messageId);
	   			if (data.flag) {
	   				//连接聊天客户端
	   				//客户端将当前登录成功的用户加入聊天群中
	   				loginWin.hide();
	   	            mini.loading(data.message, '提示');
	   	            setTimeout(function () {
	   	               location.href = '${context_path}/main';
	   	            },200);
	   			} else {
	   				$('#securityCodeImg').attr('src','<c:url value="/securityCode" />?random=' + new Date());
	   				password.setValue('');
	   				securityCode.setValue('');
	                mini.showMessageBox({
	                    width: 250,
	                    title: '提示',
	                    buttons: ['ok'],
	                    message: data.message,
	                    iconCls: 'mini-messagebox-info'
	                });  
	   			}
	   		},
	   		dataType:'json',
	       	error: function (jqXHR, textStatus, errorThrown) {
	       		mini.hideMessageBox(messageId);
	       		var data =jqXHR.responseJSON.error;
	       		$('#securityCodeImg').attr('src','<c:url value="/securityCode" />?random=' + new Date());
					password.setValue('');
					securityCode.setValue('');
	            mini.showMessageBox({
	                width: 250,
	                title: '提示',
	                buttons: ['ok'],
	                message: data.message,
	                iconCls: 'mini-messagebox-info'
	            });  
	         }
	   	});
	}
	function onChangeLan(e){
		if(e.value!='default'){
			location.href="${ctx}/login?locale="+e.value;
		}
	}
</script>
</body>
</html>
