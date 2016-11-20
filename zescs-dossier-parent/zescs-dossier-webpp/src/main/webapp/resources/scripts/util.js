/**
 * 
 */
function labelModel(form) {
    var fields = form.getFields();                
    for (var i = 0, l = fields.length; i < l; i++) {
        var c = fields[i];
        if (c.setReadOnly) c.setReadOnly(true);     //只读
        if (c.setIsValid) c.setIsValid(true);      //去除错误提示
        if (c.addCls) c.addCls("asLabel");          //增加asLabel外观
    }
}
function closeWindow(action) {
	if (window.CloseOwnerWindow) {
		if (!action) {
			action = "";
		}
		return window.CloseOwnerWindow(action);
	} else
		window.close();
}
function reset(form) {
	form.reset();
	var fs = form.getFields();
	for (var i = 0; i < fs.length; i++) {
		var id = fs[i].name + "_error";
		var el = document.getElementById(id);
		if (el) {
			el.className = '';
			el.innerHTML = '';
		}
	}
}
function setFromData(form, data, state) {
	form.setData(data);
	form.setChanged(state);
}
// 启用或禁用
function setEnabled(form, flag) {
	var fs = form.getFields();
	for (var i = 0; i < fs.length; i++) {
		var field = fs[i];
		if (field.name != 'file') {
			field.setEnabled(flag);
		}
	}
	editor.readonly(!flag);
}

// 文件上传进度
function showUploadProgressBar(url) {
	// 新建一个div对象
	var div = $("<div />", {
		'class' : 'initProgress'
	});
	div.appendTo(progressBar);
	var r = setInterval(function() {
		$.getJSON(url, function(data) {
			if ($.isEmptyObject(data) || data.fileProgress == '100.00%') {
				clearInterval(r);
			}
			div.html(data.fileProgress);
			div.animate({
				width : data.fileProgress
			}, 160);
		});
	}, 170);
}

function trim(str) {
	if(str!=null && str!="" && str.lenth>0){
		return str.replace(/(^\s*)|(\s*$)/g, "");
	}
	return null;
}
function loaderror(){
	mini.alert("数据加载错误");
}

function getFileSize(bytes) {  
    if (bytes === 0) return '0 B';  
     var k = 1024;  
     sizes = ['B','KB', 'MB', 'GB', 'TB', 'PB', 'EB', 'ZB', 'YB'];  
     i = Math.floor(Math.log(bytes) / Math.log(k));  
     return (bytes / Math.pow(k, i)).toPrecision(3) + ' ' + sizes[i];                                                                                 
}

function appError(jqXHR, textStatus, errorThrown,messageId,upFormCode) {
  try{
		var data =jqXHR.responseJSON.error;
	  	if(messageId){
	  		mini.hideMessageBox(messageId);
	  	}
	  	if(upFormCode){
	  		mini.get("formCode").setValue(data.formCode);
	  	}
	    mini.showMessageBox({
	        width: 250,
	        title: '提示',
	        buttons: ['ok'],
	        message: data.message,
	        iconCls: 'mini-messagebox-info'
	    });  
  }catch(e){
	  mini.showMessageBox({
	        width: 250,
	        title: '提示',
	        buttons: ['ok'],
	        message:'<fmt:message key="global.message.operation.error"/>',
	        iconCls: 'mini-messagebox-info'
	    }); 
  }
 }