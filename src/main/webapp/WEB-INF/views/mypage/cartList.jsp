<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  
<%@ include file="../header.jsp" %>  

<!-- Start Content Page -->
<div class="container-fluid bg-light py-5">
    <div class="col-md-6 m-auto pt-3 text-center">
        <h2 class="h2">CART</h2>
    </div>
</div>

<!-- Start Contact -->
<div class="container py-4 mb-4">
    <div class="row py-4">
        <form class="col-md-9 m-auto" name="formm" method="post" id="theform">
			<c:choose>
			<c:when test="${cartList.size() == 0}">
				<h3 class="t-a-c mb-3">장바구니가 비었습니다.</h3>
			</c:when>
			
			<c:otherwise>
				<table id="cartList" class="table t-a-c fs-12">
					<tr>
						<th>상품명</th><th>수량</th><th>가격</th><th>주문일</th><th>삭제</th>
					</tr>
					
					<c:forEach items="${cartList}" var="cartVO">
			        <tr>      
			          <td>
			            <a href="product_detail?pseq=${cartVO.pseq}" class="f-a-n">${cartVO.pname}</a>    
			          </td>
			          <td> ${cartVO.quantity} </td>
			          <td> 
			            <fmt:formatNumber value="${cartVO.price2*cartVO.quantity}" 
			                              type="currency"/> 
			          </td>      
			          <td><fmt:formatDate value="${cartVO.indate}" type="date"/></td>      
			          <td><input type="checkbox" name="cseq" value= "${cartVO.cseq}"> 
			          </td>
			        </tr>
					</c:forEach>
					
			        <tr>        
			          <th colspan="2"> 총 액 </th>
			          <th colspan="2"> 
			            <fmt:formatNumber value="${totalPrice}" type="currency"/><br>
			          </th> 
			          <th><a href="#" onclick="go_cart_delete()" class="f-a-n text-danger f-w-b">삭제하기</a></th>                       
			        </tr>
        
				</table>
			</c:otherwise>
			</c:choose>
			
			<div id="buttons" class="row py-4 t-a-c">
				<div class="">
					<input type="button" value="쇼핑 계속하기" class="btn btn-secondary" onclick="location.href='index'">
					<input type="button" value="마이페이지" class="btn btn-secondary" onclick="location.href='mypage'">      
					<c:if test= "${cartList.size() != 0}">
						<input type="button" value="주문하기"  class="btn btn-success" onclick="go_order_insert()">
					</c:if>
				</div>
			</div>
			
        </form>
    </div>
</div>
<!-- End Contact -->

<%@ include file="../footer.jsp" %>