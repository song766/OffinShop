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
<title>우편번호 검색</title>
<script type="text/javascript">
function result(zipnum,sido,gugun,dong) {
   opener.document.formm.zip_num.value=zipnum;
   opener.document.formm.addr1.value=sido+" "+gugun+" "+dong;
   self.close();
};
</script>
</head>
<body>
	<div id="popup" class="container py-4 mb-4">
		<h3>우편번호검색</h3>
		<form class="col-md-9 m-auto" method=post name=formm action="find_zip_num">
			<div class="row">
				<div class="input-group">
					<input type="text" name="dong" class="form-control" placeholder="동 이름">
					<input type="submit" value="검색" class="btn btn-sm btn-box btn-outline-secondary">
				</div>
			</div>
		</form>
		
		<div class="row pe-4 p-3 fs-12">
			<table id="zipcode">
				<tr>
					<th>우편번호</th>
					<th>주소</th>
				</tr>
				<c:forEach items="${addressList}" var="addressVO">
				<tr>
					<td>${addressVO.zip_num}</td>
					<td>
						<a href="#" onclick="return result('${addressVO.zip_num}'
						,'${addressVO.sido}', '${addressVO.gugun}','${addressVO.dong}')">
						${addressVO.sido} ${addressVO.gugun} ${addressVO.dong} 
						</a>
					</td>
				</tr>
				</c:forEach>
			</table>
		</div>
		
	</div>
</body>
</html>