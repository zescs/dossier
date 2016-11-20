<div class="from_item after">
	<label class="common_textarea">${displayName}:</label>
	<div class="form_item_middle">
		 <input id="${fieldName}" name="${fieldName}" 
			 class="${inputType}  m-textarea-common" 
			 errorMode="border"
			 emptyText="请输入${displayName}"
			 vtype="maxLength:${maxLength?c};minLength:${minLength?c};${vtypes!}"
			 ${errorMessages!}
			 style="height:118px;"
			 maxLengthErrorText="<fmt:message key="global.message.maxLengthErrorText"/>"
			 minLengthErrorText="<fmt:message key="global.message.minLengthErrorText"/>"
			/>
	</div>
</div>