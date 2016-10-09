$(function(){
	initBinding();
	initData();
	initTree();
	initPagination();
	getHouseList();
});
function initBinding(){
	$("#searchHouseBtn").click(function(){getHouseList()});
	$("#addHouseBtn").click(addHouse);
	$("#submitAddHouseBtn").click(submitAddHouse);
	$("#submitMdyHouseBtn").click(submitMdyHouse);
}
function initData() {
	queryDomainData("房源类型", "#hType", loadSelectData);
	queryDomainData("出租方式", "#rentType", loadSelectData);
	queryDomainData("装修程度", "#hDecation", loadSelectData);
	queryDomainData("朝向", "#hDirection", loadSelectData);
	queryDomainData("配套设施", "#supportingFacilitySpan", loadSupportingFacilityData);
	queryDomainData("付款方式", "#hRentType");
}

function queryDomainData(dType, idVal, callback) {
	var data = {};
	data.dType = dType;
	data.pageNumber = 1;
	data.pageSize = 100;
	$.ajax({
		type : "post",
		data : data,
		url : $("#baseURL").val() + "/rmsDomain/list",
		success : function(result) {
			console.log(JSON.stringify(result));
			if (null != result) {
				callback(idVal, result.results);
//				$(idVal).combobox({
//					valueField : "dValue",
//					textField : "dName",
//					data: result.results
//				});
			}
		}
	});
}
function loadSelectData(idVal, data) {
	$(idVal).combobox({
		valueField : "dValue",
		textField : "dName",
		data: data
	});
}
function loadSupportingFacilityData(idVal, data) {
	var html = "";
	for (var i = 0; i < data.length; i++) {
		html += "<input type='checkbox' id='h_sf_"+data[i].dValue+"' name='supportingFacility' value='"+data[i].dValue+"' />" + data[i].dName + "&nbsp;";
	}
	$(idVal).html(html);
}
function initTree() {
	$('#houseDataGrid').datagrid({   
	    columns:[[
	        {field:'hName',title:'小区名称',width:150,align:'center'},
	        {field:'hAddress',title:'小区地址',width:150,align:'center'},
	        {field:'hBedroomNum',title:'户型',width:100,align:'center',formatter:formatHuxing},
	        {field:'hFloor',title:'楼层',width:120,align:'center',formatter:formtLouceng},
	        {field:'hTitle',title:'房源标题',width:150,align:'center'}, 
	        {field:'hCheckinDate',title:'入住时间',width:90,align:'center', formatter:formatHCheckinDate}, 
	        {field:'hContact',title:'联系人',width:100,align:'center'}, 
	        {field:'hPhone',title:'联系方式',width:100,align:'center'}, 
	        {field:'operator',title:'操作',width:100,align:'center',formatter:formatOper} 
	    ]]   
	});  
}
function formatHuxing(val, row, index) {
	return row.hBedroomNum + "室" + row.hLivingroomNum + "厅" + row.hWashroomNum + "卫";
}
function formtLouceng(val, row, index) {
	return "第" + row.hFloor + "楼共" + row.hFloorTotal + "楼";
}
function formatOper(val, row, index){
	return '<a href="#" onclick="editHouse('+row.id+','+index+')">修改</a>&nbsp;<a href="#" onclick="delHouse('+row.id+')">删除</a>';
}
function formatHCheckinDate(value,row,index){
	var unixTimestamp = new Date(value);  
	return unixTimestamp.format("yyyy-MM-dd");  
}
function initPagination(total, pageSize) {
	if (null == total || undefined == total) {
		total = 1;
	}
	if (null == pageSize || undefined == pageSize) {
		pageSize = 10;
	}
	$('#pp').pagination({
	    total:total,
	    pageSize:pageSize,
	    onSelectPage:function(pageNumber, pageSize){
	    	setPage(pageNumber, pageSize)},
	    onRefresh:function(pageNumber, pageSize){
	    	setPage(pageNumber, pageSize)},
	    onChangePageSize: function(pageSize){
	    	changePageSize(pageSize)}
	}); 
}
var g_pageNumber = 1;
var g_pageSize = 10;
var g_doing = false;
function changePageSize(pageSize) {
	g_pageNumber = 1;
	g_pageSize = pageSize;
	
	getHouseList();
}
function setPage(pageNumber, pageSize) {
	g_pageNumber = pageNumber;
	g_pageSize = pageSize;
	getHouseList();
}
function getSearchParam(){
	var house = {};
	var hName = $("#searchHNameTxt").val();
	if (hName != '') {
		house.hName = hName;
	}
	return house;
}
function getHouseList() {
	if (g_doing) {
		return;
	}
	var data = getSearchParam();
	data.pageNumber = g_pageNumber;
	data.pageSize = g_pageSize;
	
	g_doing = true;
	
	$.ajax({
		type : "post",
		data : data,
		url : $("#baseURL").val() + "/house/list",
		success : function(result) {
			console.log(JSON.stringify(result));
			if (null != result) {
				$('#houseDataGrid').datagrid({
					data: result.results
				});
				
				initPagination(result.total, g_pageSize);
			}
			else {
				$('#houseDataGrid').datagrid({
					data: []
				});
				
				initPagination(1, g_pageSize);
			}
			
			g_doing = false;
		}
	});
}

