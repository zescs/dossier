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
	   	  <!-- pro-start -->
	   	  ${form_item!}
	   	  <!-- pro-end -->
	   	   <#if existFile>
		   <div class="from_item after">
        		<label class="common_label"></label>
        		<div class="form_item_middle">
        			<input name="electFileId" id="electFileId" class="mini-hidden" />
        			<a class="mini-button m-btn-common" iconCls="icon-upload" onclick="onAddElectFile"><fmt:message key="global.message.add.electfile" /></a>
        		</div>
        	</div>
		  </#if>
		  <#if existAtt>
		  	<div class="from_item after">
        		<label class="common_label"></label>
        		<div class="form_item_middle">
        			<input name="attachmentIds" id="attachmentIds" class="mini-hidden" />
        			<a class="mini-button m-btn-common" iconCls="icon-upload" onclick="onAddAttachment"><fmt:message key="global.message.add.attachment" /></a>
        		</div>
        	</div>
		  </#if>
	   	  <!-- dh-start -->
	   	  <div  class="from_item after">
				<label class="common_label"><fmt:message key="archives.message.title.show.config.archivesNo"/>:</label>
				<div class="form_item_middle">
					 <input id="dh_stencil" 
					 		name="dh_stencil"
					 		class="mini-combobox m-textbox-common" 
					 		valueField="archivesNoConfigId" 
					 		textField="name"
					 		required="true"
					 		showNullItem="true" 
					 		<#if archivesNoConfigId?exists>
					    	value="${archivesNoConfigId}"
					        </#if>
					 		emptyText="<fmt:message key="global.message.form.combobox.emptyText"/>"
					 		nullItemText="<fmt:message key="global.message.form.combobox.nullItemText"/>"
						    errorMode="border"
						    url="<c:url value="/archives/config/archivesno/quryListByArchives.action"/>?archivesId=${r'${archivesId}'}"
						    allowInput="true" />
				</div>
		  </div>
	   	  <!-- dh-end -->
	      <div class="from_btn after">
			<label class="common_label"></label>
	  		<div class="from_btn_middle">
	  			<div class="form_btn_left">
	  				<a class="mini-button" iconCls="icon-add" onclick="onSaveData"><fmt:message key="global.message.add"/></a>
	  			</div>
	  			<div class="form_btn_right">
	  				<a class="mini-button mini-button-iconRight" iconCls="icon-reload" onclick="onRestForm" ><fmt:message key="global.message.reset"/></a>
	  			</div>
	  		</div>
     	  </div>
	   </div>
	</form>
	<!-- hidden-start -->
	<input name="archivesId" id="archivesId" value="${r'${archivesId}'}" class="mini-hidden" />
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
                url: '<c:url  value="/archives/entry/add.action" />',
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
                         		closeWindow("OK");
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
		///////////////////////////////////////////
		mini.VTypes["remoteLengthErrorText"] = '<fmt:message key="global.message.remoteLengthErrorText"/>';
        mini.VTypes["remoteLength"] = function (v,n) {
           var flag = false;
           var value = v;
           if(value){
	           var length = n[1];
	           if(v.length<length){
	           		var temp ="";
	           		for(var i=0;i<(length-value.length);i++){
						temp+="0";
					}
					value=temp+value;
					mini.get(n[0]).setValue(value);
	           }
	           flag =true;
            }
           return flag;
        }
		///////////////////////////////////////////
		<#if existFile>
		function onAddElectFile(){
			 mini.open({
                url: '<c:url value="/archives/electfile/add.action" />',
                title: '<fmt:message key="global.message.add.electfile"/>',
                width:460,
				height:380,
				allowResize : false,
				allowDrag:false,
				showModal:true,
                iconCls:'icon-addfolder', 
                allowResize:false,
                ondestroy: function (action) {
                   var iframe = this.getIFrameEl();
                   var data = iframe.contentWindow.GetData();
                   mini.get("electFileId").setValue(data);
                }
            });
		}
		</#if>
		///////////////////////////////////////////
		<#if existAtt>
		function onAddAttachment(){
			 mini.open({
                url: '<c:url value="/archives/attachment/add.action" />',
                title: '<fmt:message key="global.message.add.attachment"/>',
                width:460,
				height:380,
				allowResize : false,
				allowDrag:false,
				showModal:true,
                iconCls:'icon-addfolder', 
                allowResize:false,
                ondestroy: function (action) {
                   var iframe = this.getIFrameEl();
                   var data = iframe.contentWindow.GetData();
                   mini.get("attachmentIds").setValue(data);
                }
            });
		}
		</#if>
		///////////////////////////////////////////
		function onComboValidation(e) {
            var items = this.findItems(e.value);
            if (!items || items.length == 0) {
                e.isValid = false;
                e.errorText = "<fmt:message key="global.message.form.combobox.error"/>";
            }
        }
		///////////////////////////////////////////
		function onComboCloseClick(e) {
            var obj = e.sender;
            obj.setText("");
            obj.setValue("");
        }
	</script>
	<!-- body-end -->
</body>
</html>