<div type="comboboxcolumn" field="${fieldName}" width="${width}"  headerAlign="${headerAlign}" align="${contentAlign}" allowSort="${sort?string('true', 'false')}">${displayName}
	<input property="editor" class="mini-combobox" style="width:100%;" url="<c:url value="/system/param/queryParamsBySerial?serial=${serial}" />" valueField="code" textField="name"/>                
</div>