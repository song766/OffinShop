<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Offin Admin</title>

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
    
    <link rel="stylesheet" href="admin/css/admin.css">

    <script type="text/javascript" src="js/jquery-3.6.0.min.js"></script>
    <script src="js/jquery-1.11.0.min.js"></script>
    <script src="js/jquery-migrate-1.2.1.min.js"></script>
    <script src="js/bootstrap.bundle.min.js"></script>
    <script src="js/templatemo.js"></script>
    <script src="js/custom.js"></script>    

<script type="text/javascript">
function worker_check()
{
  if(document.frm.workId.value==""){
      alert("아이디를 입력하세요.");
      return false;
  }else if(document.frm.workPw.value==""){
     alert("비밀번호를 입력하세요.");
      return false;
    }
    return true;  
}
</script>
</head>

<body class="c-app flex-row align-items-center">

<div id="wrap" class="container">
	<article class="row justify-content-center">
		<div class="col-md-8">
			<div class="card-group">
				<div class="card p-4">
					<div id="loginform" class="card-body">
						<form name="frm" method="post" action="admin_login">
							<h1 class="mb-4">Admin Login</h1>
			                <div class="row">
			                    <div class="form-group col-md-6 mb-3">
			                        <label for="inputid">ID</label>
			                        <input type="text" class="form-control mt-1" id="workerId" name="workerId" value="admin1">
			                    </div>
				                <div class="form-group col-md-6 mb-3">
				                    <label for="inputpassword">PASSWORD</label>
				                    <input type="password" class="form-control mt-1" id="workerPwd" name="workerPwd" value="1234">
				                </div>
			                </div>
			                <div class="row">
			                    <div class="col text-center mt-2">
			                    	<!--<input class="btn" type="submit" value="업무 로그인" onclick="return worker_check()">-->
			                        <input class="btn btn-success btn-lg btn-full mb-2" type="submit" onclick="return worker_check()" value="업무 로그인"/>
			                        <p class="fs-12 text-danger">${message}</p>
			                    </div>
			                </div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</article>
</div>
  
</body>
</html>