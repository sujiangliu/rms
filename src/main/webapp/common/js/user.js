$(function(){
	initTree();
	getUserList();
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

function getUserList () {
	
	$.ajax({
		type : "post",
		data : data,
		url : $("#baseURL").val() + "/user/list",
		success : function(result) {
			if (null != result) {
				$('#userDataGrid').datagrid({
					data: result
				});
			}
			else {
				$('#userDataGrid').datagrid({
					data: []
				});
			}
		}
	});

	
}
