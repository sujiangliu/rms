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
				
				<div id="editHouseWin" class="easyui-window" title="房屋信息" data-options="modal:true,closed:true,iconCls:'icon-save'" style="width:600px;height:650px;padding:10px;">
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
								<input id="hRentType" name="hRentType" class="easyui-combobox" data-options="required:true" style="width:100px;height:32px;"/>
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
							<label>配套设施</label>
							<span id="supportingFacilitySpan" style="width:300px;line-height:25px;"></span>
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
						<span class="column_form">
							<label>上传图片</label>
							<span>
								<input type="hidden" id="houseImgSrc" name="houseImgSrc" /> 
								<form>
									<div style="display: inline; border: solid 1px #7FAAFF;padding: 2px;">
										<span id="spanButtonPlaceholder"></span>
										<input id="btnUpload" type="button" value="上  传"
											onclick="startUploadFile();" class="btn3_mouseout" 
											/>
										<input id="btnCancel" type="button" value="取消所有上传"
											onclick="cancelUpload();" disabled="disabled" class="btn3_mouseout" 
											/>
									</div>
								</form>
								<div id="divFileProgressContainer"></div>
								<div id="thumbnails">
									<table id="infoTable" border="0" width="400" style="display: inline; padding: 2px;margin-top:8px;">
									</table>
								</div>
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
	
	<script type="text/javascript" src="${baseURL}/common/swfupload/swfupload.js"></script>
	<script type="text/javascript" src="${baseURL}/common/swfupload/swfupload.queue.js"></script>
	<script type="text/javascript" src="${baseURL}/common/swfupload/handlers.js"></script>
	
	<script type="text/javascript">
		var swfu;
		window.onload = function () {
			swfu = new SWFUpload({
				upload_url: "${baseURL}/upload",
				post_params: {"name" : "zwm"},
				use_query_string:true,
				// File Upload Settings
				file_size_limit : "1 MB",	// 文件大小控制
				file_types : "*.jpg;*.png;*.gif",
				file_types_description : "All Files",
				file_upload_limit : "0",
								
				file_queue_error_handler : fileQueueError,
				file_dialog_complete_handler : fileDialogComplete,//选择好文件后提交
				file_queued_handler : fileQueued,
				upload_progress_handler : uploadProgress,
				upload_error_handler : uploadError,
				upload_success_handler : uploadSuccess,
				upload_complete_handler : uploadComplete,
				button_placeholder_id : "spanButtonPlaceholder",
				button_width: 100,
				button_height: 18,
				button_text : '<span class="button">请选择文件 </span>',
				button_text_style : '.button { font-family: Helvetica, Arial, sans-serif; font-size: 12pt; } .buttonSmall { font-size: 10pt; }',
				button_text_top_padding: 0,
				button_text_left_padding: 18,
				button_window_mode: SWFUpload.WINDOW_MODE.TRANSPARENT,
				button_cursor: SWFUpload.CURSOR.HAND,
				// Flash Settings
				flash_url : "${baseURL}/common/swfupload/swfupload.swf",
				custom_settings : {
					upload_target : "divFileProgressContainer"
				},
				// Debug Settings
				debug: false  //是否显示调试窗口
			});
		};
		function startUploadFile(){
			swfu.startUpload();
		}
		
	</script>