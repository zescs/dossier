<#if interval>
<div  class="from_item after">
	<label class="common_label">${displayName}:</label>
	<div class="form_item_middle">
		 <input id="${startName}" name="${startName}" 
			 class="${inputType} m-textbox-common" 
			 emptyText="请选择${displayName}"
			 errorMode="border"
			 format="${dateFormat}"
			 showTime="true" 
			 showClearButton="false"
			 />
	</div>
</div>
<div  class="from_item after">
	<label class="common_label">到:</label>
</div>
<div  class="from_item after">
	<label class="common_label">${displayName}:</label>
	<div class="form_item_middle">
		 <input id="${endName}" name="${endName}" 
			 class="${inputType} m-textbox-common" 
			 emptyText="请选择${displayName}"
			 errorMode="border"
			 format="${dateFormat}"
			 showTime="true" 
			 showClearButton="false"
			 />
	</div>
</div>
<#else>
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
			 />
	</div>
</div>
</#if>