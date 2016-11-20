<div  class="from_item after">
	<label class="common_label">${displayName}:</label>
	<div class="form_item_middle">
		 <div id="${fieldName}" 
		 		name="${fieldName}"
		 		class="${inputType} m-textbox-common" 
		 		valueField="code" 
		 		textField="name"
		 		emptyText="请选择${displayName}"
			    errorMode="border"
			    showClose="true"
		 		multiSelect="false"
		 		oncloseclick="onComboCloseClick"
		 		popupWidth="200"
			    url="<c:url value="/system/param/queryParamsBySerial"/>?serial=${serial}"
			    allowInput="true">
			    <div property="columns">
			        <div header="<fmt:message key="system.param.code"/>" field="code" width="80" align="center" headerAlign="center"></div>
			        <div header="<fmt:message key="system.param.name"/>" field="name" width="100" align="center" headerAlign="center"></div>
			    </div>
		  </div>
	</div>
</div>