function editHouse(houseId, index) {
	
	$("#submitAddHouseBtn").hide();
	$("#submitMdyHouseBtn").show();
	
	var house = $("#houseDataGrid").datagrid('getData').rows[index];
	
	$("#editHouseWin").panel({'title':'修改房屋'}).window("open");
	g_addOrEdit = "edit";
	
	$(".houseForm input[name='id']").val(house.id);
	$("#hType").combobox('setValue', house.hType);
	$("#rentType").combobox('setValue', house.rentType);
	$(".houseForm input[name='hName']").val(house.hName);
	$(".houseForm input[name='hAddress']").val(house.hAddress);
	$(".houseForm input[name='hBedroomNum']").val(house.hBedroomNum);
	$(".houseForm input[name='hLivingroomNum']").val(house.hLivingroomNum);
	$(".houseForm input[name='hWashroomNum']").val(house.hWashroomNum);
	$(".houseForm input[name='hArea']").val(house.hArea);
	$("#hDecation").combobox('setValue', house.hDecation);
	$("#hDirection").combobox('setValue', house.hDirection);
	$(".houseForm input[name='hFloorTotal']").val(house.hFloorTotal);
	$(".houseForm input[name='hFloor']").val(house.hFloor);
	$(".houseForm input[name='hNo']").val(house.hNo);
	$(".houseForm input[name='hRent']").val(house.hRent);
	$("#hRentType").combobox('setValue', house.hRentType);
	$(".houseForm input[name='hTitle']").val(house.hTitle);
	$(".houseForm input[name='hContent']").val(house.hContent);
	$("#hCheckinDate").datebox('setValue', new Date(house.hCheckinDate).format("yyyy-MM-dd"));
	$(".houseForm input[name='hContact']").val(house.hContact);
	$(".houseForm input[name='hPhone']").val(house.hPhone);
	$(".houseForm input[name='hLon']").val(house.hLon);
	$(".houseForm input[name='hLat']").val(house.hLat);
	
	var eles =document.getElementsByName("supportingFacility");
	
	for (var m = 0; m < eles.length; m++) {
		eles[m].checked = false;
	}
	
	for (var i = 0; i < house.supportingFacilities.length; i++) {
		var cid = 'h_sf_' + house.supportingFacilities[i].sfId;
		document.getElementById(cid).checked = true;
	}
	
}
function delHouse(id) {
	$.messager.confirm('提示','确定要删除该房屋',function(r){
		if (r) {
			$.ajax({
				type : "post",
				data : {'houseId': id},
				url : $("#baseURL").val() + "/house/del",
				success : function(result) {
					if ("200" == result) {
						$("#editHouseWin").panel({'title':'新增房屋'}).window("close");
						getHouseList();
					}
					else {
						showMessage(result);
					}
				}
			});	
		}
	});
}

