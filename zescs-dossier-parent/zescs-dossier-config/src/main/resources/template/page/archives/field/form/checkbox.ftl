<div class="from_item after">
	<label class="common_label"></label>
	<div class="form_item_middle">
		<div id="${fieldName}" name="${fieldName}" class="mini-checkbox" readOnly="false" <#if defaultCheckvalue?exists>checked="${defaultCheckvalue?string('true', 'false')}"</#if>  text="${displayName}" ></div>
	</div>
</div>