<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>
<%@ include file="../sub_menu.jsp"%>
<script type="text/javascript">
function go_view(qseq) {
    var theForm = document.frm;
    theForm.qseq.value = qseq;
    theForm.action = "admin_qna_detail";
    theForm.submit();
  }  
</script>

<div id="layoutSidenav_content">
   <article>
       <div class="container-fluid px-4">
       	<h1 class="mt-4 mb-4">Q&amp;A 게시글 리스트</h1>
       	
	<form name="frm" method="post">
	<input type="hidden" name="qseq">  
	
    <!-- Navbar Search 
	<div class="d-md-inline-block form-inline ms-auto me-0 me-md-3 my-2 mb-4 col-12">
		<div class="btn-toolbar row justify-content-between">
			<div class="input-group me-2">
				<button class="btn btn-outline-secondary" type="button" name="btn_total" onclick="go_order_total()">전체보기</button>
				<input class="form-control" type="text" placeholder="주문자 이름 검색.." name="key" id="key" />
				<button class="btn btn-primary mr-2" type="button" name="btn_search" onclick="go_search()"><i class="fas fa-search"></i></button>
			</div>
			<div class="input-group">
				<button class="btn btn-warning" type="button" name="btn_chart" onclick="go_order_save()">주문처리/입금확인</button>
			</div>
		</div>
	</div>
   Navbar-->
   
   <div class="card mb-4">
       <div class="card-header"><i class="fas fa-list me-1"></i> QnA List</div>
       <div class="card-body">
		<table class="table table-bordered fs-12" id="qnaList">
		    <tr>
		        <th class="col-2">Q&amp;A번호(답변여부)</th>
		        <th class="col-6">제목</th>
		        <th class="col-2">작성자</th>
		        <th class="col-2">작성일</th>
		    </tr>
		    <c:forEach items="${qnaList}" var="qnaVO">
		    <tr>
		    	<td class="col-2">
		    		<span class="f-w-b">${qnaVO.qseq}</span>
		    		<c:choose>
		    			<c:when test='${qnaVO.rep=="1"}'><span class="text-danger">미처리</span></c:when>
		    			<c:otherwise><span class="text-dark">답변처리완료</span></c:otherwise>
		    		</c:choose>
		    	</td>
		    	<td class="col-6">
		    		<a href="#" onClick="javascript:go_view('${qnaVO.qseq}')">
		    			${qnaVO.subject}
		    		</a>
		    	</td>
		    	<td class="col-2">${qnaVO.id}</td>
		    	<td class="col-2"><fmt:formatDate value="${qnaVO.indate}"/></td>
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