$(function(){
	$("#leftMenuTree").tree({
		onClick:function(data) {
			var menu = data.text;
			if ("用户管理" == menu) {
				goUserPage();
			}
			else if ("房源管理" == menu) {
				goHousePage();
			}
		}
	});	
});

function goUserPage () {
	$.ajax({
		type : "get",
		data : "",
		dataType : "html",
		url : $("#baseURL").val() + "/user/userPage",
		success : function(result) {
			 $("#mainPanel").html(result);
		}
	});
}

function goHousePage() {
	console.log("==goHousePage==");
	$("#mainPanel").html("12312");
}