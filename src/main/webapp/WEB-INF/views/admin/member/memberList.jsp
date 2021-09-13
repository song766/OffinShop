<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>
<%@ include file="../sub_menu.jsp"%>
<script type="text/javascript">
  function go_search(name)
  {
     //document.frm.action="admin_member_list?key="+name;
     document.frm.action="admin_member_list";
     document.frm.submit();
  }
  
  function go_member_total(){
		$("#key").val("");
		$("#prod_form").attr("action", "admin_member_list").submit();
  }
  
  function go_member_search() {
	var theForm = document.frm;
	theForm.action =  "admin_member_list";
	theForm.submit();
  }
</script>

<div id="layoutSidenav_content">
   <article>
       <div class="container-fluid px-4">
       	<h1 class="mt-4">회원리스트</h1>
       	
	<form name="frm" method="post" id="prod_form">

    <!-- Navbar Search -->
	<div class="d-md-inline-block form-inline ms-auto me-0 me-md-3 my-2 mb-4 col-12">
		<div class="btn-toolbar row justify-content-between">
			<div class="input-group me-2">
				<button class="btn btn-outline-secondary" type="button" name="btn_total" onclick="go_member_total()">전체보기</button>
				<input class="form-control" type="text" placeholder="회원명 입력" name="key" id="key" />
				<button class="btn btn-primary mr-2" type="button" name="btn_search" onclick="go_member_search(this.key)"><i class="fas fa-search"></i></button>
			</div>
		</div>
	</div>
   <!-- Navbar-->
   
   <div class="card mb-4">
       <div class="card-header"><i class="fas fa-list me-1"></i> Member List</div>
       <div class="card-body">
		<table class="table table-bordered fs-12" id="memberList">
		    <tr>
		        <th>아이디(탈퇴여부)</th>
		        <th>이름</th>
		        <th>이메일</th>
		        <th>우편번호</th>
		        <th>주소</th>
		        <th>전화</th>
		        <th>가입일</th>
		    </tr>
		    <c:forEach items="${memberList}" var="memberVO">
		    <tr>
				<td>${memberVO.id} 
					<c:choose>
						<c:when test='${memberVO.useyn=="y"}'>
							<input type="checkbox" name="useyn" disabled="disabled" class="form-check-input">
						</c:when>
						<c:otherwise>
							<input type="checkbox" name="useyn" checked="checked" disabled="disabled" class="form-check-input">
						</c:otherwise>
					</c:choose>
				</td>
				<td>${memberVO.name}</td>
				<td>${memberVO.email}</td> 
				<td>${memberVO.zip_num}</td>
				<td>${memberVO.address}</td>
				<td>${memberVO.phone}</td> 
				<td><fmt:formatDate value="${memberVO.regdate}"/></td>
		    </tr>
		    </c:forEach>
		</table>
       </div>
       
   </div>        
	</form>
      
      </div>
  </article>
</div>

<%@ include file="../footer.jsp" %>