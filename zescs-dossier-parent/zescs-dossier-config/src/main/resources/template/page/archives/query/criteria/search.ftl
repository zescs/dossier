<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!doctype html>
<html>
<head>
    <script type="text/javascript" src="<c:url value="/resources/scripts/boot.js" />"></script>
    <link href="<c:url value="/resources/css/layout.css" />" rel="stylesheet" type="text/css" />
	<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet" type="text/css" />
</head>
<body>
	<!-- body-start -->
	<form id="entityForm" method="post">
		<div id="from_con">
			<!-- item-start -->
			<!-- 动态生成 -->
			${search_items!}
			<!-- item-end -->
			<OBJECT classid="clsid:${r'${cardid}'}" id="CVR_IDCard" name="CVR_IDCard" width="0" height="0"></OBJECT>
			<div class="form_item_btn_IDCARD after">
				<div class="form_item_btn_idcard_left">
					<a class="mini-button" iconCls="icon-ok" onclick="onOk()"><fmt:message key="global.message.ok"/></a>
				</div>
				<div class="form_item_btn_idcard_middle">
					<a class="mini-button" iconCls="icon-cus-contact_blue" onclick="onReadIDCard"><fmt:message key="global.message.read.IDCard"/></a>
				</div>
				<div class="form_item_btn_idcard_right">
					<a class="mini-button" iconCls="icon-cus-remove_minus_sign_outline" onclick="onRestForm" ><fmt:message key="global.clear"/></a>
				</div>
			</div>
	     </div>
	</form>
	<!-- hidden-start -->
	<!-- hidden-end -->
	<!-- body-end -->
	<script type="text/javascript">
	  mini.parse();
	  ///////////////////////////////////////////
	  var form = new mini.Form("entityForm");
	  ///////////////////////////////////////////
	  function onRestForm(){
		reset(form);
	  }
	  ///////////////////////////////////////////
	  function onCancel(){
	  	closeWindow("cancel");
	  }
	  ///////////////////////////////////////////
	  function getData() {
	  	return form.getData(true,false);
      }
	  ///////////////////////////////////////////
      function setData(data) {
          if (data) {
          	  form.setData(data);
              form.setChanged(false);
          }
      }
	  ///////////////////////////////////////////
	  function onOk(){
	  	var messageId = mini.loading('<fmt:message key="global.loading"/>','<fmt:message key="global.prompt"/>');
	    form.validate();
	    if (form.isValid() == false){
	    	 mini.hideMessageBox(messageId);
	    	 return
	    };
	  	closeWindow("ok");
	  }
	  ///////////////////////////////////////////
	  function onComboCloseClick(e) {
        var obj = e.sender;
        obj.setText("");
        obj.setValue("");
      }
      ///////////////////////////////////////////
	  function onReadIDCard(){
	  	try{
	  	 //有可能没有提名
	  	 var CVR_IDCard = document.getElementById("CVR_IDCard");					
		 var strReadResult = CVR_IDCard.ReadCard();
		 if(strReadResult == "0"){
		 	mini.get("title").setValue(CVR_IDCard.Name);
		 }else{
		 	mini.alert(strReadResult);
		 }
	  	}catch(e){
	  		console.log(e);
	  	}
	  }
	</script>
</body>
</html>