<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <script type="text/javascript" src="<c:url value="/resources/scripts/boot.js" />"></script>
  <link href="<c:url value="/resources/css/layout.css" />" rel="stylesheet" type="text/css" />
  <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet" type="text/css" />
  </head>
  <body>
   <form id="entityForm" method="post">
        <div id="from_con">
        	<div class="from_item after">
        		<label class="common_label"><fmt:message key="systemConfig.sysKey"/>:</label>
        		<div class="form_item_middle">
        			 <input id="sysKey" name="sysKey" 
						 class="mini-textbox m-textbox-common" 
						 emptyText="<fmt:message key="systemConfig.sysKey.emptyText"/>"
						 vtype="maxLength:32;required;remoteFieldIsExists:sysKey" 
						 requiredErrorText="<fmt:message key="systemConfig.sysKey.requiredErrorText"/>" 
						 errorMode="border"
                		 maxLengthErrorText="<fmt:message key="global.message.maxLengthErrorText"/>" 
						 />
        		</div>
        	</div>
        	<div class="from_item after">
        		<label class="common_label"><fmt:message key="systemConfig.sysValue"/>:</label>
        		<div class="form_item_middle">
        			 <input id="sysValue" name="sysValue" 
						 class="mini-textbox m-textbox-common" 
						 emptyText="<fmt:message key="systemConfig.sysValue.emptyText"/>"
						 vtype="required;maxLength:120;"  
						 requiredErrorText="<fmt:message key="systemConfig.sysValue.requiredErrorText"/>" 
						 errorMode="border"
						 maxLengthErrorText="<fmt:message key="global.message.maxLengthErrorText"/>" 
						 />
        		</div>
        	</div>
        	<div class="from_item after">
        		<label class="common_label"></label>
        		<div class="form_item_middle">
        			<div id="enableState" name="enableState" class="mini-checkbox" readOnly="false" text="<fmt:message key="systemConfig.enableState"/>" onvaluechanged="onValueChanged"></div>
        		</div>
        	</div>
        	<div class="from_item after">
        		<label class="common_label"></label>
        		<div class="form_item_middle">
        			<div id="flush" name="flush" enabled="false" class="mini-checkbox" readOnly="false" text="<fmt:message key="systemConfig.flush"/>"></div>
        		</div>
        	</div>
        	<div class="from_item after">
        		<label class="common_textarea"><fmt:message key="systemConfig.remark"/>:</label>
        		<div class="form_item_middle">
        			 <input id="remark" 
        			 	 name="remark" 
						 class="mini-textarea m-textarea-common" 
						 errorMode="border"
						 emptyText="<fmt:message key="systemConfig.remark.emptyText"/>"
						 style="height:118px;"
						 />
        		</div>
        	</div>
        	<div class="from_btn after">
        		<label class="common_label"></label>
        		<div class="from_btn_middle">
        			<div class="form_btn_left">
        				<a class="mini-button" iconCls="icon-add" onclick="saveData()"><fmt:message key="global.add"/></a>
        			</div>
        			<div class="form_btn_right">
        				<a class="mini-button mini-button-iconRight" iconCls="icon-reload" onclick="onRestForm" ><fmt:message key="global.reset"/></a>
        			</div>
        		</div>
        	</div>
        </div>
    </form>
    <input name="formCode" id="formCode" value="${requestScope.formCode}" class="mini-hidden" />
    <script type="text/javascript">
        mini.parse();
        var form = new mini.Form("entityForm");
        function saveData() {
            var data = form.getData(true,false);            
            form.validate();
            if (form.isValid() == false) return;
            data.formCode=mini.get("formCode").getValue();
            var messageId = mini.loading('<fmt:message key="global.loading"/>','<fmt:message key="global.prompt"/>');
            $.ajax({
                url: '<c:url  value="/data/constant/add.action" />',
				type: 'post',
                data: data,
                cache: false,
                dataType:'json',
                success: function (data) {
                	 mini.get("formCode").setValue(data.formCode);
                	 mini.hideMessageBox(messageId);
                	 mini.showMessageBox({
                         title: "提示",
                         iconCls: "mini-messagebox-info",
                         buttons: ["ok"],
                         message: data.message,
                         callback: function (action) {
                        	 if(data.flag){
                         		reset(form);
                         		closeWindow();
                         	}
                         }
                     });
                },
                error: function (jqXHR, textStatus, errorThrown) {
                	mini.showMessageBox({
                         title: '<fmt:message key="global.prompt"/>',
                         iconCls: "mini-messagebox-info",
                         buttons: ["ok"],
                         message: jqXHR.responseText,
                         callback: function (action) {
                         		closeWindow();
                         }
                     });
                }
            });
        }
		function onRestForm(){
			reset(form);
		}
		 ///////////////////////////////////////////
	     mini.VTypes["remoteFieldIsExistsErrorText"] = '<fmt:message key="global.message.property.exists"/>';
	     mini.VTypes["remoteFieldIsExists"] = function (v,n) {
	          var flag = false;
	          $.ajax({
	          	async: false,/*同步请求*/
	          	url: '<c:url value="/data/constant/fieldIsExists.action"/>',
	          	type:'post',
	          	data: {"propertyName":n[0],"propertyValue":v},
	          	success: function(data) {
	          		flag = !(data.flag);
	          	},
	          	dataType:'json'
	          });
	          return flag;
	    };
        function onValueChanged(e) {
        	var t = mini.get("flush");
            var checked = this.getChecked();
            if(checked){
            	 t.enable();
            }else{
            	t.disable();
            }
        }
    </script>
  </body>
</html>
