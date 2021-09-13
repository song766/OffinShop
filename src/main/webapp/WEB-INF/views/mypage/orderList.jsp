<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>

<!-- Start Content Page -->
<div class="container-fluid bg-light py-5">
    <div class="col-md-6 m-auto pt-3 text-center">
        <h2 class="h2">Order List</h2>
    </div>
</div>

<!-- Start Contact -->
<div class="container py-4 mb-4">
    <div class="row py-4">
        <form class="col-md-9 m-auto" name="formm" method="post">
			<table id="orderList" class="table t-a-c fs-12">
				<tr>
					<th>상품명</th>
					<th>수량</th>
					<th>가격</th>
					<th>주문일</th>
					<th>진행상태</th>
				</tr>
				<c:forEach items="${orderList}"  var="orderVO">
				<tr>
					<td>
						<a href="product_detail?pseq=${cartVO.pseq}" class="f-a-n text-secondary f-w-b">${orderVO.pname}</a>
					</td>
					<td>${orderVO.quantity}</td>
					<td><fmt:formatNumber value="${orderVO.price2*orderVO.quantity}" type="currency"/></td>
					<td><fmt:formatDate value="${orderVO.indate}" type="date"/></td>
					<td>처리진행 중</td>
				</tr>
				</c:forEach>
				<tr>
					<th colspan="2">총액</th>
					<th colspan="2"><fmt:formatNumber value="${totalPrice}" type="currency"/><br></th>
					<th class="text-danger f-w-b">주문 처리가 완료되었습니다.</th>
				</tr>
			</table>
			
			<div id="buttons" class="row py-4 t-a-c">
				<div class=""> 
					<input type="button" value="쇼핑 계속하기"  class="btn btn-success" onclick="location.href='index'">
					<input type="button" value="마이페이지" class="btn btn-secondary" onclick="location.href='mypage'">
				</div>
			</div>
			
        </form>
    </div>
</div>
<!-- End Contact -->

<%@ include file="../footer.jsp" %>