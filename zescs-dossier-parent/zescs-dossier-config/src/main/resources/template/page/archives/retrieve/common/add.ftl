 <%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://zescs.com/jstl/tag/core" prefix="z"%>
<!doctype html>
<html>
<head>
    <script type="text/javascript" src="<c:url value="/resources/scripts/boot.js" />"></script>
    <link href="<c:url value="/resources/css/layout.css" />" rel="stylesheet" type="text/css" />
	<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet" type="text/css" />
</head>
<body>
	<!-- body-start -->
	<!-- form-start -->
	<form id="entityForm" method="post">
	  <div id="from_con">
	   	 <div class="from_item after">
			<label class="common_label"><fmt:message key="archives.archiveType"/>:</label>
			<div class="form_item_middle">
				<input id="archivesId" 
					   name="archivesId" 
					   class="mini-combobox m-textbox-common"
					   valueField="archivesId"
					   textField="name" 
					   showNullItem="true"
					   value="${r'${archivesId}'}"
					   emptyText="<fmt:message key="global.message.form.combobox.emptyText"/>"
			           nullItemText="<fmt:message key="global.message.form.combobox.nullItemText"/>" 
					   errorMode="border"
					   url="<c:url value="/archives/queryListByHierarchy.action"/>"
					   allowInput="false" />
			</div>
		 </div>
		 <div class="from_item after">
        		<label class="common_label"></label>
        		<div class="form_item_middle">
        			<div id="indexReset" name="indexReset" class="mini-checkbox" readOnly="false" text="<fmt:message key="archives.message.index.reset"/>"></div>
        		</div>
          </div>
		 <div class="from_item after">
        		<label class="common_label"></label>
        		<div class="form_item_middle">
        			<div id="indexElectFile" name="indexElectFile" class="mini-checkbox" readOnly="false" text="<fmt:message key="archives.message.index.retrieve"/>"></div>
        		</div>
          </div>
	   	  <!-- dh-end -->
	      <div class="from_btn after">
			<label class="common_label"></label>
	  		<div class="from_btn_middle">
	  			<div class="form_btn_left">
	  				<a class="mini-button" iconCls="icon-add" onclick="onSaveData"><fmt:message key="global.add"/></a>
	  			</div>
	  			<div class="form_btn_right">
	  				<a class="mini-button mini-button-iconRight" iconCls="icon-reload" onclick="onRestForm" ><fmt:message key="global.reset"/></a>
	  			</div>
	  		</div>
     	  </div>
	   </div>
	</form>
	<!-- hidden-start -->
	<input name="formCode" id="formCode" value="${r'${requestScope.formCode}'}" class="mini-hidden" />
	<!-- hidden-end -->
	<!-- form-end -->
	<script type="text/javascript">
		///////////////////////////////////////////
		 mini.parse();
		///////////////////////////////////////////
         var form = new mini.Form("entityForm");
		///////////////////////////////////////////
		function onSaveData() {
        	var messageId = mini.loading('<fmt:message key="global.loading"/>','<fmt:message key="global.prompt"/>');
            form.validate();
            if (form.isValid() == false){
            	 mini.hideMessageBox(messageId);
            	 return
            };
        	var data = form.getData(true,false); 
            data.formCode=mini.get("formCode").getValue();
            data["archives.archivesId"]=mini.get("archivesId").getValue();
            $.ajax({
                url: '<c:url value="/archives/entry/retrieve/add.action" />',
				type: 'post',
                data: data,
                cache: false,
                dataType:'json',
                success: function (data) {
                	 mini.get("formCode").setValue(data.formCode);
                	 mini.hideMessageBox(messageId);
                	 mini.showMessageBox({
                         title: '<fmt:message key="global.prompt"/>',
                         iconCls: "mini-messagebox-info",
                         buttons: ["ok"],
                         message: data.message,
                         callback: function (action) {
                        	 if(data.flag){
                         		reset(form);
                         		closeWindow("NO");
                         	}
                         }
                     });
                },
                error: function (jqXHR, textStatus, errorThrown) {
                  	appError(jqXHR, textStatus, errorThrown,messageId,true);
    	         }
            });
        }
		///////////////////////////////////////////
		function onRestForm(){
			setFromData(form);
		}
	</script>
	<!-- body-end -->
</body>
</html>