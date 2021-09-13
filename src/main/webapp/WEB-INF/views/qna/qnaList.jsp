<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>

<!-- Start Content Page -->
<div class="container-fluid bg-light py-5">
    <div class="col-md-6 m-auto pt-3 text-center">
        <h2 class="h2">Q&A 게시판</h2>
    </div>
</div>

<!-- Start Contact -->
<div class="container py-4 mb-4">
    <div class="row py-4">
        <form class="col-md-9 m-auto" name="formm" method="post">
			<table id="qnaList" class="table t-a-c fs-12">
				<tr>
					<th>번호</th>
					<th>제목</th>
					<th>등록일</th>
					<th>답변여부</th>
				</tr>
				<c:forEach items="${qnaList}"  var="qnaVO">
				<tr>
					<td>${qnaVO.qseq}</td>
					<td><a href="qna_view?qseq=${qnaVO.qseq}" class="f-a-n text-dark">${qnaVO.subject}</a></td>
					<td><fmt:formatDate value="${qnaVO.indate}" type="date"/></td>
					<td>
						<c:choose>
							<c:when test="${qnaVO.rep==1}"> <span class="text-secondary f-w-b">no</span> </c:when>
							<c:when test="${qnaVO.rep==2}"> <span class="text-danger f-w-b">yes</span> </c:when>
						</c:choose>
					</td>
				</tr>
				</c:forEach>
			</table>
			
			<div id="buttons" class="row py-4 t-a-c">
				<div class="">
					<input type="button" value="글쓰기" class="btn btn-secondary" onclick="location.href='qna_write_form'">
					<input type="button" value="쇼핑 계속하기" class="btn btn-success" onclick="location.href='index'">
				</div>
			</div>
			
        </form>
    </div>
</div>
<!-- End Contact -->

<%@ include file="../footer.jsp" %>