function registration(){
	const name = $('#name').val();
	const surname = $('#surname').val();
	const email = $('#regEmail').val();
	const password = $('#regPassword').val();
	const re = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	const pswre = /((?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,})/;

	if(name.length <3){
		document.getElementById('nameInvalid').removeAttribute("hidden");
		document.getElementById('name').classList.add("is-invalid");
		return;
	}else{
		document.getElementById('name').classList.remove("is-invalid");
	}

	if(surname.length <3){
		document.getElementById('surnameInvalid').removeAttribute("hidden");
		document.getElementById('surname').classList.add("is-invalid");

		return;
	}else{
		document.getElementById('surname').classList.remove("is-invalid");
	}

	if(!(re.test(String(email).toLowerCase()))){
		document.getElementById('emailInvalid').removeAttribute("hidden");
		document.getElementById('regEmail').classList.add("is-invalid");
		return;
	}else{
		document.getElementById('regEmail').classList.remove("is-invalid");
	}

	if(!(pswre.test(String(password)))){
		document.getElementById('passwordInvalid').removeAttribute("hidden");
		document.getElementById('regPassword').classList.add("is-invalid");
		return;
	}else{
		document.getElementById('regPassword').classList.remove("is-invalid");
	}

	$.ajax({
		url     : 'registerUser',
		method     : 'POST',
		data     : {name: name, surname: surname, email : email, password: password},
		success    : function(){
			window.location.replace("http://localhost:8080/tswProject2_war_exploded/index");
		},
		error : function(xhr, message){
			alert("Your email is already registered. Please use another one.")
		}
	});
}