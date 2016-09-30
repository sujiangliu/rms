$(function(){
	initBinding();
	initTree();
	initPagination();
	getUserList();
});
function initBinding(){
	$("#searchUserBtn").click(function(){alert("todo")});
	$("#addUserBtn").click(addUser);
	$("#submitAddUserBtn").click(submitAddUser);
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
	return '<a href="#" onclick="editUser('+row+')">修改</a>&nbsp;<a href="#" onclick="delUser('+row.id+')">删除</a>';
}
function formatBirthday(value,row,index){
	var unixTimestamp = new Date(value);  
	return unixTimestamp.format("yyyy-MM-dd");  
}
function formatDatetime(value,row,index){
	var unixTimestamp = new Date(value);  
	return unixTimestamp.format("yyyy-MM-dd hh:mm:ss");  
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

function editUser(user) {
	$("#editUserWin").panel({'title':'新增用户'}).window("open");
	g_addOrEdit = "edit";
	
	$(".userForm input[name='id']").val(user.id);
	$(".userForm input[name='username']").val(user.username);
	$(".userForm input[name='trueName']").val(user.trueName);
	$(".userForm input[name='nickName']").val(user.nickName);
	$(".userForm input[name='password']").val();
	$(".userForm input[name='mobile']").val(user.mobile);
	$(".userForm input[name='birthday']").val(user.birthday);
	$(".userForm select[name='sex']").val(user.sex);
	$(".userForm select[name='roleId']").val(user.roleId);
}
function delUser(id) {
	alert("del" + id);
}

g_addOrEdit = "add";
function addUser(){
	// $(".userForm").reset();
	$("#editUserWin").panel({'title':'新增用户'}).window("open");
	g_addOrEdit = "add";
	
	// $(".userForm input[name='password']")
}

function submitAddUser() {
	validForm();
	
	var user = {};
	user.id = $(".userForm input[name='id']").val();
	user.username = $(".userForm input[name='username']").val();
	user.trueName = $(".userForm input[name='trueName']").val();
	user.nickName = $(".userForm input[name='nickName']").val();
	user.password = $(".userForm input[name='password']").val();
	user.mobile = $(".userForm input[name='mobile']").val();
	user.birthday = $(".userForm input[name='birthday']").val();
	user.sex = $(".userForm select[comboname='sex']").val();
	user.roleId = $(".userForm select[comboname='roleId']").val();
	
	$.ajax({
		type : "post",
		data : user,
		url : $("#baseURL").val() + "/user/add",
		success : function(result) {
			if ("200" == result) {
				$("#editUserWin").panel({'title':'新增用户'}).window("close");
				getUserList();
			}
			else {
				showMessage(result);
			}
		}
	});
}
function validForm(){
	var id = $(".userForm input[name='id']").val();
	if ("edit" == g_addOrEdit && isEmpty(id)) {
		showMessage("信息获取错误，请刷新页面");
	}
//	var username = $(".userForm input[name='username']").val();
//	if (isEmpty(username)) {
//		showMessage("用户名不能为空");
//	}
	
	var trueName = $(".userForm input[name='trueName']").val();
	var nickName = $(".userForm input[name='nickName']").val();
	var password = $(".userForm input[name='password']").val();
	if ("add" == g_addOrEdit && isEmpty(password)) {
		showMessage("密码不能为空");
	}
//	var mobile = $(".userForm input[name='mobile']").val();
//	var birthday = $(".userForm input[name='birthday']").val();
//	var sex = $(".userForm select[name='sex']").val();
//	var roleId = $(".userForm select[name='roleId']").val();
}