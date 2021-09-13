<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>
<%@ include file="../sub_menu.jsp"%>
<script type="text/javascript">
   function go_qna_list()
   {
	   var theForm = document.frm;
	   theForm.action="admin_qna_list";
	   theForm.submit();
   }

   function go_rep(qseq)
   {
	   var theForm = document.frm;
	   //theForm.qseq.value=qseq; //아래 input hidden에 qseq value값으로 넘겨주어 잠시 막음
	   theForm.action="admin_qna_repsave";
	   theForm.submit();
   }   
</script>


<div id="layoutSidenav_content">
   <article>
       <div class="container-fluid px-4">
       	<h1 class="mt-4 mb-4">Q&amp;A 게시판</h1>
       	
	<form name="frm" method="post">
	<input type="hidden" name="qseq" value="${qnaVO.qseq}"> 
   
   <div class="card mb-4">
       <div class="card-header"><i class="fas fa-list me-1"></i> QnA</div>
       <div class="card-body">
		<table class="table table-bordered fs-12 mb-0" id="qnaDetail">
			<tr>
				<th class="table-warning">제목</th>
				<td>${qnaVO.subject} ${qnaVO.rep}</td>
				<th class="table-warning">등록일</th>
				<td><fmt:formatDate value="${qnaVO.indate}"/></td>
			</tr>
			<tr>
				<th class="table-warning">내용</th>
				<td colspan="3">${qnaVO.content}</td>
			</tr>
		</table>
       </div>
       <div class="card-footer">
		<c:choose>          
			<c:when test='${qnaVO.rep=="1"}'>
				<table class="table table-bordered fs-12">
					<tr>
						<td colspan="2" class="f-w-b">답변등록</td>
					</tr>
					<tr>
						<td colspan="2">    
						<textarea name="reply" rows="3" cols="50" class="w-100" style="height:200px;"></textarea>
						<input type="button" class="btn btn-sm btn-primary mt-2 mr-2" value="저장" onClick="go_rep('${qnaVO.qseq}')">    
						</td>
					</tr>
				</table>
			</c:when>
			<c:otherwise>  
				<table class="table table-bordered fs-12">
					<tr>
						<th>댓글</th>
						<td>${qnaVO.reply}</td>
					</tr>
				</table>
			</c:otherwise>
		</c:choose> 
		   
		<div class="input-group justify-content-center mb-2">
			<input class="btn btn-outline-dark" type="button" value="목록으로 가기" onclick="go_qna_list()">
		</div>
		
       </div>
   </div>        
	</form>
      
      </div>
  </article>
</div>


<%@ include file="../footer.jsp"%>