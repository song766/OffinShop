<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>비밀번호 변경</title>
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
<style>
label {width:120px; margin-right:5px;}
.input-group {margin-bottom:2px;}
</style>
 
<script type="text/javascript" src="js/jquery-3.6.0.min.js"></script>
<script src="js/jquery-1.11.0.min.js"></script>
<script src="js/jquery-migrate-1.2.1.min.js"></script>
<script src="js/bootstrap.bundle.min.js"></script>
<script src="js/templatemo.js"></script>
<script src="js/custom.js"></script>
<script type="text/javascript" src="member/findIdAndPassword.js"></script>
<script type="text/javascript">
function idok(){
  self.close();
}
</script>

</head>
<body>
	<div id="wrap" class="container py-4 mb-4">
		<h3>비밀번호 변경</h3>
		<form method=post name=formm id="pwd_form" class="col-md-9 m-auto">
			<div class="row">
				<div class="input-group">
					<label>아이디</label>
					<input type=text name="id" value="${id}" class="form-control">
				</div>   
				<div style="margin-top: 20px">
					<c:if test="${message == 1}">
						<div class="input-group">
							<label>비밀번호</label> 
							<input type="password"  name="pwd" id="pwd" class="form-control"> 
						</div>
						<div class="input-group">
							<label>비밀번호 변경</label> 
							<input type="password"  name="pwdCheck" id="pwdcheck" class="form-control">
						</div>
						<div class="input-group mt-2">
							<input type="button" value="확인" class="btn btn-box btn-outline-secondary" onclick="changePassword()">
						</div>
					</c:if>
					<c:if test="${message==-1}">
						잘못된 사용자 정보입니다.
						<input type="button" value="확인" class="cancel" onclick="idok()">
					</c:if>
				</div>
		    </div>
		</form>
	</div>  
</body>
</html>