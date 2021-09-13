<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>

<!-- Start Content Page -->
<div class="container-fluid bg-light py-5">
    <div class="col-md-6 m-auto pt-3 text-center">
        <h2 class="h2">Order Detail Info</h2>
    </div>
</div>

<!-- Start Contact -->
<div class="container py-4 mb-4">
    <div class="row py-4">
        <form class="col-md-9 m-auto" name="formm" method="post">
        <h4 class="t-a-c mb-4">주문자 정보</h4>
			<table id="orderDetail" class="table t-a-c fs-12">
				<tr>
					<th>주문일자</th>
					<th>주문번호</th>
					<th>주문자</th>
					<th>총액</th>
				</tr>
				<tr>
					<td><fmt:formatDate value="${orderDetail.indate}" type="date"/></td>
					<td>${orderDetail.oseq}</td>
					<td>${orderDetail.mname}</td>
					<td><fmt:formatNumber type="currency" value="${totalPrice}" /></td>
				</tr>
			</table>
		<h4 class="mt-5 t-a-c mb-4">주문 상품 정보</h4>
			<table id="orderDetail" class="table t-a-c fs-12">
				<tr>
					<th>상품명</th>
					<th>상품별 주문번호</th>
					<th>수량</th>
					<th>가격</th>
					<th>처리 상태</th>
				</tr>
				<c:forEach items="${orderList}"  var="orderVO">
				<tr>
					<td>${orderVO.pname}</td>
					<td>${orderVO.odseq}</td>
					<td>${orderVO.quantity}</td>
					<td><fmt:formatNumber type="currency" value="${orderVO.price2*orderVO.quantity}"/></td>
					<td>
						<c:choose>
							<c:when test='${orderVO.result=="1"}'><span class="text-danger f-w-b">진행중</span></c:when>
							<c:otherwise><span class="text-primary f-w-b">처리완료</span></c:otherwise>
						</c:choose>
					</td>
				</tr>
				</c:forEach>
			</table>
			
			<div id="buttons" class="row py-4 t-a-c">
				<div class=""> 
					<input type="button" value="쇼핑 계속하기"  class="btn btn-success" onclick="location.href='index'">
					<input type="button" value="이전 목록" class="btn btn-secondary" onclick="location.href='mypage'">
				</div>
			</div>
			
        </form>
    </div>
</div>
<!-- End Contact -->

<%@ include file="../footer.jsp" %>