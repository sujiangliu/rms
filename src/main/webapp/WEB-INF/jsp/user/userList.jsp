<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>RMS</title>
	<jsp:include page="../../../common.jsp"></jsp:include>
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
						<span><input id="searchUsernameTxt" type="text" class="easyui-textbox" style="width:150px;height:32px"/></span>
					</span>
					<span class="search_item">
						<label>电话</label>
						<span><input id="searchMobileTxt" type="text" class="easyui-textbox" style="width:150px;height:32px"/></span>
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
					<form class="userForm">
						<input type="hidden" name="id" />
						<span class="column_form">
							<label>用户名</label>
							<span><input name="username" type="text" class="easyui-validatebox" data-options="required:true" style="width:200px;height:32px"/></span>
						</span>
						<span class="column_form">
							<label>真实姓名</label>
							<span><input name="trueName" type="text" class="easyui-validatebox" data-options="required:true" style="width:200px;height:32px"/></span>
						</span>
						<span class="column_form">
							<label>昵称</label>
							<span><input name="nickName" type="text" class="easyui-validatebox" data-options="required:true" style="width:200px;height:32px"/></span>
						</span>
						<span class="column_form">
							<label>手机号码</label>
							<span><input name="mobile" type="text" class="easyui-validatebox" data-options="required:true" style="width:200px;height:32px"/></span>
						</span>
						<span class="column_form">
							<label>密码</label>
							<span><input name="password" type="password" class="easyui-textbox" style="width:200px;height:32px"/></span>
						</span>
						<span class="column_form">
							<label>性别</label>
							<span>
								<select name="sex" class="easyui-combobox" data-options="required:true" style="width:200px;">
								    <option value="男">男</option>
								    <option value="女">女</option>
								</select>
							</span>
						</span>
						<span class="column_form">
							<label>生日</label>
							<span>
								<input id="birthday" name="birthday" class="easyui-datebox" data-options="required:true" style="width:200px;"/>
							</span>
						</span>
						<span class="column_form">
							<label>角色</label>
							<span>
								<select class="easyui-combobox" name="roleId" data-options="required:true" style="width:200px;" >
								    <option value="1" style="width:100%;">管理员</option>
								    <option value="2">内部员工</option>
								    <option value="3">用户</option>
								</select>
							</span>
						</span>
						<a id="submitAddUserBtn" href="#" class="easyui-linkbutton" >新增</a>
						<a id="submitMdyUserBtn" href="#" class="easyui-linkbutton" >修改</a>
					</form>
				</div>
				
			</div>
		</div>
	</div>
</body>
</html>

	<script type="text/javascript">
		$(".easyui-layout").height($(window).height());
	</script>
	<script src="${baseURL}/common/js/menu.js"></script>
	<script src="${baseURL}/common/js/user.js"></script>