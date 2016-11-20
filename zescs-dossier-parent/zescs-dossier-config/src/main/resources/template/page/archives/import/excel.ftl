<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <script type="text/javascript" src="<c:url value="/resources/scripts/boot.js" />"></script>
  <link href="<c:url value="/resources/css/layout.css" />" rel="stylesheet" type="text/css" />
  <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet" type="text/css" />
  <script language="javascript">
	function challs_flash_update(){ //Flash 初始化函数
	var a={};
	//定义变量为Object 类型
	a.title = "<fmt:message key='global.message.data.import'/>"; //设置组件头部名称
	a.FormName = "file";
	//设置Form表单的文本域的Name属性
	a.url = '<c:url value="/archives/entry/import/excel.action" />'; 
	//设置服务器接收代码文件
	a.parameter = "archivesId=${r'${archivesId}'}"; 
     a.typefile = ["File (*.xls,*.xlsx)", "*.xls,*.xlsx"];
	//设置可以上传文件 数组类型
	//"Images (*.gif,*.png,*.jpg)"为用户选择要上载的文件时可以看到的描述字符串,
	//"*.gif;*.png;*.jpg"为文件扩展名列表，其中列出用户选择要上载的文件时可以看到的 Windows 文件格式，以分号相隔
	//2个为一组，可以设置多组文件类型
	a.newTypeFile =["File (*.xls,*.xlsx)", "*.xls,*.xlsx"];
	//设置可以上传文件，多了一个苹果电脑文件类型过滤 数组类型, 设置了此项，typefile将无效
	//"Images (*.gif,*.png,*.jpg)"为用户选择要上载的文件时可以看到的描述字符串,
	//"*.gif;*.png;*.jpg"为文件扩展名列表，其中列出用户选择要上载的文件时可以看到的 Windows 文件格式，以分号相隔
	//"JPE;JPEG;JPG;GIF;PNG" 分号分隔的 Macintosh 文件类型列表，如下面的字符串所示："JPEG;jp2_;GI
	a.UpSize = 0;
	//可限制传输文件总容量，0或负数为不限制，单位MB
	a.fileNum = 5;
	//可限制待传文件的数量，0或负数为不限制
	a.size =10;
	//上传单个文件限制大小，单位MB，可以填写小数类型
	a.FormID = ['select','select2'];
	//设置每次上传时将注册了ID的表单数据以POST形式发送到服务器
	//需要设置的FORM表单中checkbox,text,textarea,radio,select项目的ID值,radio组只需要一个设置ID即可
	//参数为数组类型，注意使用此参数必须有 challs_flash_FormData() 函数支持
	
	a.autoClose = 1;
	//上传完成条目，将自动删除已完成的条目，值为延迟时间，以秒为单位，当值为 -1 时不会自动关闭，注意：当参数CompleteClose为false时无效
	
	a.CompleteClose = true;
	//设置为true时，上传完成的条目，将也可以取消删除条目，这样参数 UpSize 将失效, 默认为false
	
	a.repeatFile = true;
	//设置为true时，可以过滤用户已经选择的重复文件，否则可以让用户多次选择上传同一个文件，默认为false
	
	a.returnServer = true;
	//设置为true时，组件必须等到服务器有反馈值了才会进行下一个步骤，否则不会等待服务器返回值，直接进行下一步骤，默认为false
	
	a.MD5File = 1;
	//设置MD5文件签名模式，参数如下 ,注意：FLASH无法计算超过100M的文件,在无特殊需要时，请设置为0
	//0为关闭MD5计算签名
	//1为直接计算MD5签名后上传
	//2为计算签名，将签名提交服务器验证，在根据服务器反馈来执行上传或不上传
	//3为先提交文件基本信息，根据服务器反馈，执行MD5签名计算或直接上传，如果是要进行MD5计算，计算后，提交计算结果，在根据服务器反馈，来执行是否上传或不上传
	
	a.loadFileOrder=true;
	//选择的文件加载文件列表顺序，TRUE = 正序加载，FALSE = 倒序加载
	
	a.mixFileNum=0;
	//至少选择的文件数量，设置这个将限制文件列表最少正常数量（包括等待上传和已经上传）为设置的数量，才能点击上传，0为不限制
	
	a.ListShowType = 1;
	//文件列表显示类型：
	//1 = 传统列表显示，
	//2 = 缩略图列表显示（适用于图片专用上传）
	//3 = 单列模式
	//4 = MP3播放模式（适用于MP3专用上传）
	//5 = 极简模式
	
	
	a.TitleSwitch = true;
	//是否显示组件头部
	
	a.ForceFileNum = 0;
	//强制条目数量，已上传和待上传条目相加等于为设置的值（不包括上传失败的条目），否则不让上传, 0为不限制，设置限制后mixFileNum,autoClose和fileNum属性将无效！
	
	a.autoUpload = false;
	//设置为true时，用户选择文件后，直接开始上传，无需点击上传，默认为false;
	
	a.adjustOrder = true;
	//设置为true时，用户可以拖动列表，重新排列位置
	
	a.deleteAllShow = true
	//设置是否显示，全部清除按钮
	
	a.countData = true;
	//是否向服务器端提交组件文件列表统计信息，POST方式提交数据
	//access2008_box_info_max 列表总数量
	//access2008_box_info_upload 剩余数量 （包括当前上传条目）
	//access2008_box_info_over 已经上传完成数量 （不包括当前上传条目)
	
	a.isShowUploadButton = true;
	//是否显示上传按钮，默认为true

	a.isRotation = true;
	//是否可旋转图片
	//此项只有在缩略图模式下才有用
	//开启此项会POST一个图片角度到服务器端，由服务器端旋转图片
	//access2008_image_rotation 角度  0 到 -360 
	
	a.isUploadRate = true;
	//是否显示上传速率

	a.requireMpegBitrates = 0;
	//MP3播放模式下有效
	//MP3频率要求，0为不限制
	//频率与值,例子
	//128kbps = 128
	//320kbps = 320
	//低于要求将报错，不予上传

	a.mpegBitratesError = "mpegBitrates Error";
	//MP3播放模式下有效
	//a.requireMpegBitrates 不为0 时
	//报错显示文本设置

	a.isErrorStop = true;
	//遇见错误时，是否停止上传，如果为false时，忽略错误进入下一个上传

	a.isReadCookie = false;
	//是否读取cookie值，POST到服务器端
	//需要增加了challs_flash_cookies() JS函数支持
	//POST到服务器端，值名格式为 access2008_cookie_<cookie值名称>
	//比如 cookie名称为 aaa ,post 服务器 名称为 access2008_cookie_aaa
	a.isGIFAnimation = false;
	//缩略图模式下，支持GIF动画显示
	//a.ListShowType = 2 时有效
	return a ;
	//返回Object
}
function challs_flash_onStatistics(a){ //当组件文件数量或状态改变时得到数量统计，参数 a 对象类型
	var uploadFile = a.uploadFile; //等待上传数量
	var overFile = a.overFile; //已经上传数量
	var errFile = a.errFile; //上传错误数量
}
function challs_flash_FormData(a){ // 使用FormID参数时必要函数
	try{
		var value = '';
		var id=document.getElementById(a);
		if(id.type == 'radio'){
			var name = document.getElementsByName(id.name);
			for(var i = 0;i<name.length;i++){
				if(name[i].checked){
					value = name[i].value;
				}
			}
		}else if(id.type == 'checkbox'){
			var name = document.getElementsByName(id.name);
			for(var i = 0;i<name.length;i++){
				if(name[i].checked){
					if(i>0) value+=",";
					value += name[i].value;
				}
			}
		}else if(id.type == 'select-multiple'){
		    for(var i=0;i<id.length;i++){
		        if(id.options[i].selected){
					if(i>0) value+=",";
			         values += id.options[i].value; 
			    }
		    }
		}else{
			value = id.value;
		}
		return value;
	 }catch(e){
		return '';
	 }
}
function challs_flash_cookies(){ //获取cookie
	var postParams = {};
	var i, cookieArray = document.cookie.split(';'), caLength = cookieArray.length, c, eqIndex, name, value;
	for (i = 0; i < caLength; i++) {
		c = cookieArray[i];
		// Left Trim spaces
		while (c.charAt(0) === " ") {
			c = c.substring(1, c.length);
		}
		eqIndex = c.indexOf("=");
		if (eqIndex > 0) {
			name = c.substring(0, eqIndex);
			value = c.substring(eqIndex + 1);
			postParams[name] = value;
		}
	}
	return postParams;
}

