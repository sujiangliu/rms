<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>RMS</title>
	<jsp:include page="../../../common.jsp"></jsp:include>
	<script src="${baseURL}/common/js/menu.js"></script>
	<script src="${baseURL}/common/js/user.js"></script>
</head>
<body>
	<input id="baseURL" type="hidden" value="${baseURL }" />
	<div class="easyui-layout" style="width:100%;height:100%">
		<div data-options="region:'north'" style="height:100px">
			<jsp:include page="../common/top.jsp"></jsp:include>
		</div>
		<div data-options="region:'west',split:true" title="菜单" style="width:200px;">
			<jsp:include page="../common/leftMenu.jsp"></jsp:include>
		</div>
		<div data-options="region:'center',title:'主页面',iconCls:'icon-ok'">
			<div id="mainPanel">
				<div id="search">
					
				</div>
				<table id="userDataGrid"></table>
				<div id="pp" style="background:#efefef;border:1px solid #ccc;"></div>
				
			</div>
		</div>
	</div>
	<script type="text/javascript">
		$(".easyui-layout").height($(window).height());
	</script>
</body>
</html>
