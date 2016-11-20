<#if interval>
<div class="from_item after">
	<label class="common_label">${displayName}:</label>
	<div class="form_item_middle">
		 <input id="${startName}" name="${startName}" 
			 class="${inputType}  m-textbox-common" 
			 errorMode="border"
			 emptyText="请输入${displayName}"
			 vtype="maxLength:${maxLength?c}"
			 maxLengthErrorText="<fmt:message key="global.message.maxLengthErrorText"/>"
			/>
	</div>
</div>
<div class="from_item after">
	<label class="common_textarea">到</label>
	<div class="form_item_middle">
	</div>
</div>
<div class="from_item after">
	<label class="common_label">${displayName}:</label>
	<div class="form_item_middle">
		 <input id="${endName}" name="${endName}" 
			 class="${inputType}  m-textbox-common" 
			 errorMode="border"
			 emptyText="请输入${displayName}"
			 vtype="maxLength:${maxLength?c}"
			 maxLengthErrorText="<fmt:message key="global.message.maxLengthErrorText"/>"
			/>
	</div>
</div>
<#else>
<div class="from_item after">
	<label class="common_label">${displayName}:</label>
	<div class="form_item_middle">
		 <input id="${fieldName}" name="${fieldName}" 
			 class="${inputType}  m-textbox-common" 
			 errorMode="border"
			 emptyText="请输入${displayName}"
			 vtype="maxLength:${maxLength?c}"
			 maxLengthErrorText="<fmt:message key="global.message.maxLengthErrorText"/>"
			/>
	</div>
</div>
</#if> 