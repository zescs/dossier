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
   <form id="configForm" method="post">
        <div id="from_con">
        	<div class="from_item after">
        		<input name="id" id="id" class="mini-hidden" />
        		<label class="common_label"><fmt:message key="systemConfig.sysKey"/>:</label>
        		<div class="form_item_middle">
        			 <input id="sysKey" name="sysKey" 
						 class="mini-textbox m-textbox-common" 
						 emptyText="<fmt:message key="systemConfig.sysKey.emptyText"/>"
						 vtype="maxLength:32;required;remote:sysKey" 
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
        			<div id="enableState" name="enableState" class="mini-checkbox" checked="true" readOnly="false" text="<fmt:message key="systemConfig.enableState"/>" onvaluechanged="onValueChanged"></div>
        		</div>
        	</div>
        	<div class="from_item after">
        		<label class="common_label"></label>
        		<div class="form_item_middle">
        			<div id="flush" name="flush" class="mini-checkbox" checked="true" readOnly="false" text="<fmt:message key="systemConfig.flush"/>"></div>
        		</div>
        	</div>
        	<div class="from_item after">
        		<label class="common_textarea"><fmt:message key="systemConfig.remark"/>:</label>
        		<div class="form_item_middle">
        			 <input id="remark" 
        			 	 name="remark" 
						 class="mini-textarea" 
						 errorMode="border"
						 emptyText="<fmt:message key="systemConfig.remark.emptyText"/>"
						 style="width:180px;height:120px;"
						 borderStyle="border-radius:5px;height:118px;"
						 inputStyle="height:118px;padding:0px 3px;"
						 />
        		</div>
        	</div>
        	<div class="from_btn after">
        		<label class="common_label"></label>
        		<div class="from_btn_middle">
        			<div class="form_btn_left">
        				<a class="mini-button" iconCls="icon-edit" onclick="saveData()"><fmt:message key="global.edit"/></a>
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
        var form = new mini.Form("configForm");
        var enableState = mini.get("enableState");
        var flush = mini.get("flush");
        var	tempData={};
        function init(id){
        	$.ajax({
                url: '<c:url value="/system/config/getEditById"/>',
                type:'post',
                data: {id:id},
                success: function (data) {
                	tempData=data;
                	setFromData(form,data,false);
                },
                dataType:'json'
            });
        }
        //是否禁用
        if(enableState.checked){
        	flush.enable();
        }else{
        	flush.disable();
        }
        function saveData() {
            var data = form.getData(true,false);            
            form.validate();
            if (form.isValid() == false) return;
            data.formCode=mini.get("formCode").getValue();
            var messageId = mini.loading('<fmt:message key="global.loading"/>','<fmt:message key="global.prompt"/>');
            data["_method"]="put";
            $.ajax({
                url: '<c:url  value="/system/config/edit" />',
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
			setFromData(form,tempData,false);
		}
		 /*自定义vtype*/
        mini.VTypes["remoteErrorText"] = '<fmt:message key="global.property.exists"/>';
        mini.VTypes["remote"] = function (v,n) {
            var flag = false;
            $.ajax({
            	async: false,/*同步请求*/
            	url: '<c:url value="/system/config/sysKeyIsUsed"/>',
            	type:'post',
            	data: {"sysKey":mini.get('sysKey').getValue(),"id":mini.get('id').getValue()},
            	success: function(data) {
            		flag = data.flag;
            	},
            	dataType:'json'
            });
            return flag;
        };
        function onValueChanged(e) {
            var checked = this.getChecked();
            if(checked){
            	flush.enable();
            }else{
            	flush.disable();
            }
        }
    </script>
  </body>
</html>
