<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>
<%@ include file="../sub_menu.jsp"%>

<div id="layoutSidenav_content">
   <article>
       <div class="container-fluid px-4">
       	<h1 class="mt-4">상품 상세 보기</h1>
       	
		<form name="frm" id="detail_form" method="post">
		       	 
		<div class="card mb-4">
			<div class="card-header"><i class="fas fa-list me-1"></i> Product List</div>
			<div class="card-body">
				<table class="table table-bordered" id="list"><!-- datatablesSimple -->
					<tr>
						<th>상품분류</th><td colspan="5">${kind}</td>
					</tr>
					<tr>
						<th>상품명</th><td colspan="5">${productVO.name}</td>
					</tr>
					<tr>
						<th >원가</th>
						<td>${productVO.price1}</td>
						<th>판매가</th>
						<td>${productVO.price2}</td>
						<th>수익</th>
						<td>${productVO.price3}</td>
					</tr>
					<tr>
						<th>상품상세설명</th>
						<td colspan="5">${productVO.content}</td>
					</tr>
					<tr>
						<th>상품이미지</th>
						<td colspan="5">
							<!-- 상품 이미지 출력 -->
							<img src="product_images/${productVO.image}" width="200pt">
						</td>
					</tr>
				</table>
				
				<div class="input_group">
					<button class="btn btn-outline-secondary" type="button" onClick="go_mod('${productVO.pseq}')">수정</button>
					<button class="btn btn-outline-secondary" type="button" onClick="go_list('${criteria.pageNum}', '${criteria.rowsPerPage}')">목록</button>
				</div>
			</div>
		</div>        
		</form>
          
      </div>
  </article>
</div>


<%@ include file="../footer.jsp" %>