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
				<div class="search">
					<span class="search_item">
						<label>用户名</label>
						<span><input type="text" class="easyui-textbox" style="width:150px;height:32px"/></span>
					</span>
					<span class="search_item">
						<label>电话</label>
						<span><input type="text" class="easyui-textbox" style="width:150px;height:32px"/></span>
					</span>
					
					<a id="searchUserBtn" href="#" class="easyui-linkbutton" >搜索</a>
				</div>
				<div class="operator_area">
					<a id="addUserBtn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'">新增</a>
				</div>
				<div class="clear"></div>
				<table id="userDataGrid"></table>
				<div id="pp" style="background:#efefef;border:1px solid #ccc;"></div>
				
				<div id="editUserWin" class="easyui-window" title="用户信息" data-options="modal:true,closed:true,iconCls:'icon-save'" style="width:350px;height:500px;padding:10px;">
					<form>
						<span class="column_form">
							<label>用户名</label>
							<span><input type="text" class="easyui-textbox" style="width:250px;height:32px"/></span>
						</span>
						<span class="column_form">
							<label>真实姓名</label>
							<span><input type="text" class="easyui-textbox" style="width:250px;height:32px"/></span>
						</span>
						<span class="column_form">
							<label>真实姓名</label>
							<span><input type="text" class="easyui-textbox" style="width:250px;height:32px"/></span>
						</span>
					</form>
				</div>
				
			</div>
		</div>
	</div>
	<script type="text/javascript">
		$(".easyui-layout").height($(window).height());
	</script>
</body>
</html>
