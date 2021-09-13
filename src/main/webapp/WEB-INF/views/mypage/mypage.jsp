<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>

<!-- Start Content Page -->
<div class="container-fluid bg-light py-5">
    <div class="col-md-6 m-auto pt-3 text-center">
        <h2 class="h2">My Page</h2>
    </div>
</div>

<!-- Start Contact -->
<div class="container py-4 mb-4">
    <div class="row py-4">
        <form class="col-md-9 m-auto" name="formm" method="post">
			<table id="cartOrder" class="table t-a-c fs-12">
				<tr>
					<th>주문일자</th>
					<th>주문번호</th>
					<th>상품명</th>
					<th>결제금액</th>
					<th>주문상세</th>
				</tr>
				<c:forEach items="${orderList}" var="orderVO">
				<tr>
					<td><fmt:formatDate value="${orderVO.indate}" type="date"/></td>
					<td>${orderVO.oseq}</td>
					<td>${orderVO.pname}</td>
					<td><fmt:formatNumber value="${orderVO.price2}" type="currency"/></td>
					<td><a href="order_detail?oseq=${orderVO.oseq}" class="f-a-n text-primary f-w-b">조회</a></td>
				</tr>
				</c:forEach>
			</table>
			
			<div id="buttons" class="row py-4 t-a-c">
				<div class=""> 
					<input type="button" value="쇼핑 계속하기"  class="btn btn-success" onclick="location.href='index'">
				</div>
			</div>
			
        </form>
    </div>
</div>
<!-- End Contact -->

<%@ include file="../footer.jsp" %>