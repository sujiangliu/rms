$(function(){
	initBinding();
	initTree();
	initPagination();
	getUserList();
});
function initBinding(){
	$("#searchUserBtn").click(function(){alert("todo")});
	$("#addUserBtn").click(addUser);
}
function initTree() {
	$('#userDataGrid').datagrid({   
	    columns:[[
	        {field:'id',title:'id',width:50,align:'center'},
	        {field:'username',title:'用户名',width:100,align:'center'},
	        {field:'trueName',title:'真实姓名',width:100,align:'center'},
	        {field:'sex',title:'性别',width:50,align:'center'},
	        {field:'mobile',title:'手机号',width:100,align:'center'}, 
	        {field:'birthday',title:'生日',width:90,align:'center', formatter:formatBirthday}, 
	        {field:'createTime',title:'创建时间',width:130,align:'center',formatter:formatDatetime}, 
	        {field:'operator',title:'操作',width:100,align:'center',formatter:formatOper} 
	    ]]   
	});  
}
function formatOper(val, row, index){
	return '<a href="#" onclick="editUser('+row.id+')">修改</a>&nbsp;<a href="#" onclick="delUser('+row.id+')">删除</a>';
}
function formatBirthday(value,row,index){
	var unixTimestamp = new Date(value);  
	return unixTimestamp.format("yyyy-MM-dd");  
}
function formatDatetime(value,row,index){
	var unixTimestamp = new Date(value);  
	return unixTimestamp.format("yyyy-MM-dd hh:mm:ss");  
}
function editUser(id) {
	alert("edit" + id);
}
function delUser(id) {
	alert("del" + id);
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
	
	getUserList();
}
function setPage(pageNumber, pageSize) {
	g_pageNumber = pageNumber;
	g_pageSize = pageSize;
	getUserList();
}

function getUserList() {
	if (g_doing) {
		return;
	}
	var data = {};
	data.pageNumber = g_pageNumber;
	data.pageSize = g_pageSize;
	
	g_doing = true;
	
	$.ajax({
		type : "post",
		data : data,
		url : $("#baseURL").val() + "/user/list",
		success : function(result) {
			console.log(JSON.stringify(result));
			if (null != result) {
				$('#userDataGrid').datagrid({
					data: result.results
				});
				
				initPagination(result.total, g_pageSize);
			}
			else {
				$('#userDataGrid').datagrid({
					data: []
				});
				
				initPagination(1, g_pageSize);
			}
			
			g_doing = false;
		}
	});
}
function addUser(){
	$("#editUserWin").panel({'title':'新增用户'}).window("open");
}
