$(function() {
	layui.use('form', function() {
//		var form = layui.form;
//		// 监听提交
//		form.on('submit(login)', function(data) {
//			login(data.field);
//
//			return false;
//		});
	});
})


var login = function(param){
	$.ajax({
		type : 'post',
		url : 'login',
		dataType : 'json',
		data : param,
		success : function(data) {
			if(data.code == '0'){
				location.href = 'index'
			}else{
				layer.msg(data.msg);
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			console.info(XMLHttpRequest.status);
			console.info(XMLHttpRequest.readyStat);
			console.info(textStatus);
		}
	})
}