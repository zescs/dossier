<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <script type="text/javascript" src="<c:url value="/resources/scripts/boot.js" />"></script>
    <link href="<c:url value="/resources/css/layout.css" />" rel="stylesheet" type="text/css" />
	<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet" type="text/css" />
  </head>
  <body>
   <div>
        <div class="mini-toolbar" style="border-bottom:0;padding:0px;">
             <table style="width:100%;">
	            <tr>
	                <td style="width:100%;">                
	                    <a class="mini-button" iconCls="icon-addfolder" plain="true" onclick="onAdd"><fmt:message key="global.add"/></a>
	                    <a class="mini-button" iconCls="icon-edit" plain="true" onclick="editConfig()"><fmt:message key="global.edit"/></a>
	                    <a class="mini-button" iconCls="icon-remove" plain="true" onclick="removeConfig()"><fmt:message key="global.remove"/></a>
	                    <a class="mini-button" iconCls="icon-upload" plain="true" onclick="upload()"><fmt:message key="global.import"/></a>
	                    <a class="mini-button" iconCls="icon-download" plain="true" onclick="expoetData()"><fmt:message key="global.export"/></a>
	                </td>
	                <td style="white-space:nowrap;">
                        <input id="keyword" class="mini-textbox" emptyText="<fmt:message key="global.message.search.emptyText"/>" style="width:150px;" onenter="onKeyEnter"/>   
                        <a class="mini-button" onclick="search()"  iconCls="icon-search" ><fmt:message key="global.search"/></a>
                    </td>
	            </tr>
      	   </table>        
        </div>
    </div>
    <div class="mini-fit">
	    <div id="entityDG"
	        class="mini-datagrid"
	    	style="width:100%;height:100%"
			emptyText="<fmt:message key="global.list.emptyText"/>"
			showEmptyText="true"
			url="<c:url value="/data/constant/list.action" />"
			idField="systemConfigId"
			sizeList="${sizeList}"  
		    pageSize="${app_pageSize}" 
			totalField="total"
			dataField="list"
			onLoad="onLoad"
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
	            <div type="checkcolumn" width="10"></div>
	            <div type="indexcolumn" headerAlign="center" width="20"><fmt:message key="global.index"/></div>
				<div field="sysKey" width="60" align="center" headerAlign="center"><fmt:message key="systemConfig.sysKey"/></div>
				<div field="sysValue" width="100" align="center" headerAlign="center"><fmt:message key="systemConfig.sysValue"/></div>
				<div field="enableState" width="60" align="center" headerAlign="center" allowSort="true"><fmt:message key="systemConfig.enableState"/></div>
				<div field="flush" width="30" align="center" headerAlign="center" allowSort="true"><fmt:message key="systemConfig.flush"/></div>
				<div field="createDate" width="50" align="center" headerAlign="center" dateFormat="yyyy-MM-dd HH:mm:ss" allowSort="true"><fmt:message key="systemConfig.createDate"/></div>
				<div field="remark" width="100" align="center" headerAlign="center"><fmt:message key="systemConfig.remark"/></div>
	        </div>
	    </div>
    </div>
    <script type="text/javascript">
        mini.parse();
        var grid = mini.get("entityDG");
        grid.load();
        ////////////////////////////////////////////////////
        var formData ={}
        //////////////////////////////////////////////////////
        //表格加载事件
		function onLoad(e) {
			var grid = e.sender;
			grid.setShowPager(grid.totalCount > 0);
		}
		 grid.on("drawcell", function (e) {
        	  var  column = e.column;
              if (column.field == "enableState") {
                  if (e.value == true) {
                      e.cellHtml = "<span style=\"color:red;\">可更改</span>";
                  } else {
                      e.cellHtml = "<span style=\"color:#ff7300;font-weight:bold;\">不可更改</span>";
                  }
              }
              if (column.field == "flush") {
                  if (e.value == true) {
                      e.cellHtml = "<span style=\"color:red;\">是</span>";
                  } else {
                      e.cellHtml = "<span style=\"color:#ff7300;font-weight:bold;\">否</span>";
                  }
              }
         });
        //////////////////////////////////////////////////////
       function onAdd() {
            mini.open({
                url:'<c:url	value="/data/constant/add.action" />',
                title:'<fmt:message key="systemConfig.list.add"/>', 
            	iconCls :'icon-addfolder',
				allowResize : false,
				allowDrag:false,
				showModal:true,
				width:350,
                ondestroy: function (action) {
                    grid.reload();
                }
            });
        }
        //删除系统配置信息
        function removeConfig(){
        	var rows = grid.getSelecteds();
        	if(rows.length>0){
        		    //检测当前值是否可以修改
	                mini.showMessageBox({
	                title: '<fmt:message key="global.remove.title"/>',
	                iconCls: "mini-messagebox-question",
	                buttons: ["ok","cancel"],
	                message: '<fmt:message key="global.remove.message"/>',
	                callback: function (action) {
	                	if(action=='ok'){
	                		var ids = [];
		            		$.each(rows,function(i,item){
		            			ids.push(item.systemConfigId);
		            		});
	                		var messageId = mini.loading('<fmt:message key="global.loading"/>','<fmt:message key="global.prompt"/>');
	                		$.ajax({
	                			url:'<c:url value="/data/constant/remove.action" />',
	                			data:{"_method":"delete","delete_ids":ids.join()},
	                			type:"post",
	                			dataType:'json',
	                			success:function(data){
	                				mini.hideMessageBox(messageId);
	                				if(data.flag){
	                					 grid.reload();
	                				}
	                			},
	                			error: function (jqXHR, textStatus, errorThrown) {
		            				appError(jqXHR, textStatus, errorThrown,messageId,false);
		           	            }
	                		});
	                	}
	                }
	            });
        	}else{
        		mini.alert('<fmt:message key="global.chose.prompt" />');
        	}
        }
  		function editConfig() {
  			var rows = grid.getSelecteds();
  			if(rows.length<=1&&rows.length!=0 ){
	             var row = grid.getSelected();
	             if(row){
	             	//检测当前值是否可以修改
	             	if(row.enableState==true){
	             		mini.open({
		                url: '<c:url value="/data/constant/edit" />',
			                title: '<fmt:message key="systemConfig.edit.message.title"/>',
			                width:350,
							height:400,
			                iconCls:'icon-edit', 
			                allowResize:false,
			                onload: function () {
			                    var iframe = this.getIFrameEl();
			                    iframe.contentWindow.init(row.id);
			                },
			                ondestroy: function (action) {
			                	grid.reload();
			                }
			            });
	             	}else{
	             		mini.alert('<fmt:message key="global.message.enableState"/>');
	             	}
	             }else{
	             	mini.alert('<fmt:message key="global.chose.prompt"/>');
	             }
        	}else{
        		mini.alert('<fmt:message key="global.chose.justChose"/>');
        	}
  		}
  		function upload(){
  			mini.open({
                url:'<c:url	value="/data/constant/upload" />',
                title:'<fmt:message key="global.import"/>', 
            	iconCls :'icon-addfolder',
				allowResize : false,
				allowDrag:false,
				showModal:true,
				width:450,
				height:130,
                ondestroy: function (action) {
                    grid.reload();
                }
            });
  		}
  	  function expoetData(){
  	  	//将用户选中的所有行的id传递到后台
  	  	var rows = grid.getSelecteds();
        	if(rows.length>0){
        		var messageId = mini.loading('处理中，请稍等......','提示');
              	var ids = [];
          		$.each(rows,function(i,item){
          			ids.push(item.id);
          		});
          		location.href='<c:url value="/data/constant/export" />/'+ids.join();
          		mini.hideMessageBox(messageId);
        	}else{
        		mini.alert('<fmt:message key="global.message.export.choseData"/>');
        	}
  	  }
  	  /////////////////////////////////////////////////////
 	  function onKeyEnter(e) {
           search();
      }
  	  $("#keyword").bind("keydown", function (e) {
        if (e.keyCode == 13) {
            search();
        }
      });
      function search(){
		 formData["sysKey"]=mini.get("#keyword").getValue();
		 grid.load(formData);
	  }
	  ///////////////////////////////////////////
    </script>
  </body>
</html>
