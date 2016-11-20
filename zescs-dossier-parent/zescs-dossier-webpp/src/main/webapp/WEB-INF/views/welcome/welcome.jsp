<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>欢迎进入系统</title>
<script src="<c:url value="/resources/scripts/jquery-3.0.0.min.js" />"type="text/javascript"></script>
<link href="<c:url value="/resources/css/layout.css" />" rel="stylesheet" type="text/css" />
<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet"type="text/css" />
<link href="<c:url value="/resources/css/welcome.css" />" rel="stylesheet"type="text/css" />
<script src="<c:url value="/resources/scripts/jwplayer/jwplayer.js" />" type="text/javascript"></script>
<script src="<c:url value="/resources/scripts/echarts/echarts-all.js" />" type="text/javascript"></script>
</head>

<body>
	<div id="container">
		<div class="wecome-top">
			<span>欢迎 <a href="javascript:void(0);" style="color: red;">${loginUserAdmin.nickName}</a>
				进入系统
			</span>
		</div>
		<div class="wecome-bottom">
			<div class="wecome-bottom-content">
				<!-- 上边部分 -->
				<div class="content-top">
					<h2>系统管理快捷菜单</h2>
				</div>
				<div class="content-buttom after">
					<!-- 左边用户信息 -->
					<div class="content-left after">
						<!-- 头像 -->
						<p class="content-left-hreader">
							<img src="${server_path_url}${loginUserAdmin.avatarPath}" />
						</p>
						<ul class="content-left-info">
							<li title="${user.userName }"><label class="content-left-info-label">用户名:</label><span class="content-left-info-value">${user.userName }</span></li>
							<li title="${user.nickName}"><label class="content-left-info-label">昵称:</label><span class="content-left-info-value">${user.nickName}</span></li>
							<li title="${user.name}"><label class="content-left-info-label">姓名:</label><span class="content-left-info-value">${user.name}</span></li>
							<li title="${user.ip}"><label class="content-left-info-label">IP地址:</label><span class="content-left-info-value">${user.ip }</span></li>
							<li title="${user.phone}"><label class="content-left-info-label">电话:</label><span class="content-left-info-value">${user.phone }</span></li>
							<li title="${user.email}"><label class="content-left-info-label">邮箱:</label><span class="content-left-info-value">${user.email }</span></li>
							<li title="${user.gender.genderText}"><label class="content-left-info-label">性别:</label><span class="content-left-info-value">${user.gender.genderText }</span></li>
							<li title="${user.diploma.diplomaText}"><label class="content-left-info-label">学历:</label><span class="content-left-info-value">${user.diploma.diplomaText }</span></li>
							<li title="${user.organization.branch}"><label class="content-left-info-label">部门:</label><span class="content-left-info-value">${user.organization.branch }</span></li>
							<li title="${loginUserAdmin.roleName }"><label class="content-left-info-label">主要角色:</label><span class="content-left-info-value">${loginUserAdmin.roleName }</span></li>
							<li title="${user.schoolName }"><label class="content-left-info-label">毕业学校:</label><span class="content-left-info-value">${user.schoolName }</span></li>
						</ul>
					</div>
					<!-- 中间视频部分 -->
					<div class="conent-middle">
						<div id="graphic" class="col-md-8">
			                <div id="main" style="height:520px;padding:2px;"></div>
			            </div>
					</div>
					<!-- 左边部分 -->
					<div class="content-right">
						<div class="notice_title">
							<span>通知公告</span>
							<i class="logo_right"><a href="#">更多&gt;&gt;</a></i>
						</div>
						<div class="notice_content">
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		function getNames(){
			var names = '${archives_names}';
			return $.parseJSON(names);
		}
		function getSeries(){
			var data ={};
			 $.ajax({
                url: '<c:url  value="/archives/entry/statistical/group.action" />',
				type: 'post',
                cache: false,
                async:false,
                dataType:'json',
                success: function (_data) {
                	data=_data;
                }
            });
			 return data;
		}
		option = {
			    title : {
			        text: '档案数量统计',
			        subtext: '条目统计',
			        x:'center'
			    },
			    tooltip : {
			        trigger: 'item',
			        formatter: "{a} <br/>{b} : {c} ({d}%)"
			    },
			    legend: {
			        orient : 'vertical',
			        x : 'left',
			        data:getNames()
			    },
			    toolbox: {
			        show : true,
			        feature : {
			            mark : {show: true},
			            dataView : {show: true, readOnly: false},
			            magicType : {
			                show: true, 
			                type: ['pie', 'funnel'],
			                option: {
			                    funnel: {
			                        x: '25%',
			                        width: '50%',
			                        funnelAlign: 'left',
			                        max: 1548
			                    }
			                }
			            },
			            restore : {show: true},
			            saveAsImage : {show: true}
			        }
			    },
			    calculable : true,
			    series : [
			        {
			            name:'档案条目数量',
			            type:'pie',
			            radius : '55%',
			            center: ['50%', '60%'],
			            data:getSeries()
			        }
			    ]
			};
		    var myChart = echarts.init(document.getElementById('main'));
	        myChart.setOption(option);
	</script>
</body>
</html>
