<div  class="from_item after">
	<label class="common_label">${displayName}:</label>
	<div class="form_item_middle">
		 <input id="${fieldName}" 
		 		name="${fieldName}"
		 		class="${inputType} m-textbox-common" 
		 		valueField="code" 
		 		textField="name"
		 		showNullItem="true" 
		 		emptyText="请选择${displayName}"
				nullItemText="<fmt:message key="global.message.form.combobox.nullItemText"/>"
			    errorMode="border"
			    url="<c:url value="/system/param/queryParamsBySerial?serial=${serial}"/>"
			    allowInput="false" />
	</div>
</div>