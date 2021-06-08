$(".card").click(function(){
	window.location.href = "http://localhost:8080/tswProject2_war_exploded/productPageServlet?ID="+this.id;
});