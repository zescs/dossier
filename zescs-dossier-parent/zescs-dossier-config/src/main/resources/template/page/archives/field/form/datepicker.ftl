 <div  class="from_item after">
	<label class="common_label">${displayName}:</label>
	<div class="form_item_middle">
		 <input id="${fieldName}" name="${fieldName}" 
			 class="${inputType} m-textbox-common" 
			 emptyText="请选择${displayName}"
			 errorMode="border"
			 format="${dateFormat}" 
			 showTime="true" 
			 showClearButton="false"
			 required="${required?string('true', 'false')}"
			 />
	</div>
</div>