g_addOrEdit = "add";
function addHouse(){
	// $(".houseForm").reset();
	$("#editHouseWin").panel({'title':'新增房屋'}).window("open");
	g_addOrEdit = "add";
	
	$("#submitAddHouseBtn").show();
	$("#submitMdyHouseBtn").hide();
	
	$(".houseForm input[name='id']").val('');
	
}

function submitAddHouse() {
//	TODO
//	if(!validForm()){
//		showMessage("请完善表单信息");
//		return;
//	};
	
	var house = getHouse();
	
	$.ajax({
		type : "post",
		data : JSON.stringify(house),
		dataType: "json",
		contentType: "application/json",
		url : $("#baseURL").val() + "/house/add",
		success : function(result) {
			if ("200" == result) {
				$("#editHouseWin").panel({'title':'新增房屋'}).window("close");
				getHouseList();
			}
			else {
				showMessage(result);
			}
		}
	});
}
function submitMdyHouse() {
//	TODO
//	if(!validForm()){
//		showMessage("请完善表单信息");
//		return;
//	};
	
	var house = getHouse();
	
	$.ajax({
		type : "post",
		data : JSON.stringify(house),
		dataType: "json",
		contentType: "application/json",
		url : $("#baseURL").val() + "/house/modify",
		success : function(result) {
			if ("200" == result) {
				$("#editHouseWin").panel({'title':'修改房屋'}).window("close");
				getHouseList();
			}
			else {
				showMessage(result);
			}
		}
	});
}
function getHouse(){
	var house = {};
	var houseid = $(".houseForm input[name='id']").val();
	house.id = houseid;
	house.hType = $("#hType").combobox('getValue');
	house.rentType = $("#rentType").combobox('getValue');
	house.hName = $(".houseForm input[name='hName']").val();
	house.hAddress = $(".houseForm input[name='hAddress']").val();
	house.hBedroomNum = $(".houseForm input[name='hBedroomNum']").val();
	house.hLivingroomNum = $(".houseForm input[name='hLivingroomNum']").val();
	house.hWashroomNum = $(".houseForm input[name='hWashroomNum']").val();
	house.hArea = $(".houseForm input[name='hArea']").val();
	house.hDecation = $("#hDecation").combobox('getValue');
	house.hDirection = $("#hDirection").combobox('getValue');
	house.hFloorTotal = $(".houseForm input[name='hFloorTotal']").val();
	house.hFloor = $(".houseForm input[name='hFloor']").val();
	house.hNo = $(".houseForm input[name='hNo']").val();
	house.hRent = $(".houseForm input[name='hRent']").val();
	house.hRentType = $("#hRentType").combobox('getValue');
	house.hTitle = $(".houseForm input[name='hTitle']").val();
	house.hContent = $(".houseForm input[name='hContent']").val();
	house.hCheckinDate = $(".houseForm input[name='hCheckinDate']").val();
	house.hContact = $(".houseForm input[name='hContact']").val();
	house.hPhone = $(".houseForm input[name='hPhone']").val();
	house.hLon = $(".houseForm input[name='hLon']").val();
	house.hLat = $(".houseForm input[name='hLat']").val();
	
	var supportingFacilities = [];
	$("#supportingFacilitySpan input").each(function(){
		if ($(this)[0].checked) {
			var sf = {};
			sf.id = null;
			sf.hId = null;
			sf.sfId = $(this).val();
			supportingFacilities[supportingFacilities.length] = sf;
		}
	});
	house.supportingFacilities = supportingFacilities;
	console.log(house);
	
	return house;
}
function validForm(){
	var id = $(".houseForm input[name='id']").val();
	if ("edit" == g_addOrEdit && isEmpty(id)) {
		showMessage("信息获取错误，请刷新页面");
	}
	
	return $(".houseForm").form('validate'); 
}