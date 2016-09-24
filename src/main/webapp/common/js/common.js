function showMessage(content, time) {
	if (null == time) {
		time = 1500;
	}
	$.dialog({
		content : content,
		title : '',
		time : time,
	});
}