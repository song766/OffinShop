<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>	 
<script>
function qnaWrite(){
	/* 제목, 내용 입력확인*/
	if ($("#qnaTitle").val() == "") {
		alert("제목을 입력해 주세요!");
		$("#qnaTitle").focus();
	} else if ($("#qnaContent").val() == "") {
		alert("내용을 입력해주세요!");
		$("#qnaContent").focus();
	} else {
		$("#qnaWrite").attr("action", "qna_write").submit();
	}
}
</script>

<!-- Start Content Page -->
<div class="container-fluid bg-light py-5">
    <div class="col-md-6 m-auto pt-3 text-center">
        <h2 class="h2">Q&A 고객 게시판</h2>
    </div>
</div>

<!-- Start Contact -->
<div class="container py-4 mb-4">
    <div class="row py-4">
        <form class="col-md-9 m-auto" name="formm" method="post" action="qna_write" id="qnaWrite">
        	<fieldset>
				<legend class="t-a-c mb-3">질문 작성</legend>
				<label class="mb-1 fs-14">제목</label>
				<input type="text" name="subject" id="qnaTitle" class="form-control">
				<br>
				<label class="mb-1 fs-14">내용</label>
				<textarea rows="8" name="content" id="qnaContent" class="form-control" style="resize: none;"></textarea>
			</fieldset>
			
			<div id="buttons" class="row py-4 t-a-c">
				<div class=""> 
					<input type="button" value="등록하기" class="btn btn-secondary" onclick="return qnaWrite()">
					<input type="reset" value="초기화" class="btn btn-secondary">
					<input type="button" value="목록으로" class="btn btn-success" onclick="location.href='qna_list'">
				</div>
			</div>
			
        </form>
    </div>
</div>
<!-- End Contact -->

<%@ include file="../footer.jsp" %>