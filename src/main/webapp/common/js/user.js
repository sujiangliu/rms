$(function(){
	initBinding();
	initTree();
	initPagination();
	getUserList();
});
function initBinding(){
	$("#searchUserBtn").click(function(){getUserList()});
	$("#addUserBtn").click(addUser);
	$("#submitAddUserBtn").click(submitAddUser);
	$("#submitMdyUserBtn").click(submitMdyUser);
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
	return '<a href="#" onclick="editUser('+row.id+','+index+')">修改</a>&nbsp;<a href="#" onclick="delUser('+row.id+')">删除</a>';
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

function getQueryParam() {
	
	var user = {};
	var s_username = $("#searchUsernameTxt").val();
	if (s_username != '') {
		user.username = s_username;
	}
	var s_mobile = $("#searchMobileTxt").val();
	if (s_mobile != '') {
		user.mobile = s_mobile;
	}
	return user;
}
function getUserList() {
	if (g_doing) {
		return;
	}
	var data = getQueryParam();
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

function editUser(userId, index) {
	
	$("#submitAddUserBtn").hide();
	$("#submitMdyUserBtn").show();
	
	var user = $("#userDataGrid").datagrid('getData').rows[index];
	
	$("#editUserWin").panel({'title':'修改用户'}).window("open");
	g_addOrEdit = "edit";
	
	$(".userForm input[name='id']").val(user.id);
	$(".userForm input[name='username']").val(user.username);
	$(".userForm input[name='trueName']").val(user.trueName);
	$(".userForm input[name='nickName']").val(user.nickName);
	$(".userForm input[name='password']").val();
	$(".userForm input[name='mobile']").val(user.mobile);
	//$(".userForm input[name='birthday']").val(user.birthday);
	$("#birthday").datebox('setValue', new Date(user.birthday).format("yyyy-MM-dd"));
	$(".userForm select[name='sex']").val(user.sex);
	$(".userForm select[name='roleId']").val(user.roleId);
}
function delUser(id) {
	$.messager.confirm('提示','确定要删除该用户',function(r){
		if (r) {
			$.ajax({
				type : "post",
				data : {'userId': id},
				url : $("#baseURL").val() + "/user/del",
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
	});
}

g_addOrEdit = "add";
function addUser(){
	// $(".userForm").reset();
	$("#editUserWin").panel({'title':'新增用户'}).window("open");
	g_addOrEdit = "add";
	
	$("#submitAddUserBtn").show();
	$("#submitMdyUserBtn").hide();
	
	$(".userForm input[name='id']").val('');
	
}

function submitAddUser() {
	validForm();
	
	var user = getUser();
	
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
function submitMdyUser() {
	validForm();
	
	var user = getUser();
	
	$.ajax({
		type : "post",
		data : user,
		url : $("#baseURL").val() + "/user/modify",
		success : function(result) {
			if ("200" == result) {
				$("#editUserWin").panel({'title':'修改用户'}).window("close");
				getUserList();
			}
			else {
				showMessage(result);
			}
		}
	});
}
function getUser(){
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
	return user;
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