/**
 *  아이디, 비밀번호 찾기 관련 스크립트 함수
 */
function find_id_form(){
	var url = "find_id_form";
	
	// 윈도우창 열기
	window.open(url, "_blank_1", 
	"toolbar=no, menubar=no, scrollbars=yes, resizable=no, width=550, height=500, " +
	"top=300, left=300");
}

/*
 * 아이디를 찾기 위해 find_id URL 요청 전송
 */
function findMemberId(){
	/* 이름, 이메일 입력확인*/
	if ($("#name").val() == "") {
		alert("이름을 입력해 주세요!");
		$("#name").focus();
	} else if ($("#email").val() == "") {
		alert("이메일을 입력해주세요!");
		$("#email").focus();
	} else {
		$("#findId").attr("action", "find_id").submit();
	}
}


/*
 * 아이디, 이름, 비밀번호를 입력하여 비밀번호 찾기
 */
function findPassword(){
	/* 아이디, 이름, 이메일 입력확인*/
	if($("#id2").val() =="") {
		alert("아이디를 입력해 주세요!");
		$("#id2").focus();
	} else if ($("#name2").val() == "") {
		alert("이름을 입력해 주세요!");
		$("#name2").focus();
	} else if ($("#email2").val() == "") {
		alert("이메일을 입력해주세요!");
		$("#email2").focus();
	} else {
		$("#findPW").attr("action", "find_password").submit();
	}	
}

/*
 *  비밀번호변경
 */
function changePassword(){
	if($("#pwd").val() == "") {
		alert("암호를 입력해주세요!");
		$("#pwd").focus();
	} else if($("#pwd").val() != $("#pwdcheck").val()){ // 비밀번호, 비번체크가 같지않으면
		alert("암호가 일치하지 않습니다!");
		$("#pwd").focus();
	} else {
		$("#pwd_form").attr("action", "change_password").submit();
	}
}


