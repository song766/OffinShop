<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>
<%@ include file="../sub_menu.jsp"%>
<script type="text/javascript">
  function go_order_save() {
    var count = 0;
    if (document.frm.result.length == undefined) {
      if (document.frm.result.checked == true) {
        count++;
      }
    } else {
      for ( var i = 0; i < document.frm.result.length; i++) {
        if (document.frm.result[i].checked == true) {
          count++;
        }
      }
    }
    if (count == 0) {
      alert("주문처리할 항목을 선택해 주세요.");
    } else {
      document.frm.action = "admin_order_save";
      document.frm.submit();
    }
  }
  
  function go_order_search() {
	var theForm = document.frm;
	theForm.action =  "admin_order_list";
	theForm.submit();
  }
  
  function go_order_total(){
		$("#key").val("");
		$("#prod_form").attr("action", "admin_order_list").submit();
  }
</script>

<div id="layoutSidenav_content">
   <article>
       <div class="container-fluid px-4">
       	<h1 class="mt-4">주문리스트</h1>
       	
	<form name="frm" id="prod_form" method="post">

    <!-- Navbar Search -->
	<div class="d-md-inline-block form-inline ms-auto me-0 me-md-3 my-2 mb-4 col-12">
		<div class="btn-toolbar row justify-content-between">
			<div class="input-group me-2">
				<button class="btn btn-outline-secondary" type="button" name="btn_total" onclick="go_order_total()">전체보기</button>
				<input class="form-control" type="text" placeholder="주문자명 입력" name="key" id="key" />
				<button class="btn btn-primary mr-2" type="button" name="btn_search" onclick="go_order_search()"><i class="fas fa-search"></i></button>
			</div>
			<div class="input-group">
				<button class="btn btn-warning" type="button" name="btn_chart" onclick="go_order_save()">주문처리/입금확인</button>
			</div>
		</div>
	</div>
   <!-- Navbar-->
   
   <div class="card mb-4">
       <div class="card-header"><i class="fas fa-list me-1"></i> Order List</div>
       <div class="card-body">
		<table class="table table-bordered fs-12" id="orderList">
		    <tr>
		        <th>주문번호</th><th>주문자</th><th>상품명</th><th>수량</th><th>우편번호</th><th>배송지</th><th>전화</th><th>주문일</th>
		    </tr>
		    <c:forEach items="${orderList}" var="orderVO">
		    <tr>
		    	<td>
		    		<c:choose>
		    			<c:when test='${orderVO.result=="1"}'>
		    				<span class="f-w-b text-danger">${orderVO.odseq}</span>
		    				<input type="checkbox" name="result" value="${orderVO.odseq}" class="form-check-input">
		    				<label class="text-danger">미처리</label>
		    			</c:when>
		    			<c:otherwise>
		    				<span class="f-w-b text-primary">${orderVO.odseq}</span>
		    				<input type="checkbox" checked="checked" disabled="disabled" class="form-check-input">
		    				<label class="text-primary">처리완료</label>
		    			</c:otherwise>
		    		</c:choose>
		    	</td>
		    	<td>${orderVO.mname}</td>
		    	<td>${orderVO.pname}</td>
		    	<td>${orderVO.quantity}</td>
		    	<td>${orderVO.zip_num}</td>
		    	<td>${orderVO.address}</td>
		    	<td>${orderVO.phone}</td>
		    	<td><fmt:formatDate value="${orderVO.indate}"/></td>
		    </tr>
		    </c:forEach>
		</table>
       </div>
       <%@ include file="page_area.jsp" %>
   </div>        
	</form>
      
      </div>
  </article>
</div>

<%@ include file="../footer.jsp" %>