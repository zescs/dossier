<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://zescs.com/jstl/tag/core" prefix="z"%>
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
			  	  <z:toolbar/> 
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
			url="<c:url value="/archives/entry/query/list.action" />"
			idField="id"
			sizeList="${r'${sizeList}'}"  
		    pageSize="${r'${app_pageSize}'}" 
			totalField="totalElements"
			dataField="pageList"
			onLoad="onLoad"
			onbeforeload="onBeforeload"
			contextMenu="#gridMenu"
			onshowrowdetail="onShowRowDetail"
			allowResizeColumn="${config.allowResizeColumn?string('true', 'false')}"
			allowMoveColumn="${config.allowMoveColumn?string('true', 'false')}" 
			multiSelect="${config.multiSelect?string('true', 'false')}"
			allowCellSelect="${config.allowCellSelect?string('true', 'false')}"
            showFilterRow="${config.showFilterRow?string('true', 'false')}"
            showSortIcon="${config.showSortIcon?string('true', 'false')}"
            allowAlternating="${config.allowAlternating?string('true', 'false')}"
   			showSummaryRow="${config.showSummaryRow?string('true', 'false')}"
     		>
     		<div property="columns">
     			<div type="checkcolumn" width="50"></div>
     			<div type="expandcolumn" width="50">#</div>
				<div type="indexcolumn" width="70" align="center" headerAlign="center"><fmt:message key="global.index"/></div>
				<#if existFile>
					<div name="actionElectFile" width="120" align="center" headerAlign="center"><fmt:message key="archives.message.title.file"/></div>
				</#if>
				${property_columns}
				<div name="actionHandle" width="150" headerAlign="center" align="center" renderer="onActionHandleRenderer" cellStyle="padding:0;"><fmt:message key="global.message.title.handle"/></div>
     		</div>
     	</div>
     
	</div>
	<!-- grid-end -->
	<!-- hidden-start -->
	<input name="archivesId" id="archivesId" value="${r'${archivesId}'}" class="mini-hidden" />
	<!-- hidden-end -->
	<!-- contextmenu-start -->
    <ul id="gridMenu" class="mini-contextmenu" onbeforeopen="onBeforeOpen"> 
    	<#if existFile>
        <li>
		    <span iconCls="icon-cus-folder_modernist_type_image" ><fmt:message key="archives.message.menu.title.electFile" /></span>
			<ul>
			   <z:archivesMenu showType="MENU"/>
			</ul>
	    </li>
	    <li class="separator"></li>
        </#if>             
        <#if existAtt>
        <li iconCls="icon-cus-folder_classic_type_document" onclick="onLookAttachment"><fmt:message key="global.message.look.att" /></li>
        <li class="separator"></li>
        </#if>
        <li>
		    <span iconCls="icon-cus-folder_modernist_down" ><fmt:message key="global.message.contenxt.menu.title.register" /></span>
			<ul>
			    <li iconCls="icon-cus-folder_modernist_stuffed_add" onclick="onAddAccessInfo"><fmt:message key="global.message.query.register" /></li>
	            <li class="separator"></li>
	            <li iconCls="icon-cus-folder_modernist_stuffed_add" onclick="onAddBorrowPhysical"><fmt:message key="global.message.lending.register" /></li>	
			</ul>
	    </li>
    </ul>
    <!-- contextmenu-end -->
    <div id="entityDetialForm" style="display:none;height:300px;">
       <div  class="mini-tabs" 
       		 id="entityTabs"
       		 style="width:100%;height:100%;"
       		 activeIndex="0"
       		 onactivechanged="onActivechanged"
             >
            <div  title="<fmt:message key="archives.entry.note" />" name="defaultTab" iconCls="icon-cus-view_less_text">
        	   <p id="note_detial"></p>
            </div>
       </div>
    </div>
	<!-- body-end -->
	<script type="text/javascript">
	  mini.parse();
	  ///////////////////////////////////////////
	  var grid = mini.get("entityDG");
	  ///////////////////////////////////////////
	  var entityDetialForm = document.getElementById("entityDetialForm");
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
	  function onBeforeload(e){
	  	e.data["archives.archivesId"]=mini.get("archivesId").getValue();
	  }
	  ///////////////////////////////////////////
	  function onBeforeOpen(e) {
   	    var menu = e.sender;
   	    var row = grid.getSelected();
   	    if (!row) {
   	        e.cancel = true;
   	        //阻止浏览器默认右键菜单
   	        e.htmlEvent.preventDefault();
   	        return;
   	    }
	  }
	  ///////////////////////////////////////////
	  function onShowRowDetail(e) {
    	 var tabs = mini.get("entityTabs");
         var defaultTab =tabs.getTab("defaultTab");
    	 var row = e.record;
         var td = grid.getRowDetailCellEl(row);
         td.appendChild(entityDetialForm);
         entityDetialForm.style.display ="";
         tabs.activeTab(defaultTab);
         grid.loading();
         $.ajax({
              url: "<c:url value='/archives/entry/query/getnote.action' />",
              type:"get",
              dataType:"json",
              cache: false,
              data:{"entryId":row.id},
              success: function (_data) {
                  $("#note_detial").html(_data);
                  grid.unmask();
              },
              error: function (jqXHR, textStatus, errorThrown) {
                $("#note_detial").html(textStatus);
              	grid.unmask();
    	      }
         });
         grid.unmask();
      }
      ///////////////////////////////////////////////
	  //动态设置参数
	  function onActivechanged(e){
		var tab = e.tab;
		var row = grid.getSelected();
		if(row){
			if(tab.url){
				var url =tab.url;
				if(url.indexOf("archivesId")!=-1){
					url =url.substring(0,url.indexOf("archivesId")-1);
				}
				tab.url=url+'?archivesId='+mini.get("archivesId").getValue()+"&entryId="+row.id;
			}
		}
	  }
	  ///////////////////////////////////////////
	  grid.on("drawcell", function (e) {
   	  	var record = e.record;
    	var column = e.column;
   	  	var id = record.id;
   	  	<#if existFile>
   	  		if (column.name == "actionElectFile") {
	   	  		e.cellStyle = "text-align:center";
	   	  		e.cellHtml ='<z:archivesMenu/>';
   	  		}
   	  	</#if>
	  });
	  ///////////////////////////////////////////
	  function onActionHandleRenderer(e){
		  	var grid = e.sender;
	        var record = e.record;
	        var s="";
	        s = '<a href="javascript:onPrintCover(0);" class="btn_common"><fmt:message key="global.message.cover"/></a>';
	        <#if existFile>
	        s += '<i class="nbsp_common"></i><a href="javascript:onEditElectFile(0);" class="btn_common"><fmt:message key="global.message.edit.electFile"/></a>';
	        </#if>
	        return s;
	   }
	  <#if existFile>
	  /////////////////修改原文//////////////////////////
	  function onEditElectFile(){
	  	var rows = grid.getSelecteds();
	  	  if(rows.length<=1&&rows.length!=0 ){
	  	  	 var row = grid.getSelected();
	         if(row){
	         	mini.open({
	                url:'<c:url	value="/archives/electfile/edit.action" />?entryId='+row.id,
	                title:'<fmt:message key="global.message.edit.electFile"/>', 
	            	iconCls :'icon-edit',
					allowResize : false,
					allowDrag:false,
					showModal:true,
					width:460,
					height:380,
	                ondestroy: function (action) {
	                	if(action=='OK'){
		                   grid.reload();
	                    }
	                }
        	   });
	         }else{
	         	mini.alert('<fmt:message key="global.chose.prompt"/>');
	         }
	  	  }else{
	  	  	mini.alert('<fmt:message key="global.chose.justChose"/>');
	  	  }
	  }
	  <#if existSwf>
	  function onPreviewElectFile(){
			  var row = grid.getSelected();
			  if(row){
			       window.open('<c:url value="/archives/electfile/preview.action" />?id='+row.id,"_blank");
			  }else{
		         	mini.alert('<fmt:message key="global.chose.prompt"/>');
		      }
	  }
	  </#if>
	  function onLookElectFile(){
	   var row = grid.getSelected();
		  if(row){
		       window.open('<c:url value="/archives/electfile/look.action" />?id='+row.id,"_blank");
		  }else{
	         	mini.alert('<fmt:message key="global.chose.prompt"/>');
	      }
	  }
	  </#if>
	  /////////////////打印封面//////////////////////////
	  function onPrintCover(){
	  	var rows = grid.getSelecteds();
	  	  if(rows.length<=1&&rows.length!=0 ){
	  	  	 var row = grid.getSelected();
	         if(row){
	           location.href='<c:url value="/archives/entry/print/cover.action" />?entryId='+row.id;
	         }else{
	         	mini.alert('<fmt:message key="global.chose.prompt"/>');
	         }
	  	  }else{
	  	  	mini.alert('<fmt:message key="global.chose.justChose"/>');
	  	  }
	  }
	  ///////////////////////////////////////////
	  function onExportData(){
	 	if(grid.totalCount>0){
	 		mini.open({
                url:'<c:url value="/archives/entry/stencil/chose.action" />?archivesId='+mini.get("archivesId").getValue(),
                title:'<fmt:message key="global.message.chose.stencil"/>', 
            	iconCls :'icon-cus-folder_modernist_type_movie',
				allowResize : true,
				allowDrag:true,
				showModal:true,
				width:350,
				height:200,
                onload: function () {
                     var iframe = this.getIFrameEl();
                     iframe.contentWindow.setData(grid.getLoadParams());
                 }
            });
	 	}else{
	 		mini.alert('<fmt:message key="global.message.data.none" />');
	 	}
	  }
	  ///////////////////////////////////////////
	  function onExportAllData(){
	 	if(grid.totalCount>0){
	 		if(grid.totalCount<=5000){
		 		mini.open({
	                url:'<c:url value="/archives/entry/stencil/chose.action" />?archivesId='+mini.get("archivesId").getValue(),
	                title:'<fmt:message key="global.message.chose.stencil"/>', 
	            	iconCls :'icon-cus-folder_modernist_type_movie',
					allowResize : false,
					allowDrag:true,
					showModal:true,
					width:350,
					height:300,
	                onload: function () {
	                     var iframe = this.getIFrameEl();
	                     iframe.contentWindow.setData(grid.getLoadParams());
	                 }
	            });
            }else{
            	mini.alert('<fmt:message key="archives.message.export.max.count" />');
            }
	 	}else{
	 		mini.alert('<fmt:message key="global.message.data.none" />');
	 	}
	  }
	  ///////////////////////////////////////////
	  function onExportChoseData(){
	  	var rows = grid.getSelecteds();
       	if(rows.length>0){
       		var messageId = mini.loading('<fmt:message key="global.loading"/>','<fmt:message key="global.prompt"/>');
           	var ids = [];
       		$.each(rows,function(i,item){
       			ids.push(item.id);
       		});
       		mini.hideMessageBox(messageId);
       		mini.open({
                url:'<c:url value="/archives/entry/stencil/chose.action" />?archivesId='+mini.get("archivesId").getValue(),
                title:'<fmt:message key="global.message.chose.stencil"/>', 
            	iconCls :'icon-cus-folder_modernist_type_movie',
				allowResize : false,
				allowDrag:true,
				showModal:true,
				width:350,
				height:300,
                onload: function () {
                     var iframe = this.getIFrameEl();
                     iframe.contentWindow.setData({"export_ids":ids.join()});
                 },
                  ondestroy: function (action) {
            	  if(action=='ok'){
                   grid.reload();
                  }
                }
            });
       	}else{
       		mini.alert('<fmt:message key="global.message.export.choseData"/>');
       	}
	  }
	  ///////////////////////////////////////////
	  function onImportData(){
	  	 mini.open({
                url:'<c:url value="/archives/entry/import/excel.action" />?archivesId='+mini.get("archivesId").getValue(),
                title:'<fmt:message key="global.message.data.import"/>', 
            	iconCls :'icon-upload',
				allowResize : false,
				allowDrag:true,
				showModal:true,
				width:450,
				height:400,
				ondestroy: function (action) {
                   grid.reload();
               }
            });
	  }
	  ///////////////////////////////////////////
	  function complexSearch(){
		mini.open({
                url:'<c:url value="/archives/entry/query/search.action" />?archivesId='+mini.get("archivesId").getValue(),
                title:'<fmt:message key="global.message.search.complex"/>', 
            	iconCls :'icon-cus-search_go',
				allowResize : true,
				allowDrag:true,
				showModal:true,
				width:380,
				height:600,
                onload: function () {
                    var iframe = this.getIFrameEl();
	                iframe.contentWindow.setData(formData);
                },
                ondestroy: function (action) {
                	if(action=='ok'){
	                    var iframe = this.getIFrameEl();
	                    var data = iframe.contentWindow.getData();
	                    data = mini.clone(data);    //必须
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
	  function onLookVolumes(){
	  	var row = grid.getSelected();
		if(row){
			mini.open({
	                url:'<c:url	value="/archives/entry/query/volumes/list.action" />?archivesId='+mini.get("archivesId").getValue()+"&entryId="+row.id,
	                title:'<fmt:message key="archives.message.title.tab.volumes"/>', 
	            	iconCls :'icon-addfolder',
					allowResize : false,
					allowDrag:false,
					showModal:true,
					width:1200,
					height:700,
	                ondestroy: function (action) {
	                	if(action=='OK'){
		                   grid.reload();
	                    }
	                }
	         });
		 }
	  }
	  ///////////////////////////////////////////
	  <#if existAtt>
	  function onLookAttachment(){
	  	var row = grid.getSelected();
		if(row){
			mini.open({
	                url:'<c:url	value="archives/attachment/list.action" />?entryId='+row.id,
	                title:'<fmt:message key="global.message.look.att"/>', 
	            	iconCls :'icon-cus-folder_classic_type_document',
					allowResize : false,
					allowDrag:false,
					showModal:true,
					width:1000,
					height:600,
	                ondestroy: function (action) {
	                	if(action=='OK'){
		                   grid.reload();
	                    }
	                }
	         });
		 }
	  }
	  </#if>
	  ///////////////////////////////////////////
	  function onAddData(){
	  	 mini.open({
                url:'<c:url	value="/archives/entry/add.action" />?archivesId='+mini.get("archivesId").getValue(),
                title:'<fmt:message key="global.message.addData"/>', 
            	iconCls :'icon-addfolder',
				allowResize:${allowResize?string('true', 'false')},
				allowDrag:false,
				showModal:true,
				width:${addWidth?c},
				height:${addHeight?c},
                ondestroy: function (action) {
                	if(action=='OK'){
	                   grid.reload();
                    }
                }
         });
	  }
	  ///////////////////////////////////////////
	  function onEditData(){
		  var rows = grid.getSelecteds();
	  	  if(rows.length<=1&&rows.length!=0 ){
	  	  	 var row = grid.getSelected();
	         if(row){
	         	mini.open({
	                url:'<c:url	value="/archives/entry/edit.action" />?archivesId='+mini.get("archivesId").getValue(),
	                title:'<fmt:message key="global.message.data.edit"/>', 
	            	iconCls :'icon-edit',
					allowResize:${allowResize?string('true', 'false')},
					allowDrag:false,
					showModal:true,
					width:${editWidth?c},
				    height:${editHeight?c},
				    onload: function () {
	                    var iframe = this.getIFrameEl();
	                    var data={"entryId":row.id};
	                    iframe.contentWindow.initData(data);
		            },
	                ondestroy: function (action) {
	                	if(action=='OK'){
		                   grid.reload();
	                    }
	                }
        	   });
	         }else{
	         	mini.alert('<fmt:message key="global.chose.prompt"/>');
	         }
	  	  }else{
	  	  	mini.alert('<fmt:message key="global.chose.justChose"/>');
	  	  }
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
			         			ids.push(item.id);
			         		});
		            		$.ajax({
		            			url:'<c:url value="/archives/entry/remove.action" />',
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
	   function onAddAccessInfo(){
        var rows = grid.getSelecteds();
		if(rows.length<=1&&rows.length!=0 ){
		  	var row = grid.getSelected();
		  	mini.open({
	                url:'<c:url	value="/utilize/accessinfo/add.action" />?entryId='+row.id,
	                title:'<fmt:message key="global.message.query.register"/>', 
	            	iconCls :'icon-cus-folder_modernist_stuffed_add',
					allowResize : false,
					allowDrag:false,
					showModal:true,
					width:360,
					height:450,
	                ondestroy: function (action) {
	                	if(action=='OK'){
		                   grid.reload();
	                    }
	                }
	         });
         }else{
         	mini.alert('<fmt:message key="global.chose.justChose"/>');
         }
	  }
	  ///////////////////////////////////////////
	   function onAddBorrowPhysical(){
        var rows = grid.getSelecteds();
		if(rows.length<=1&&rows.length!=0 ){
		  	var row = grid.getSelected();
		  	mini.open({
	                url:'<c:url	value="/borrow/physical/add.action" />?entryId='+row.id,
	                title:'<fmt:message key="global.message.lending.register"/>', 
	            	iconCls :'icon-cus-folder_modernist_stuffed_add',
					allowResize : false,
					allowDrag:false,
					showModal:true,
					width:360,
					height:600,
	                ondestroy: function (action) {
	                	if(action=='OK'){
		                   grid.reload();
	                    }
	                }
	         });
         }else{
         	mini.alert('<fmt:message key="global.chose.justChose"/>');
         }
	  }
	  ///////////////////////////////////////////
	  function onAddBorrowElectFile(){
		var rows = grid.getSelecteds();
		if(rows.length<=1&&rows.length!=0 ){
		  	var row = grid.getSelected();
		  	mini.open({
	                url:'<c:url	value="/borrow/electfile/add.action" />?entryId='+row.id,
	                title:'<fmt:message key="archives.message.apply.borrow"/>', 
	            	iconCls :'icon-cus-folder_modernist_stuffed_add',
					allowResize : false,
					allowDrag:false,
					showModal:true,
					width:360,
					height:350,
	                ondestroy: function (action) {
	                	if(action=='OK'){
		                   grid.reload();
	                    }
	                }
	         });
         }else{
         	mini.alert('<fmt:message key="global.chose.justChose"/>');
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
		 formData["title"]=mini.get("#keyword").getValue();
		 grid.load(formData);
	  }
	  ///////////////////////////////////////////
	</script>
</body>
</html>