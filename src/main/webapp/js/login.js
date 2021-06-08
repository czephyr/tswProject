function login(){
	const email = $('#email').val();
	const password = $('#password').val();
	$.ajax({
		url     : 'loginUser',
		method     : 'POST',
		data     : {email : email, password: password},
		success    : function(){
			window.location.replace("http://localhost:8080/tswProject2_war_exploded/index");
		},
		error : function(xhr, message){
			$('#logError').text("Wrong username or password");
		}
	});
}