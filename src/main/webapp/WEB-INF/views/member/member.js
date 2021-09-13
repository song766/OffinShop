/*
 * 약관동의 여부 확인
 */
function go_next(){
	if($('.agree')[0].checked == true) { // 동의함 체크 시 회원가입
		$('#join').attr('action', 'join_form').submit();
	} else if($('.agree')[1].checked == true) {
		alert("약관에 동의해 주셔야 합니다.")
	}
}


//id중복확인 화면 출력
function idcheck(){
	if ($('#id').val() == "") {
		alert("아이디를 입력해 주세요!");
		$('#id').focus();
		return;
	}
	
	if($("#id").val().length < 4 || $("#id").val().length >11) {
		alert("아이디는 4~11자 이내로 입력 가능합니다");
		$("#id").select(); // 입력한 문자 선택 상태로
		return;
	}
	
	// 아이디 중복 체크를 위한 윈도우를 open 
	var url="id_check_form?id=" + $('#id').val();
	
	window.open(url, "_blank_1", 
			"toolbar=no, menubar=no, scrollbars=no, resizable=yes, width=450, height=200");
}

//사용가능한 ID를 사용
function idok(){
	$("#theform").attr("action", "id_check_confirmed").submit();
}

/* 
* 회원가입 시, 필수 입력 사항 체크
* URL : join
*/
function go_save(){
	if($("#id").val() == ""){
		alert("아이디를 입력해주세요");
		$("#id").focus();
	} else if($("#id").val() != $("#reid").val()){
		alert("아이디 중복체크를 해주세요");
		$("#id").focus();
	} else if($("#pwd").val() == "") {
		alert("비밀번호를 입력해주세요");
		$("#pwd").focus();
	} else if($("#pwd").val() != $("#pwdCheck").val()){
		alert("비밀번호가 일치하지 않습니다.");
		$("#pwd").focus();
	} else if($("#name").val() == ""){
		alert("이름을 입력해 주세요");
		$("#name").focus();
	} else {
		$("#join").attr("action", "join").submit();
	}
}

/*
* 우편번호 찾기 창 오픈 
*/
function post_zip(){
	var url = "find_zip_num";
	
	window.open(url, "_blank_1", 
	"toolbar=no, menubar=no, scrollbars=no, resizable=yes, width=500, height=350");
}
