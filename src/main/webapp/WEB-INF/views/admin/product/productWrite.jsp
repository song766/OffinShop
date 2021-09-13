<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>
<%@ include file="../sub_menu.jsp"%>

<div id="layoutSidenav_content">
   <article>
       <div class="container-fluid px-4">
       	<h1 class="mt-4">상품 등록</h1>
       	
		<form name="frm" id="write_form" method="post" enctype="multipart/form-data">
		       	 
		<div class="card mb-4">
			<div class="card-header"><i class="fas fa-list me-1"></i> Product List</div>
			<div class="card-body">
				<table class="table table-bordered" id="list"><!-- datatablesSimple -->
					<tr>
						<th>상품분류</th>
						<td colspan="5">
						    <select name="kind" id="kind">
								<c:forEach items="${kindList}" var="kind" varStatus="status">
									<option value="${status.count}">${kind}</option>
								</c:forEach>
						    </select> 
						</td>
					</tr>
					<tr>
						<th>상품명</th>
						<td colspan="5">
							<input type="text" name="name" id="name" size="47" maxlength="100" value="">
						</td>
					</tr>
					<tr>
						<th>원가</th>
						<td>
							<input type="text" name="price1" id="price1" size="11" onKeyUp='NumFormat(this)' value="">
						</td>
						<th>판매가</th>
						<td>
							<input type="text" name="price2" id="price2" size="11" onBlur="go_ab()" onKeyUp='NumFormat(this)' value="">
						</td>
						<th>수익</th>
						<td>
							<input type="text" name="price3" id="price3" size="11" readonly onKeyUp='NumFormat(this)'>
						</td>
					</tr>
					<tr>
						<th>상품상세설명</th>
						<td colspan="5">
							<textarea name="content" id="content" style="resize: none;" class="text-area"></textarea>
						</td>
					</tr>
					<tr>
						<th>상품이미지</th>
						<td colspan="5">
							<input type="file" name="product_image" id="product_image">
						</td>
					</tr>
				</table>
				
				<div class="input_group">
					<button class="btn btn-outline-secondary" type="button" onClick="go_save()">등록</button>
					<button class="btn btn-outline-secondary" type="button" onClick="go_mov()">취소</button>
				</div>
			</div>
		</div>        
		</form>
          
      </div>
  </article>
</div>


<%@ include file="../footer.jsp" %>