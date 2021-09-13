<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>
<%@ include file="../sub_menu.jsp"%>

<div id="layoutSidenav_content">
   <article>
       <div class="container-fluid px-4">
       	<h1 class="mt-4">상품 수정</h1>
       	
		<form name="frm" id="update_form" method="post" enctype="multipart/form-data">
		<input type="hidden" name="pseq" value="${productVO.pseq}">
		<input type="hidden" name="code" >
		<input type="hidden" name="nonmakeImg" value="${productVO.image}">
		       	 
		<div class="card mb-4">
			<div class="card-header"><i class="fas fa-list me-1"></i> Product List</div>
			<div class="card-body">
				<table class="table table-bordered" id="list"><!-- datatablesSimple -->
					<tr>
						<th>상품분류</th>
						<td colspan="5">
						    <select name="kind" id="kind">
						      <c:forEach items="${kindList}" var="kind" varStatus="status">
						        <c:choose>
						          <c:when test="${productVO.kind==status.count}">
						            <option value="${status.count}" selected="selected">${kind}</option>
						          </c:when>
						          <c:otherwise>
						            <option value="${status.count}">${kind}</option>
						          </c:otherwise>
						        </c:choose>
						      </c:forEach>
						    </select> 
						</td>
					</tr>
					<tr>
						<th>상품명</th>
						<td colspan="5">
							<input type="text" name="name" id="name" size="47" maxlength="100" value="${productVO.name}">
						</td>
					</tr>
					<tr>
						<th>원가</th>
						<td>
							<input type="text" name="price1" id="price1" onKeyUp='NumFormat(this)' value="${productVO.price1}">
						</td>
						<th>판매가</th>
						<td>
							<input type="text" name="price2" id="price2" onBlur="go_ab()" onKeyUp='NumFormat(this)' value="${productVO.price2}">
						</td>
						<th>수익</th>
						<td>
							<input type="text" name="price3" id="price3" readonly onKeyUp='NumFormat(this)'>
						</td>
					</tr>
					<tr>
						<th>베스트상품</th>
						<td>
					      <c:choose>
					        <c:when test='${productVO.bestyn=="y"}'>
					          <input type="checkbox" name="bestyn" value="y" id="bestyn" checked="checked">
					        </c:when>
					        <c:otherwise>
					          <input type="checkbox" name="bestyn" value="n" id="bestyn" >
					        </c:otherwise>
					      </c:choose>
						</td>
						<th>신상품</th>
						<td>
							<c:choose>
								<c:when test='${productVO.useyn=="y"}'>
									<input type="checkbox" name="useyn" id="useyn" value="y" checked="checked">
								</c:when>
								<c:otherwise>
									<input type="checkbox" name="useyn" id="useyn" value="n">
								</c:otherwise>
							</c:choose>
						</td>
					</tr>
					<tr>
						<th>상품상세설명</th>
						<td colspan="5">
							<textarea name="content" id="content" style="resize: none;" class="text-area">${productVO.content}</textarea>
						</td>
					</tr>
					<tr>
						<th>상품이미지</th>
						<td colspan="5">
							<img src="product_images/${productVO.image}" width="200px">
							<br>
							<input type="file" name="product_image" id="product_image">
							<input type="hidden" name="image" value="${productVO.image}">
						</td>
					</tr>
				</table>
				
				<div class="input_group">
					<button class="btn btn-outline-secondary" type="button" onClick="go_mod_save('${productVO.pseq}')">수정</button>
					<button class="btn btn-outline-secondary" type="button" onClick="go_mov()">취소</button>
				</div>
			</div>
		</div>        
		</form>
          
      </div>
  </article>
</div>


<%@ include file="../footer.jsp" %>