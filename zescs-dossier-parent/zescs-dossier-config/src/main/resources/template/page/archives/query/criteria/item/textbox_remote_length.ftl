<div class="from_item after">
	<label class="common_label">${displayName}:</label>
	<div class="form_item_middle">
		 <input id="${fieldName}" name="${fieldName}" 
			 class="${inputType}  m-textbox-common" 
			 errorMode="border"
			 emptyText="请输入${displayName}"
			 vtype="maxLength:${fixedLength?c};remoteLength:${fieldName},${fixedLength?c}"
			 maxLengthErrorText="<fmt:message key="global.message.maxLengthErrorText"/>"
			/>
	</div>
</div>