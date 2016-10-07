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
						<label>小区名称</label>
						<span><input id="searchHNameTxt" type="text" class="easyui-textbox" style="width:150px;height:32px"/></span>
					</span>
					<a id="searchHouseBtn" href="#" class="easyui-linkbutton" >搜索</a>
				</div>
				<div class="operator_area">
					<a id="addHouseBtn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'">新增</a>
				</div>
				<div class="clear"></div>
				<table id="houseDataGrid"></table>
				<div id="pp" style="background:#efefef;border:1px solid #ccc;"></div>
				
				<div id="editHouseWin" class="easyui-window" title="房屋信息" data-options="modal:true,closed:true,iconCls:'icon-save'" style="width:450px;height:600px;padding:10px;">
					<form class="houseForm">
						<input type="hidden" name="id" />
						<span class="column_form">
							<label>房屋类型</label>
							<span>
								<select id="hType" name="hType" class="easyui-combobox" data-options="required:true" style="width:200px;height:32px;">
								</select>
							</span>
						</span>
						<span class="column_form">
							<label>出租方式</label>
							<span>
								<select id="rentType" name="rentType" class="easyui-combobox" data-options="required:true" style="width:200px;height:32px;">
								</select>
							</span>
						</span>
						<span class="column_form">
							<label>小区名称</label>
							<span><input name="hName" type="text" class="easyui-validatebox" data-options="required:true" style="width:200px;height:32px"/></span>
						</span>
						<span class="column_form">
							<label>小区地址</label>
							<span><input name="hAddress" type="text" class="easyui-validatebox" data-options="required:true" style="width:200px;height:32px"/></span>
						</span>
						<span class="column_form">
							<label>户型</label>
							<span>
								<input name="hBedroomNum" type="text" class="easyui-validatebox" data-options="required:true" style="width:40px;height:32px"/>室
								<input name="hLivingroomNum" type="text" class="easyui-validatebox" data-options="required:true" style="width:40px;height:32px"/>厅
								<input name="hWashroomNum" type="text" class="easyui-validatebox" data-options="required:true" style="width:40px;height:32px"/>卫
								面积：<input name="hArea" type="text" class="easyui-validatebox" data-options="required:true" style="width:40px;height:32px"/>平米
							</span>
						</span>
						<span class="column_form">
							<label>装修</label>
							<span>
								<select id="hDecation" name="hDecation" class="easyui-combobox" data-options="required:true" style="width:200px;height:32px;">
								</select>
							</span>
						</span>
						<span class="column_form">
							<label>房屋朝向</label>
							<span>
								<select id="hDirection" name="hDirection" class="easyui-combobox" data-options="required:true" style="width:200px;height:32px;">
								</select>
							</span>
						</span>
						<span class="column_form">
							<label>楼栋号</label>
							<span>
								<input name="hNo" type="text" class="easyui-validatebox" data-options="required:true" style="width:40px;height:32px"/>号楼
								第<input name="hFloor" type="text" class="easyui-validatebox" data-options="required:true" style="width:40px;height:32px"/>楼
								共<input name="hFloorTotal" type="text" class="easyui-validatebox" data-options="required:true" style="width:40px;height:32px"/>楼
							</span>
						</span>
						<span class="column_form">
							<label>租金</label>
							<span>
								<input id="hRent" name="hRent" class="easyui-validatebox" data-options="required:true" style="width:40px;height:32px;"/>元/
								<input id="hRentType" name="hRentType" class="easyui-validatebox" data-options="required:true" style="width:100px;height:32px;"/>
							</span>
						</span>
						<span class="column_form">
							<label>房源标题</label>
							<span><input name="hTitle" type="text" class="easyui-validatebox" data-options="required:true" style="width:200px;height:32px"/></span>
						</span>
						<span class="column_form">
							<label>房源描述</label>
							<span><input name="hContent" type="text" class="easyui-validatebox" data-options="required:true" style="width:200px;height:32px"/></span>
						</span>
						<span class="column_form">
							<label>入住时间</label>
							<span><input id="hCheckinDate" name="hCheckinDate" class="easyui-datebox" data-options="required:true" style="width:200px;height:32px;"/></span>
						</span>
						<span class="column_form">
							<label>联系人</label>
							<span><input name="hContact" type="text" class="easyui-validatebox" data-options="required:true" style="width:200px;height:32px"/></span>
						</span>
						<span class="column_form">
							<label>联系电话</label>
							<span><input name="hPhone" type="text" class="easyui-validatebox" data-options="required:true" style="width:200px;height:32px"/></span>
						</span>
						<span class="column_form">
							<label>&nbsp;</label>
							<span>
								经度：<input name="hLon" type="text" class="easyui-validatebox" data-options="required:true" style="width:40px;height:32px"/>
								纬度：<input name="hLat" type="text" class="easyui-validatebox" data-options="required:true" style="width:40px;height:32px"/>
							</span>
						</span>
						<a id="submitAddHouseBtn" href="#" class="easyui-linkbutton" >新增</a>
						<a id="submitMdyHouseBtn" href="#" class="easyui-linkbutton" >修改</a>
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
	<script src="${baseURL}/common/js/house.js"></script>