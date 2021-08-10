var flag = true;

//生成验证码
function changeImg() {
	var imgSrc = $("#imgObj");
	var src = imgSrc.attr("src");
	alert("test!");
	imgSrc.attr("src", chgUrl(src));
}

// 时间戳
// 为了使每次生成图片不一致，即不让浏览器读缓存，所以需要加上时间戳
function chgUrl(url) {
	var timestamp = (new Date()).valueOf();
	url = url.substring(0, 20);
	if ((url.indexOf("&") >= 0)) {
		url = url + "×tamp=" + timestamp;
	} else {
		url = url + "?timestamp=" + timestamp;
	}
	return url;
}
//验证输入是否为空
function FocusItem(object) {
	if ($(object).attr('name') == 'codeValue'){
		$(object).next().next().next().html('').remove('regClass');
	}else{
		$(object).next('span').html('').remove('regClass');
	}

}
//显示验证结果
function CheckItem(object) {
	var message = $(object).next('span');
	var code = $(object).next().next().next('span');
	switch ($(object).attr('name')) {
		case "userName":
			if ($(object).val() == ""){
				message.html('用户名不能为空！');
				message.addClass('regClass');
				flag = false;
			}else{
				var url = "UserNameCheck?name="+encodeURI($(object).val())+"&"+new Date().getTime();
				$.get(url,function (date) {
					if (date == "false" ){
						$(object).next('span').html('').remove('regClass');
						flag = true;
					}else{
						message.html('用户名已经存在！');
						message.addClass('regClass');
						flag = false;
					}
				});
			}
			break;
		case "password":
			if ($(object).val() == ""){
				message.html('密码不能为空！');
				message.addClass('regClass');
				flag = false;
			}else {
				flag = true;
			}
			break;
		case "rePassword":
			if ($(object).val() == ""){
				message.html('确认密码不能为空！');
				message.addClass('regClass');
				flag = false;
			}else if ($(object).val() != $('input[name="password"]').val()){
				message.html('确认密码与密码不一致！');
				message.addClass('regClass');
				flag = false;
			}else {
				flag = true;
			}
			break;
		case "codeValue":
			if ($(object).val() == ""){
				code.html('验证码不能为空！');
				code.addClass('regClass');
				flag = false;
			}else{
				var url = "CheckCode?code="+encodeURI($(object).val())+"&"+new Date().getTime();
				$.get(url,function (result) {
					if (result == "false"){
						code.html('验证码错误！');
						code.addClass('regClass');
						flag = false;
					}else{
						code.html('').remove('regClass');
						flag = true;
					}
				});
			}
			break;
	}
}

function checkForm(object) {
	var form = object.getElementsByTagName('input');
	for (var i=0;i<form.length;i++){
		if (form[i] != null){
			if (form[i].getAttribute("onblur")){
				$.ajaxSettings.async = false;//ajax临时同步
				CheckItem(form[i]);
				$.ajaxSettings.async = true;//ajax恢复异步
				if (!flag) break;
			}
		}
	}
	return flag;
}