function challs_flash_style(){ //组件颜色样式设置函数
	var a = {};
	/*  整体背景颜色样式 */
	a.backgroundColor=['#f6f6f6','#f3f8fd','#dbe5f1'];	//颜色设置，3个颜色之间过度
	a.backgroundLineColor='#5576b8';					//组件外边框线颜色
	a.backgroundFontColor='#066AD1';					//组件最下面的文字颜色
	a.backgroundInsideColor='#FFFFFF';					//组件内框背景颜色
	a.backgroundInsideLineColor=['#e5edf5','#34629e'];	//组件内框线颜色，2个颜色之间过度
	/*  头部颜色样式 */
	a.Top_backgroundColor=['#e0eaf4','#bcd1ea']; 		//颜色设置，数组类型，2个颜色之间过度
	a.Top_fontColor='#245891';							//头部文字颜色
	/*  按钮颜色样式 */
	a.button_overColor=['#FBDAB5','#f3840d'];			//鼠标移上去时的背景颜色，2个颜色之间过度
	a.button_overLineColor='#e77702';					//鼠标移上去时的边框颜色
	a.button_overFontColor='#ffffff';					//鼠标移上去时的文字颜色
	a.button_outColor=['#ffffff','#dde8fe']; 			//鼠标离开时的背景颜色，2个颜色之间过度
	a.button_outLineColor='#91bdef';					//鼠标离开时的边框颜色
	a.button_outFontColor='#245891';					//鼠标离开时的文字颜色
	/* 滚动条样式 */	
	a.List_scrollBarColor=0x000000;				//滚动条颜色
	a.List_scrollBarGlowColor=0x34629e;			//滚动条阴影颜色

	/* 文件列表样式 */
	a.List_backgroundColor='#EAF0F8';					//列表背景色
	a.List_fontColor='#333333';						//列表文字颜色
	a.List_errFontColor='#ff0000';							//列表错误信息文字颜色
	a.List_LineColor='#B3CDF1';							//列表分割线颜色
	a.List_cancelOverFontColor='#ff0000';				//列表取消文字移上去时颜色
	a.List_cancelOutFontColor='#D76500';				//列表取消文字离开时颜色
	a.List_progressBarLineColor='#B3CDF1';				//进度条边框线颜色
	a.List_progressBarBackgroundColor='#D8E6F7';		//进度条背景颜色	
	a.List_progressBarColor=['#FFCC00','#FFFF00'];		//进度条进度颜色，2个颜色之间过度
	/* 错误提示框样式 */
	a.Err_backgroundColor='#C0D3EB';					//提示框背景色
	a.Err_fontColor='#245891';							//提示框文字颜色
	a.Err_shadowColor='#000000';						//提示框阴影颜色
	return a;
}
function challs_flash_language(){ //组件文字设置函数
	var a = {
		// $[1]$ $[2]$ $[3]$是替换符号 
		// \n 是换行符号
		//按钮文字
		ButtonTxt_1:'停 止',
		ButtonTxt_2:'选择文件',
		ButtonTxt_3:'上 传',
		ButtonTxt_4:'清空',
		
		//全局文字设置
		Font:'宋体',
		FontSize:12,
		
		//提示文字
		Alert_1:'初始化错误：\n\n没有找到 JAVASCRITP 函数 \n函数名为 challs_flash_update()',
		Alert_2:'初始化错误：\n\n函数 challs_flash_update() 返回类型必须是 "Object" 类型',
		Alert_3:'初始化错误：\n\n没有设置上传路径地址',
		Alert_4:'添加上传文件失败，\n\n不可以在添加更多的上传文件!',
		Alert_5:'添加上传文件失败，\n\n等待上传文件列表只能有$[1]$个，\n请先上传部分文件!',
		Alert_6:'提示信息：\n\n请再选择$[1]$个上传文件！',
		Alert_7:'提示信息：\n\n请至少再选择$[1]$个上传文件！',
		Alert_8:'\n\n请选择上传文件！',
		Alert_9:'上传错误：\n\n$[1]$',

		//界面文字
		Txt_5:'等待上传',
		Txt_6:'等待上传：$[1]$个  已上传：$[2]$个',
		Txt_7:'字节',
		Txt_8:'总量限制（$[1]$MB）,上传失败',
		Txt_9:'文件超过$[1]$MB,上传失败',
		Txt_10:'秒',
		Txt_11:'保存数据中...',
		Txt_12:'上传完毕',
		Txt_13:'文件加载错误',
		Txt_14:'扫描文件...',
		Txt_15:'验证文件...',
		Txt_16:'取消',
		Txt_17:'无图',
		Txt_18:'加载中',

		Txt_20:'关闭',
		Txt_21:'确定',
		Txt_22:'上传文件',
		
		//错误提示
		Err_1:'上传地址URL无效',
		Err_2:'服务器报错：$[1]$',
		Err_3:'上传失败,$[1]$',
		Err_4:'服务器提交效验错误',
		Err_5:'效验数据无效错误'
	};
	return a;
}

var isMSIE = (navigator.appName == "Microsoft Internet Explorer");   
function thisMovie(movieName){   
  if(isMSIE){   
  	return window[movieName];   
  }else{
  	return document[movieName];   
  }   
}
  </script>
</head>
  <body>
  	<form id="configForm" method="post" style="text-align: center;padding:10px 3px 3px 3px;">
		<object classid="clsid:d27cdb6e-ae6d-11cf-96b8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=11,0,0,0" width="442" height="330" id="update" align="middle">
		<param name="allowFullScreen" value="false" />
		    <param name="allowScriptAccess" value="always" />
			<param name="movie" value="<c:url value="/resources/scripts/CFUpdate/update.swf" />" />
		    <param name="quality" value="high" />
		    <param name="bgcolor" value="#ffffff" />
		    <embed src="<c:url value="/resources/scripts/CFUpdate/update.swf" />" quality="high" bgcolor="#ffffff" width="442" height="320" name="update" align="middle" allowScriptAccess="always" allowFullScreen="false" type="application/x-shockwave-flash" pluginspage="http://www.macromedia.com/go/getflashplayer" />
		</object>
	</form>
	 <script type="text/javascript">
        mini.parse();
    </script>
</body>
</html>
