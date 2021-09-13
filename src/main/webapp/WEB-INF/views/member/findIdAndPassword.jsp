<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	<script type="text/javascript" src="member/findIdAndPassword.js"></script>
	<style>
	label{width:70px; margin-right:5px;}
	.input-group {margin-bottom:2px;}
	</style>
  </head>
  <body>
  <div id="wrap" class="container py-4 mb-4">
  	<h3>아이디 찾기</h3>
	<form name="findId" id="findId" action="find_id" method="get" class="col-md-9 m-auto">
	<div class="row">
		<div class="input-group">
			<label>이름 </label>
			<input type="text" name="name" id="name" value="" class="form-control">
		</div>	
		<div class="input-group">
			<label>이메일 </label>
			<input type="text" name="email" id="email" value="" class="form-control"><br>
		</div>
		<div class="input-group mt-2">
			<input type="button" value="아이디 찾기" onclick="findMemberId()" class="btn btn-box btn-outline-secondary">
		</div>
	</div>
	</form>
	
	<br>
	
	<h3>비밀번호 찾기</h3>
	<form name="findPW" id="findPW">
	<div class="row">
		<div class="input-group">
			<label>아이디</label>
			<input type="text" name="id" id="id2" value="" class="form-control">
		</div>	
		<div class="input-group">
			<label>이름</label>
			<input type="text" name="name" id="name2" value="" class="form-control">
		</div>		
		<div class="input-group">
			<label>이메일</label>
			<input type="text" name="email" id="email2" value="" class="form-control">
		</div>	
		<div class="input-group mt-2">
			<input type="button" value="비밀번호 찾기" onclick="findPassword()" class="btn btn-box btn-outline-secondary">
		</div>	
	</div>
	</form>
	</div>
</body>
</html>