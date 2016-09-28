$(function(){
	initTree();
	initPagination();
//	getUserList();
});

function initTree() {
	$('#userDataGrid').datagrid({   
	    columns:[[
	        {field:'username',title:'登录名',width:200,align:'center'},
	        {field:'trueName',title:'真实姓名',width:200,align:'center'},
	        {field:'sex',title:'性别',width:100,align:'center'},
	        {field:'mobile',title:'手机号',width:200,align:'center'} 
	    ]]   
	});  
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
