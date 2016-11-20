<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!doctype html>
<html>
<head>
    <script type="text/javascript" src="<c:url value="/resources/scripts/boot.js" />"></script>
    <link href="<c:url value="/resources/css/layout.css" />" rel="stylesheet" type="text/css" />
	<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet" type="text/css" />
</head>
<body>
	<!-- body-start -->
	<!-- toolbar-start -->
	<div class="mini-toolbar" style="padding:0px;" borderStyle="border-bottom:0;">
		<table style="width:100%;">
			<tr>
			   <td style="width:100%;">   
			  	   <a class="mini-button" iconCls="icon-remove" onclick="onRemoveData" plain="true"><fmt:message key="global.message.remove"/></a>            
			  	   <a class="mini-button" iconCls="icon-download" onclick="onExportData" plain="true"><fmt:message key="global.message.export"/></a>            
			  	   <span class="separator"></span>
			  	   <a class="mini-button" iconCls="icon-reload" onclick="onReloaddData" plain="true"><fmt:message key="global.reload"/></a>            
                </td>
                <td style="white-space:nowrap;">
                     <input id="keyword" class="mini-textbox" emptyText="<fmt:message key="global.message.search.emptyText"/>" style="width:150px;" onenter="onKeyEnter"/>   
                     <a class="mini-button" onclick="search()"  iconCls="icon-search" ><fmt:message key="global.search"/></a>
                </td>
				<td style="white-space:nowrap;">
                    <a class="mini-button" onclick="complexSearch()"  iconCls="icon-cus-search_go" ><fmt:message key="global.message.search.complex"/></a>
                </td>
			</tr>
		</table>
	</div>
	<!-- grid-start -->
	<div class="mini-fit">
		<div id="entityDG"
	        class="mini-datagrid"
	    	style="width:100%;height:100%"
			emptyText="<fmt:message key="global.list.emptyText"/>"
			showEmptyText="true"
			url="<c:url value="/order/goods/list.action" />"
			idField="goodsId"
			sizeList="${sizeList}"  
		    pageSize="${app_pageSize}" 
			totalField="total"
			dataField="list"
			onLoad="onLoad"
			contextMenu="#gridMenu"
			allowResizeColumn="false"
			allowMoveColumn="false" 
			multiSelect="true"
			allowCellSelect="true"
            showFilterRow="true"
            showSortIcon="true"
            allowAlternating="false"
   			showSummaryRow="false"
     		>
     		<div property="columns">
     			<div type="checkcolumn" width="30"></div>
				<div type="indexcolumn" width="50" align="center" headerAlign="center"><fmt:message key="global.index"/></div>
				<div field="goodsName" width="200" align="center" headerAlign="center"><fmt:message key="goods.goodsName"/></div>
				<div field="description" width="120" align="center" headerAlign="center"><fmt:message key="goods.description"/></div>
				<div field="price" width="150" align="center" headerAlign="center"><fmt:message key="goods.price"/></div>
     		</div>
     	</div>
     
	</div>
	<!-- body-end -->
	<script type="text/javascript">
	  mini.parse();
	  ///////////////////////////////////////////
	  var grid = mini.get("entityDG");
	  ///////////////////////////////////////////
	  grid.load();
	  ///////////////////////////////////////////
	  var formData ={}
	  ///////////////////////////////////////////
	  function onLoad(e) {
			var grid = e.sender;
			grid.setShowPager(grid.totalCount > 0);
	  }
	  ///////////////////////////////////////////
	  ///////////////////////////////////////////
	  function complexSearch(){
		mini.open({
                url:'<c:url value="/borrow/electfile/search.action" />',
                title:'<fmt:message key="global.message.search.complex"/>', 
            	iconCls :'icon-cus-search_go',
				allowResize : true,
				allowDrag:true,
				showModal:true,
				width:350,
				height:600,
                onload: function () {
                    var iframe = this.getIFrameEl();
	                iframe.contentWindow.setData(formData);
                },
                ondestroy: function (action) {
                	if(action=='ok'){
	                    var iframe = this.getIFrameEl();
	                    var data = iframe.contentWindow.getData();
	                    data = mini.clone(data);//必须
	                    if (data) {
	                       formData=data;
	                       search();
	                    }
                    }
                }
         });
	  }
	  ///////////////////////////////////////////
	  function onReloaddData(){
	  	grid.reload();
	  }
	  ///////////////////////////////////////////
	  function onAddData(){
	  	 mini.open({
                url:'<c:url	value="/borrow/electfile/add.action" />?archivesId='+mini.get("archivesId").getValue(),
                title:'<fmt:message key="global.message.addData"/>', 
            	iconCls :'icon-addfolder',
				allowResize : false,
				allowDrag:false,
				showModal:true,
				width:360,
				height:700,
                ondestroy: function (action) {
                	if(action=='OK'){
	                   grid.reload();
                    }
                }
         });
	  }
	  ///////////////////////////////////////////
	  function onRemoveData(){
	     var messageId = mini.loading('<fmt:message key="global.loading"/>','<fmt:message key="global.prompt"/>');
	  	 var rows = grid.getSelecteds();
	   	 if(rows.length>0){
	            mini.showMessageBox({
	            title: '<fmt:message key="global.remove.title"/>',
	            iconCls: "mini-messagebox-question",
	            buttons: ["ok","cancel"],
	            message: '<fmt:message key="global.message.remove.batch.message"/>',
	            callback: function (action) {
	            	if(action=='ok'){
	            		var ids = [];
		         		$.each(rows,function(i,item){
		         			ids.push(item.borrowElectFileId);
		         		});
	            		$.ajax({
	            			url:'<c:url value="/borrow/electfile/remove.action" />',
	            			data:{"_method":"delete","delete_ids":ids.join()},
	            			type:"post",
	            			dataType:'json',
	            			success:function(data){
	            				mini.hideMessageBox(messageId);
	            				mini.showMessageBox({
	            		            title: '<fmt:message key="global.prompt"/>',
	            		            iconCls: "mini-messagebox-question",
	            		            buttons: ["ok"],
	            		            message:data.message,
	            		            callback: function (action) {
	            		            	if(data.flag){
	                   					 	grid.reload();
	                   					}
	            		            }
	            		        });
	            			},
	            			error: function (jqXHR, textStatus, errorThrown) {
	            				appError(jqXHR, textStatus, errorThrown,messageId,false);
	           	         }
	            	  });
	            	}else{
	            		mini.hideMessageBox(messageId);
	            	}
	            }
	        });
	   	}else{
	   		mini.alert('<fmt:message key="global.chose.prompt" />');
	   		mini.hideMessageBox(messageId);
	   	}
	  }
	  ///////////////////////////////////////////
 	  function onKeyEnter(e) {
           search();
      }
  	  $("#keyword").bind("keydown", function (e) {
        if (e.keyCode == 13) {
            search();
        }
      });
      function search(){
		 formData["goodsName"]=mini.get("#keyword").getValue();
		 grid.load(formData);
	  }
	  ///////////////////////////////////////////
	</script>
</body>
</html>