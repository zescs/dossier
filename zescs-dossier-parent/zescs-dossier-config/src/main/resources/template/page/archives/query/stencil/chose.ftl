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
			<div  class="from_item after">
		   		<label class="common_label"><fmt:message key="archives.stencilConfig.name"/>:</label>
		   		<div class="form_item_middle">
					 <input id="stencilConfigId" 
				 		name="stencilConfigId"
				 		class="mini-combobox m-textbox-common" 
				 		valueField="stencilConfigId" 
				 		textField="name"
				 		required="true"
				 		showNullItem="true" 
				 		emptyText="<fmt:message key="global.message.form.combobox.emptyText"/>"
				 		nullItemText="<fmt:message key="global.message.form.combobox.nullItemText"/>"
					    errorMode="border"
					    <#if stencilConfigId?exists>
					    value="${stencilConfigId}"
					    </#if>
					    url="<c:url value="/archives/config/stencil/quryListByArchives.action"/>?archivesId=${r'${archivesId}'}"
					    allowInput="true" />
				</div>
		    </div>
			<div  class="from_item after">
		   		<label class="common_label"></label>
		   		<div class="form_item_middle">
				</div>
		    </div>
		    <div class="from_btn after">
      		<label class="common_label"></label>
      		<div class="from_btn_middle">
      			<div class="form_btn_left">
        			<a class="mini-button" onclick="onExportData" iconCls="icon-ok" ><fmt:message key="global.ok" /></a>
       			</div>
       			<div class="form_btn_right">
       				<a class="mini-button" onclick="onCancel" iconCls="icon-cancel"><fmt:message key="global.cancel" /></a>
       			</div>
      		</div>
          </div>
        </div>
	</form>
	<!-- hidden-start -->
	<input name="archivesId" id="archivesId" value="${r'${archivesId}'}" class="mini-hidden" />
	<!-- hidden-end -->
	<!-- body-end -->
	<script type="text/javascript">
	  mini.parse();
	  ///////////////////////////////////////////
	  var form = new mini.Form("entityForm");
	  var qurey_data ={};
	  ///////////////////////////////////////////
	  function setData(temp_data){
	  	qurey_data=temp_data;
	  }
	  ///////////////////////////////////////////
	  function onRestForm(){
		reset(form);
	  }
	  ///////////////////////////////////////////
	  function onExportData(){
	  	var messageId = mini.loading('<fmt:message key="global.loading"/>','<fmt:message key="global.prompt"/>');
        form.validate();
        if (form.isValid() == false){
        	 mini.hideMessageBox(messageId);
        	 return
        };
    	var data = form.getData(true,false);
    	qurey_data["stencilConfigId"]=data["stencilConfigId"];
    	location.href="<c:url value='/archives/entry/export/excel.action' />?"+$.param(qurey_data);
       	setTimeout(function(){
       		mini.hideMessageBox(messageId);
       	}, 5000)
	  }
	  ///////////////////////////////////////////
	  function onCancel(e) {
	     closeWindow("cancel");
	  }
	  ///////////////////////////////////////////
	</script>
</body>
</html>