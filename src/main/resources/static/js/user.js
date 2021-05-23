let index = {
	init: function(){
		$("#btn-save").on("click",()=>{ // btn 을 click 이벤트가 발생하면 ()=> 람다식으로 save를 호출
			this.save();
		});
	},
	
	save: function(){
		let data = {
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val()
		}
		
		//console.log(data);
		
		$.ajax().done().fail(); // ajax 통신을 통해 3개의 데이터를 json으로 변경하여 insert 요청한다.
	}
}

index.init();