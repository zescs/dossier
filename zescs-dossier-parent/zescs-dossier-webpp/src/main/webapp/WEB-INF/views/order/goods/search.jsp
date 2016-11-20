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
			<div  class="from_item after">
				<label class="common_label"><fmt:message key="archives.borrowinfo.name"/>:</label>
				<div class="form_item_middle">
					<input id="name" 
		   			 	 name="name" 
						 class="mini-textbox m-textbox-common" 
						 emptyText="<fmt:message key="archives.borrowinfo.name.message.emptyText"/>"
						 vtype="maxLength:64;"
						 errorMode="border"
						 maxLengthErrorText="<fmt:message key="global.message.maxLengthErrorText"/>" 
					 />
				</div>
			</div>
	        <div class="from_item after">
				<label class="common_label"><fmt:message key="archives.borrowinfo.phone"/>:</label>
				<div class="form_item_middle">
					<input id="phone" 
		   			 	 name="phone" 
						 class="mini-textbox m-textbox-common" 
						 emptyText="<fmt:message key="archives.borrowinfo.phone.message.emptyText"/>"
						 vtype="maxLength:18;"
						 errorMode="border"
						 maxLengthErrorText="<fmt:message key="global.message.maxLengthErrorText"/>" 
					 />
				</div>
			</div>
	        <div class="from_item after">
				<label class="common_label"><fmt:message key="archives.borrowinfo.unit"/>:</label>
				<div class="form_item_middle">
					<input id="unit" 
		   			 	 name="unit" 
						 class="mini-textbox m-textbox-common" 
						 emptyText="<fmt:message key="archives.borrowinfo.unit.message.emptyText"/>"
						 vtype="maxLength:64;"
						 errorMode="border"
						 maxLengthErrorText="<fmt:message key="global.message.maxLengthErrorText"/>" 
					 />
				</div>
			</div>
		  	<div class="from_item after">
				<label class="common_label"><fmt:message key="archives.borrowinfo.address"/>:</label>
				<div class="form_item_middle">
					<input id="address" 
		   			 	 name="address" 
						 class="mini-textbox m-textbox-common" 
						 emptyText="<fmt:message key="archives.borrowinfo.address.message.emptyText"/>"
						 vtype="maxLength:128;"
						 errorMode="border"
						 maxLengthErrorText="<fmt:message key="global.message.maxLengthErrorText"/>" 
					 />
				</div>
			</div>
		  	<div class="from_item after">
				<label class="common_label"><fmt:message key="archives.borrowinfo.IDCard"/>:</label>
				<div class="form_item_middle">
					<input id="idCard" 
		   			 	 name="idCard" 
						 class="mini-textbox m-textbox-common" 
						 emptyText="<fmt:message key="archives.borrowinfo.IDCard.message.emptyText"/>"
						 vtype="maxLength:18;"
						 errorMode="border"
						 maxLengthErrorText="<fmt:message key="global.message.maxLengthErrorText"/>" 
					 />
				</div>
			</div>
		  	<div class="from_item after">
				<label class="common_label"><fmt:message key="archives.borrowinfo.purpose"/>:</label>
				<div class="form_item_middle">
					<input id="purpose" 
		   			 	 name="purpose" 
						 class="mini-textbox m-textbox-common" 
						 emptyText="<fmt:message key="archives.borrowinfo.purpose.message.emptyText"/>"
						 vtype="maxLength:220;"
						 errorMode="border"
						 maxLengthErrorText="<fmt:message key="global.message.maxLengthErrorText"/>" 
					 />
				</div>
			</div>
		  	<div class="from_item after">
				<label class="common_label"><fmt:message key="archives.borrowinfo.principal"/>:</label>
				<div class="form_item_middle">
					<input id="principal" 
		   			 	 name="principal" 
						 class="mini-textbox m-textbox-common" 
						 emptyText="<fmt:message key="archives.borrowinfo.principal.message.emptyText"/>"
						 vtype="maxLength:32;"
						 errorMode="border"
						 maxLengthErrorText="<fmt:message key="global.message.maxLengthErrorText"/>" 
					 />
				</div>
			</div>
		  	<div class="from_item after">
				<label class="common_label"><fmt:message key="archives.borrowinfo.director"/>:</label>
				<div class="form_item_middle">
					<input id="director" 
		   			 	 name="director" 
						 class="mini-textbox m-textbox-common" 
						 emptyText="<fmt:message key="archives.borrowinfo.director.message.emptyText"/>"
						 vtype="maxLength:32;"
						 errorMode="border"
						 maxLengthErrorText="<fmt:message key="global.message.maxLengthErrorText"/>" 
					 />
				</div>
			</div>
		  	<div class="from_item after">
				<label class="common_label"><fmt:message key="archives.borrowinfo.opinion"/>:</label>
				<div class="form_item_middle">
					<input id="opinion" 
		   			 	 name="opinion" 
						 class="mini-textbox m-textbox-common" 
						 emptyText="<fmt:message key="archives.borrowinfo.opinion.message.emptyText"/>"
						 vtype="maxLength:200;"
						 errorMode="border"
						 maxLengthErrorText="<fmt:message key="global.message.maxLengthErrorText"/>" 
					 />
				</div>
			</div>
			<div class="from_item after">
        		<label class="common_label"><fmt:message key="archives.borrowinfo.returnStatus"/>:</label>
				<div class="form_item_middle">
					 <input id="returnStatus" 
					 		name="returnStatus"
					 		class="mini-combobox m-textbox-common" 
					 		textField="text"
					 		valueField="id" 
					 		showNullItem="true" 
					 		emptyText="<fmt:message key="global.message.form.combobox.emptyText"/>"
							nullItemText="<fmt:message key="global.message.form.combobox.nullItemText"/>"
						    errorMode="border"
						    data='${returnStatus}'
						    allowInput="flse" />
				</div>
            </div>
			<div  class="from_item after">
        		<label class="common_label"><fmt:message key="archives.borrowinfo.returnDate"/>:</label>
        		<div class="form_item_middle">
        			 <input id="returnDate" name="returnDate" 
						 class="mini-datepicker m-textbox-common" 
						 emptyText="<fmt:message key="archives.borrowinfo.returnDate.message.emptyText"/>"
						 errorMode="border"
						 format="yyyy-MM-dd" 
						 showTime="true" 
						 showClearButton="false"
						 />
        		</div>
	        </div>
			<div  class="from_item after">
        		<label class="common_label"><fmt:message key="archives.borrowinfo.borrowDate"/>:</label>
        		<div class="form_item_middle">
        			 <input id="borrowDate" name="borrowDate" 
						 class="mini-datepicker m-textbox-common" 
						 emptyText="<fmt:message key="archives.borrowinfo.borrowDate.message.emptyText"/>"
						 errorMode="border"
						 format="yyyy-MM-dd" 
						 showTime="true" 
						 showClearButton="false"
						 />
        		</div>
	        </div>
			<!-- item-end -->
			<div class="from_btn after">
	      		<label class="common_label"></label>
	      		<div class="from_btn_middle">
	      			<div class="form_btn_left">
	      				<a class="mini-button" iconCls="icon-ok" onclick="onOk()"><fmt:message key="global.message.ok"/></a>
	      			</div>
	      			<div class="form_btn_right">
	      				<a class="mini-button mini-button-iconRight" iconCls="icon-reload" onclick="onRestForm" ><fmt:message key="global.clear"/></a>
	      			</div>
	      		</div>
	        </div>
	     </div>
	</form>
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
	</script>
</body>
</html>