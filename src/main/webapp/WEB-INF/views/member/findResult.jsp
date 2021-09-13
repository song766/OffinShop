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
<title>아이디 찾기</title>
<script type="text/javascript">
function idok(){
  self.close();
}
</script>
</head>
<body>
	<div id="wrap" class="container py-4 mb-4">
	  <h3>ID 찾기 결과</h3>
	  <form class="col-md-9 m-auto" method=post name=formm action="id_check_form" >
	    <input type=text name="id" value="${id}" placeholder="User ID">   
	    <div class="mt-3">   
	      <c:if test="${message == 1}">
	        <script type="text/javascript">
	          opener.document.formm.id.value="";
	        </script>
	             요청하신 ID는 ${id}입니다.
	      </c:if>
	      <c:if test="${message==-1}">
	             가입하지 않은 ID입니다.
	      </c:if>
	    </div>
	    <input type="button" value="확인" class="btn btn-sm btn-box btn-outline-secondary" onclick="idok()">
	  </form>
	</div>  
</body>
</html>