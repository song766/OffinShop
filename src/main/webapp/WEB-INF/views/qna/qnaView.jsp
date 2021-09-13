<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %> 
<style>
#qnaView th {width:20%;}
#qnaView tr:last-child {border-bottom: transparent;}
</style>
<!-- Start Content Page -->
<div class="container-fluid bg-light py-5">
    <div class="col-md-6 m-auto pt-3 text-center">
        <h2 class="h2">Q&A 고객 게시판</h2>
    </div>
</div>

<!-- Start Contact -->
<div class="container py-4 mb-4">
    <div class="row py-4">
        <form class="col-md-9 m-auto" name="formm" method="post">
			<table id="qnaView" class="table t-a-c fs-12">
				<tr>
					<th>제목</th>
					<td>${qnaVO.subject}</td>
				</tr>
				<tr>
					<th>등록일</th>
					<td><fmt:formatDate value="${qnaVO.indate}" type="date"/></td>
				</tr>
				<tr>
					<th>질문내용</th>
					<td>${qnaVO.content}</td>
				</tr>
				<tr>
					<th>답변내용</th>
					<td>${qnaVO.reply}</td>
				</tr>
			</table>
			
			<div id="buttons" class="row py-4 t-a-c">
				<div class=""> 
					<input type="button" value="이전 목록"  class="btn btn-secondary" onclick="location.href='qna_list'">
					<input type="button" value="쇼핑 계속하기"  class="btn btn-success" onclick="location.href='index'">
				</div>
			</div>
			
        </form>
    </div>
</div>
<!-- End Contact -->

<%@ include file="../footer.jsp" %>