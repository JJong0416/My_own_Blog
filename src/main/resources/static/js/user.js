let index = {
	init: function() {
		$("#btn-save").on("click", () => { // btn 을 click 이벤트가 발생하면 ()=> 람다식으로 save를 호출, Function을 안쓰는 이유는 this를 바인딩 하기 위해서.
			this.save();
		});
 
	},

	save: function() {
		let data = {
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val()
		};

		//console.log(data);

		//ajax호출시 default가 비동기 호출
		// ajax 통신을 통해 3개의 데이터를 json으로 변경하여 insert 요청한다.
		//ajax가 통신을 성공하고 json으로 리턴해주면 자동으로 자바 오브젝트로 변환해준다.
		$.ajax({
			type: "POST",
			url: "/auth/joinProc",
			data: JSON.stringify(data), // 데이터를 JSON형식으로 보낸다. http body 데이터
			contentType: "application/json; charset=utf-8", // body 데이터가 어떤 타입인지
			//dataType: "json" // 요청을 서버로 해서 응답이 왔을 때, 기본적으로 모든 것이 문자열( 생긴게 json이라면) => js 오브젝트로 변경해준다.
		}).done(function(resp) { // resp는 반환값을 가져온다.
			alert("회원가입이 완료되었습니다.");
			location.href = "/";
			// 정상일때 done 실행
		}).fail(function(error) {
			alert(JSON.stringify(error));
			// 실패일떄 fail 실행
		});
	},
}

index.init();