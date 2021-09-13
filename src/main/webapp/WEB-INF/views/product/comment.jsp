<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!-- 상품평 -->
<div class="card mt-2">
	
	<div class="card-body">
		<div class="row">
			<form id="commentForm" name="commentForm" method="post" enctype="multipart/form-data">
				<h6 class="mt-3">REVIEW <span id="cCnt"></span></h6>
				
				<div id="reply">
					<table class="table table-bordered fs-12" id="rep_input">
						<tr>
							<td>
								<textarea class="comment_box" id="content" name="content" placeholder="리뷰를 작성해주세요"></textarea>
							</td>
						</tr>
						<!-- <tr>
							<td>
								<div class="custom-file">
									<input type="file" name="comment_image" id="comment_image" class="custom-file-input">
								</div>
							</td>
						</tr> -->
					</table>
					
					<div class="col d-grid">
						<a class="btn btn-sm btn-outline-success fs-12" href='#' onClick="save_comment('${productVO.pseq }')">리뷰등록</a>
						<input type="hidden" id="pseq" name="pseq" value="${productVO.pseq}"/>
					</div>
					
				</div>
				
				<div class="row pb-3">
			
				</div>
			</form>
		</div>
		
		<div class="row">
			<form id="commentListForm" name="commentListForm" method="post">
				<div id="commentList"></div>
			</form>
		</div>
	</div>
	
</div>


<script type="text/javascript">
	$(document).ready(function(){
		// 상품상세정보 로딩 시에 상품평 목록을 조회하여 출력
		getCommentList();
	});
	
	// 상품평 목록 불러오기 함수
	function getCommentList(){
		$.ajax({
			type: 'GET',
			url: 'comments/list',
			dataType: 'json',
			data:$("#commentForm").serialize(),
			contentType: 'application/x-www-form-urlencoded; sharset=UTF-8',
			success: function(data){
				var totalCount = data.total;
				var commentList = data.commentList;
				
				showHTML(commentList, totalCount);
			},
			error: function(){
				alert("상품평 목록을 조회하지 못했습니다.")
			}
		});
	}
	
	function showHTML(commentList, totalCount){
		var html = "";
		
		if (commentList.length > 0){
			// 상품평의 각 항목별로 HTML 생성
			$.each(commentList, function(index, item){
				html += "<div class=\"mb-2\">";
				html += "<div id=\"comment_item\" class=\"fs-12\"> <strong class=\"me-2 f-w-b\">작성자: " + item.writer + "</strong>"
				html += "<span id=\"write_date\" class=\"fs-12\">" + displayTime(item.regDate) + "</span><br>"
				html += item.content+"<br></div>"
				html += "</div>";
			});
			
		} else { // 조회된 상품평이 없을 경우
			html += "<div>";
			html += "<h5 class=\"fs-12\">등록된 상품평이 없습니다.</h5>";
			html += "</div>";
		}
		
		// 상품평 갯수 출력
		$("#cCnt").html("<span class='text-danger fs-12'>" + totalCount + "개" + "</span>");
		// 상품평 목록 출력
		$("#commentList").html(html);

	}
	
	/*
	 * 입력 파라미터:
		 timeValue - 상품평 등록 시간
	 */
	function displayTime(timeValue) {
		var today = new Date();
		
		// 현재시간에서 댓글등록시간을 빼줌
		var timeGap = today.getTime() - timeValue;
		
		// timeValue를 Date객체로 변환
		var dateObj = new Date(timeValue);
		
		// timeGap이 24시간 이내인지 계산을 하고 24시간 이내이면 시 분 초를 구함
		if(timeGap < (1000 * 60 * 60 * 24)){ // 1초는 1000ms, 1분, 1시간, 24시간
			var hh = dateObj.getHours();
			var mi = dateObj.getMinutes();
			var ss = dateObj.getSeconds();
			
			//return hh + ':' + mi + ':' + ss;
			return [(hh>9 ? '' : '0')+hh, ':', (mi>9 ? '' : '0')+mi, ':',
				(ss>9 ? '' : '0')+ss].join('');
		} else {
			var yy = dateObj.getFullYear();
			var mm = dateObj.getMonth() + 1;
			var dd = dateObj.getDate();
			
			//return yy + "-" + mm + "-" + dd;
			return [yy, '/', (mm>9 ? '' : '0')+mm, '/', (dd>9 ? '' : '0')+dd].join('');
		}
	}
	
	/*
	 리뷰등록
	*/
	function save_comment(pseq){
		var form = $("#commentForm")[0];
		var formData = new FormData(form);
		
		$.ajax({
			type: 'POST',
			url: 'comments/save',
			data:formData,
			success: function(data) {
				if (data=='success'){ // 상품평 등록 성공
					getCommentList(); // 상품평 목록 요청함수 호출
					$("#content").val("");
				} else if (data=='fail'){
					alert("상품평 등록이 실패하였습니다. 다시 시도해 주세요");
				} else if (data=='not_logedin'){
					alert("상품평 등록은 로그인이 필요합니다.");
				}
			},				
			error: function(request, status, error){
				alert("error:" + error);
			},
			cache: false,
			contentType: false,
			processData: false
		});
	}
		
</script>

