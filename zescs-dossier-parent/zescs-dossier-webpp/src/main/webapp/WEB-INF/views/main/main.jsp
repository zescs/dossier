<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title><fmt:message key="system.main.title"/></title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta name="keywords" http-equiv="keywords" content="${search_keywords}" />
<meta name="description" http-equiv="description" content="${search_description}" />
<link rel="shortcut icon" href="<c:url value="/resources/images/favicon.ico" />" />
<link href="<c:url value="/resources/css/layout.css" />" rel="stylesheet" type="text/css" />
<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="<c:url value="/resources/css/main.css" />" type="text/css" />
<script type="text/javascript" src="<c:url value="/resources/scripts/boot.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/scripts/socket/sockjs.min.js" />"></script>
</head>
<body>
<!-- 框架布局 -->
<div class="mini-layout" style="width:100%;height:100%;">
    <div 
    	class="app-header" 
    	region="north"
    	bodyStyle="overflow:hidden;" 
    	height="70" 
    	style="background:#00a5a5"
    	showHeader="false" 
    	showSplit="false">
    	<div class="main_header">
	    	<!-- 网站头部 -->
	    	<div class="header-logo">
	    	
	    	</div>
	    	<div class="header-system">
	    		<div class="hreader-system-top">
	    		   <div class="header-userinfo" title="${loginUserAdmin.nickName} (${loginUserAdmin.userName})">
		    			<a href="javascript:void(0);" class="header-userinfo-image">
		    				<img width="28" height="28" class="" src="${server_path_url}${loginUserAdmin.avatarPath}">
		    			</a>
		    			<span class="header-userinfo-userName">
		    				<strong style="color:#ff0000;">${loginUserAdmin.nickName}</strong><span style="color:#ff7300;margin-left:3px;">(${loginUserAdmin.userName})</span>
		    			</span>
	    		    </div>
	    		</div>
	    		<div class="hreader-system-buttom after">
	    			<span id="main_time" class="main_show_time ellipsis"></span><a href="javascript:onExitClick(0);" class="main_logout_common" >[<fmt:message key="system.main.loginOut"/>]</a>
	    		</div>
	    	</div>
    	</div>
    </div>
    <!-- 版权声明 -->
    <div region="south" 
         showSplit="false" 
         showHeader="false"
         height="24"
         style="text-align:center;">
        <strong><span style="font-family:宋体;">Copyright&nbsp;&copy;&nbsp;2016&nbsp;<a href="#"><fmt:message key="system.main.copyright"/></a></span></strong>
    </div>
    <!-- 左侧功能菜单 -->
	<div region="west" 
		 title="<fmt:message key="layout.message.menu"/>" 
		 showHeader="true" 
		 showSplitIcon="true"
		 expanded="true"
		 bodyStyle="padding-top:2px;" 
		 width="230" 
		 minWidth="150"
		 maxWidth="350">
        <!-- 功能树 -->
        <div class="mini-fit">
	        <div id="leftTree" class="mini-tree"
				url="<c:url value="/permissions/menu/loadMenus.action" />"
				showTreeIcon="true" 
				idField="menuId" 
				textField="displayName"
				onnodeclick="onNodeClick" 
				onbeforeexpand="onBeforeExpand" 
				contextMenu="#treeMenu"
				expandOnDblClick="true"
				resultAsTree="false"
				>
			</div>
        </div>
	</div>
	<!-- 消息管理 -->
	<div region="east"  
		title="<fmt:message key="layout.message.info"/>" 
		expanded="false"
		showCloseButton="true" 
		showSplitIcon="true"
		width="200" 
		minWidth="150"
		maxWidth="350">
		<div id="chatTabs" 
        	class="mini-tabs" 
        	activeIndex="0" 
        	style="width:100%;height:100%;margin-top:2px;"
        	borderStyle="border-left:0;"
       		>
       		<div title="<fmt:message key="chat.message.friend.list"/>" name="friendTab" url="<c:url value="/chat/friend/list" />"></div>
       		<div title="<fmt:message key="chat.message.friend.list"/>" name="friendTab" url="<c:url value="/chat/friend/list" />"></div>
         </div> 
    </div>
    <!-- 中间内容区域 -->
    <div title="center" region="center" style="border:0;">
        <div id="mainTabs" 
        	class="mini-tabs" 
        	activeIndex="0" 
        	style="width:100%;height:100%;" 
        	contextMenu="#tabsMenu"
       		>
       		<div title="<fmt:message key="system.main.home"/>" name="home" url="<c:url value="/welcome" />" iconCls="icon-cus-home"></div>
         </div>        
    </div>
   </div>
   <ul id="tabsMenu" class="mini-contextmenu" onbeforeopen="onTabsBeforeOpen">  
   		<li iconCls="icon-reload" onclick="refreshTab"><fmt:message key="global.refresh"/></li> 
	 	<li iconCls="icon-remove" onclick="closeTab"><fmt:message key="system.main.tab.closeTab"/></li> 
	 	<li iconCls="icon-cut" onclick="closeAllBut"><fmt:message key="system.main.tab.closeAllBut"/></li> 
	 	<li iconCls="icon-no" onclick="closeAll"><fmt:message key="system.main.tab.closeAll"/></li> 
	</ul>
	<script type="text/javascript">
		window.alert = function (e) {
		    return false;
		};
		mini.parse();
	    function notify() {
	        mini.showMessageBox({
	            showModal: false,
	            width: 250,
	            title: "提示",
	            iconCls: "mini-messagebox-warning",
	            message: "记录已删除",
	            timeout: 6000,
	            x: "right",
	            y: "bottom"
	        });
	    }
	      
		var tabs = mini.get('mainTabs');
		//首页
		function onHomePageClick() {
			tabs.activeTab(tabs.getTab(0));
		}
		
		function onReload(){
			var tree=mini.get("leftTree");
			tree.load(tree.url);
		}
		function refreshNode() {
	        var tree = mini.get("leftTree");
	        var node = tree.getSelectedNode();
	        if (node) {
	            tree.loadNode(node);
	        }
		 }
		//退出
		function onExitClick() {
			mini.confirm('您确定要退出吗？', '系统提示', function(action) {
				if (action == 'ok') {
					var messageId = mini.loading('<fmt:message key="global.loading"/>','<fmt:message key="global.prompt"/>');
					 setTimeout(function () {
						 window.location.href = '<c:url value="/logout" />';
		   	         },200);
				}
			});
		}
	
		/**
		 * 显示选项卡
		 * @param node 被单击的菜单项对象
		 * @param flag 选项卡显示模式，true-每次只显示一个，false-每次显示多个
		 */
		function showTab(node, flag) {
			var id = 'tab$' + node.menuId;//命别名
			var tab = tabs.getTab(id);
			if (!tab) {
				/* if (flag) {
					//把除第一个选项卡以外的所有选项卡全部删除
					tabs.removeAll(tabs.getTab(0));
				} */
				//定义选项卡对象
				tab = {};
				//设置选项卡名称
				tab.name = id;
				//设置选项卡标题
				tab.title = node.displayName;
				//设置选项卡可以被关闭
				tab.showCloseButton = true;
				//设置图标
				tab.iconCls="icon-cus-box_new";
				//设置选项卡URL，JQuery MINIUI会根据这个URL异步加载网页内容
				var url = node.url;
				var tag="?";
				if(url.indexOf('?')!=-1){
					tag="&";
				}
				tab.url = '${context_path}'+url+tag+"_perMenuId="+node.menuId+"&time="+(new Date()).getTime();
				//把新创建的选项卡添加到选项卡控件中
				tabs.addTab(tab);
			}
			//激活当前选项卡
			tabs.activeTab(tab);
		}
	
		//单击菜单
		function onNodeClick(e) {
			var node = e.node;
			var isLeaf = e.isLeaf;
			if (isLeaf) {//单击的是树的叶子，才显示选项卡
				if(node.url){
					showTab(node, true);
				}
			}
		}
		//展开当前菜单时，是否折叠其它菜单
		function onBeforeExpand(e) {
			var tree = e.sender;
			var nowNode = e.node;
			var root = tree.getRootNode();
	
			tree.cascadeChild(root, function(node) {
				if (tree.isExpandedNode(node)) {
					if (node != nowNode && !tree.isAncestor(node, nowNode)) {
						tree.collapseNode(node, true);
					}
				}
			});
		}
		function onChangeLan(e){
			location.href="${ctx}/main?locale="+e.value;
		}
		tabs.on("beforecloseclick", function(e) {
			if (e.tab.title == "修改用户信息") {
				window.location.reload();
			}
		});
		function onBeforeOpen(e) {
		    var menu = e.sender;
		    var tree = mini.get("leftTree");
		    var node = tree.getSelectedNode();
		    if (!node) {
		        e.cancel = true;
		        return;
		    }
		    if (node) {
		        e.cancel = true;
		        //阻止浏览器默认右键菜单
		        e.htmlEvent.preventDefault();
		        return;
		    }
	
		    ////////////////////////////////
		    var editItem = mini.getbyName("edit", menu);
		    var removeItem = mini.getbyName("remove", menu);
		    editItem.show();
		    removeItem.enable();
	
		    if (node.id == "forms") {
		        editItem.hide();
		    }
		    if (node.id == "lists") {
		        removeItem.disable();
		    }
		}
		 ///////////////////////////
	    var currentTab = null;
	    function onTabsBeforeOpen(e) {
	         currentTab = tabs.getTabByEvent(e.htmlEvent);
	         if (!currentTab) {
	             e.cancel = true;                
	         }
	     }
	    function refreshTab(){
	    	tabs.reloadTab(currentTab);
	    }
	    function closeTab() {
	    	tabs.removeTab(currentTab);
	    }
	    function closeAllBut() {
	    	var but = [currentTab];            
		    but.push(tabs.getTab("home"));
	    	tabs.removeAll(but);
	    }
	    function closeAll() {
    	   var but = [];            
	       but.push(tabs.getTab("home"));
	       tabs.removeAll(but);
	    }
	    function myrefresh(){
	           window.location.reload();
	    }
	    ///////////////////////////////////////////////
	    var websocket = connWebSocket();
	  	function connWebSocket(){
		  		//判断当前浏览器是否支持WebSocket
		  	var ws = null;
		  	if('WebSocket' in window){
		  		ws = new WebSocket("ws://"+window.location.host+"/archives/websocket/system");
		  	}else{
		  		ws =new SockJS("http://"+window.location.host+"/archives//websocket/sockjs/system");
		  	}
		  	return ws;
	  	}
	  	function recCnnWebSocket(){
	  		if(websocket!=null){
	  			websocket.close();
	  		}
	  		websocket = connWebSocket();
	  	}
	  	//连接发生错误的回调方法
	  	websocket.onerror = function(){
	  		 mini.showMessageBox({
	            showModal: false,
	            width: 250,
	            title: "提示",
	            iconCls: "mini-messagebox-warning",
	            message: "error",
	            timeout: 3000,
	            x: "right",
	            y: "bottom"
	        });
	  	};
	  	
	  	//连接成功建立的回调方法
	  	websocket.onopen = function(event){
	  		 mini.showMessageBox({
	            showModal: false,
	            width: 250,
	            title: "提示",
	            iconCls: "mini-messagebox-warning",
	            message: "socket open",
	            timeout: 3000,
	            x: "right",
	            y: "bottom"
	        });
	  	}
	  	
	  	//接收到消息的回调方法
	  	websocket.onmessage = function(event){
	  		try{
	  			var message = jQuery.parseJSON(event.data)
		  		mini.showMessageBox({
		            showModal: false,
		            width: 250,
		            title: "提示",
		            iconCls: "mini-messagebox-warning",
		            message: message.message,
		            timeout: 3000,
		            x: "right",
		            y: "bottom"
		        });
		        if(message.flag){
		           myrefresh();
		        }
	  		}catch(e){
	  		
	  		}
	  	}
	  	
	  	//连接关闭的回调方法
	  	websocket.onclose = function(){
	  		 mini.showMessageBox({
	            showModal: false,
	            width: 250,
	            title: "提示",
	            iconCls: "mini-messagebox-warning",
	            message: "close",
	            timeout: 3000,
	            x: "right",
	            y: "bottom"
	        });
	  	}
	  	
	  	//监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
	  	/* window.onbeforeunload = function(){
	  		websocket.close();
	  	} */
	  	/* setInterval("recCnnWebSocket()",300000) */
	  	///////////////////////////////////////////////////////////
	  	//var mailInfoTimer=setInterval("getNotReadMailInfo()",60000);
	  	$(function () {
			 (function longPolling() {
				 $.ajax({
		                url: '<c:url  value="/email/receivebox/getNotReadMailInfo.action" />',
						type: 'post',
		                cache: false,
		                dataType:'json',
		                success: function (data) {
		                	if(!data.flag){
		                		mini.showMessageBox({
		             	            showModal: false,
		             	            width:250,
		             	            title:'<fmt:message key="global.prompt"/>',
		             	            iconCls:"mini-messagebox-warning",
		             	            message:data.message,
		             	            timeout: 3000,
		             	            x: "right",
		             	            y: "bottom"
		             	        });
		                		setTimeout(function(){
			                		longPolling();
			                	},5000);
		                	}else{
		                		setTimeout(function(){
			                		longPolling();
			                	}, 60000);
		                	}
		                },
		                error: function (jqXHR, textStatus, errorThrown) {
		                	
		    	        }
		            });
			 })();
		});
	  	function show_cur_times(){
		 //获取当前日期
		 var date_time = new Date();
		 //定义星期
		 var week;
		 //switch判断
		 switch (date_time.getDay()){
			case 1: week="星期一"; break;
			case 2: week="星期二"; break;
			case 3: week="星期三"; break;
			case 4: week="星期四"; break;
			case 5: week="星期五"; break;
			case 6: week="星期六"; break;
			default:week="星期天"; break;
		 }
		 //年
		 var year = date_time.getFullYear();
		 	//判断小于10，前面补0
		   if(year<10){
		 	year="0"+year;
		 }
		 //月
		 var month = date_time.getMonth()+1;
		 	//判断小于10，前面补0
		  if(month<10){
		  month="0"+month;
		 }
		 //日
		 var day = date_time.getDate();
		 	//判断小于10，前面补0
		   if(day<10){
		 	day="0"+day;
		 }
		 //时
		 var hours =date_time.getHours();
		 	//判断小于10，前面补0
		    if(hours<10){
		 	hours="0"+hours;
		 }
		 //分
		 var minutes =date_time.getMinutes();
		 	//判断小于10，前面补0
		    if(minutes<10){
		 	minutes="0"+minutes;
		 }
		 //秒
		 var seconds=date_time.getSeconds();
		 	//判断小于10，前面补0
		    if(seconds<10){
		 	seconds="0"+seconds;
		 }
		 //拼接年月日时分秒
		 var date_str = year+"-"+month+"-"+day+" "+hours+":"+minutes+":"+seconds+" "+week;
		 
		 //显示在id为showtimes的容器里
		 $("#main_time").html(date_str);
		}
	  	show_cur_times();
	  	setInterval("show_cur_times()",100);
	</script>
</body>
</html>
