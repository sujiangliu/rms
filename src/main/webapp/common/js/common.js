function showMessage(content, time) {
	if (null == time) {
		time = 1500;
	}
//	$.dialog({
//		content : content,
//		title : '',
//		time : time,
//	});
//	
	$.messager.alert('提示',content);
}

function isEmpty(str) {
	if (null == str || undefined == str || str.length <= 0) {
		return true;
	}
	
	return false;
}