<div  class="from_item after" >
	<label class="common_label">${displayName}:</label>
	<div class="form_item_middle">
		 <input id="${fieldName}" name="${fieldName}" 
		 errorMode="border"
		 class="${inputType} m-textbox-common" 
		 emptyText="请输入${displayName}"
		 minValue="${minValue?c}" 
		 maxValue="${maxValue?c}"
		 value="${defaultValue?c}"
		 vtype="${vtypes!}"
		 ${errorMessages!}
		 />
	</div>
</div>
	   	 