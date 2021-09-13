<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>
<%@ include file="../sub_menu.jsp"%>

<div id="layoutSidenav_content">
   <article>
       <div class="container-fluid px-4">
       	<h1 class="mt-4">상품리스트</h1>
       	
	<form name="frm" id="prod_form" method="post">
        	
    <!-- Navbar Search -->
	<div class="d-md-inline-block form-inline ms-auto me-0 me-md-3 my-2 mb-4 col-12">
		<div class="btn-toolbar row justify-content-between">
			<div class="input-group me-2">
				<button class="btn btn-outline-secondary" type="button" name="btn_total" onclick="go_total()">전체보기</button>
				<input class="form-control" type="text" placeholder="상품명 입력" name="key" id="key" />
				<button class="btn btn-primary mr-2" type="button" name="btn_search" onclick="go_search()"><i class="fas fa-search"></i></button>
			</div>
			<div class="input-group">
				<button class="btn btn-outline-secondary" type="button" name="btn_write" onclick="go_wrt()">상품등록</button>
			</div>
		</div>
	</div>
   <!-- Navbar-->
   
   <div class="card mb-4">
       <div class="card-header"><i class="fas fa-list me-1"></i> Product List</div>
       <div class="card-body">
		<table class="table table-bordered fs-12" id="productList"><!-- datatablesSimple -->
		    <tr>
		        <th>번호</th><th>상품명</th><th>원가</th><th>판매가</th><th>등록일</th><th>신상품</th>
		    </tr>
			<c:choose>
				<c:when test="${productListSize<=0}">
					<tr><td colspan="7" align="center">등록된 상품이 없습니다.</td></tr>
				</c:when>
				<c:otherwise>
					<c:forEach items="${productList}" var="productVO">
						<tr>
							<td>${productVO.pseq}</td>
							<td>
								<!-- <a href="admin_product_detail${pageMaker.makeQuery(pageMaker.cri.pageNum)}&pseq=${productVO.pseq}">${productVO.name}</a> -->
								<a href="admin_product_detail?pseq=${productVO.pseq}" class="f-a-n text-primary">${productVO.name}</a>
							</td>
							<td><fmt:formatNumber value="${productVO.price1}"/></td>
							<td><fmt:formatNumber value="${productVO.price2}"/></td>
							<td><fmt:formatDate value="${productVO.regdate}"/></td>
							<td>
							<c:choose>
								<c:when test='${productVO.useyn=="n"}'><span class="text-useyn text-dark">NO</span></c:when>
								<c:otherwise><span class="text-useyn text-danger">NEW</span></c:otherwise>  
							</c:choose>
							</td>
						</tr>
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</table>
       </div>
       <%@ include file="page_area.jsp" %>
   </div>        
	</form>
      
      </div>
  </article>
</div>


<%@ include file="../footer.jsp" %>