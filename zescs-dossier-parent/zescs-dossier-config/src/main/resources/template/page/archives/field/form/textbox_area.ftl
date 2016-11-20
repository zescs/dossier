<div class="from_item after">
	<label class="common_label">${displayName}:</label>
	<div class="form_item_middle">
		 <input id="${fieldName}" name="${fieldName}" 
			 class="${inputType}  m-textarea-common" 
			 errorMode="border"
			 emptyText="请输入${displayName}"
			 vtype="maxLength:${maxLength?c};minLength:${minLength?c};${vtypes!}"
			 style="height:60px;"
			 maxLengthErrorText="<fmt:message key="global.message.maxLengthErrorText"/>"
			 minLengthErrorText="<fmt:message key="global.message.minLengthErrorText"/>"
			 ${errorMessages!}
			/>
	</div>
</div>