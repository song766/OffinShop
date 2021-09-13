<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/templatemo.css">
    <link rel="stylesheet" href="css/custom.css">
    <!-- Load fonts style after rendering the layout styles -->
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Roboto:wght@100;200;300;400;500;700;900&display=swap">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Lato&family=Noto+Sans+JP&family=Roboto&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Kaushan+Script&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css">
    <script type="text/javascript" src="js/jquery-3.6.0.min.js"></script>
    <script src="js/jquery-1.11.0.min.js"></script>
    <script src="js/jquery-migrate-1.2.1.min.js"></script>
    <script src="js/bootstrap.bundle.min.js"></script>
    <script src="js/templatemo.js"></script>
    <script src="js/custom.js"></script>
<script type="text/javascript">
function idok(){
  opener.formm.id.value="${id}"; 
  opener.formm.reid.value="${id}";
  self.close();
}
function idcheck2(){
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
}
</script>
<title>Insert title here</title>
</head>
<body>
	<div id="wrap" class="container py-4 mb-4">
	  <h3>ID 중복확인</h3>
	  <form method=post name=formm id="theform" action="id_check_form" class="col-md-9 m-auto">
		<div class="row">
			<div class="input-group">
		  	<input type=text name="id" id="id" value="${id}" placeholder="ID" class="form-control"> 
		 	<!-- <input type=submit value="검색" class="btn btn-sm btn-box btn-outline-secondary"> -->
		 	<button value="검색" class="btn btn-sm btn-box btn-outline-secondary" onclick="idcheck2()">검색</button>
		  </div>
		</div> 
		    
		<div class="mt-3 fs-12">   
		  <c:if test="${message == 1}">
		    <script type="text/javascript">
		      opener.document.formm.id.value="";
		    </script>
		    ${id}는 이미 사용중인 아이디입니다.
		  </c:if>
		  <c:if test="${message==-1}">
		    ${id}는 사용 가능한 ID입니다.
		    <input type="button" value="사용" class="btn btn-box btn-outline-secondary btn-sm" onclick="idok()">
		  </c:if>
		</div>
	  </form>
	</div> 
</body>

</html>