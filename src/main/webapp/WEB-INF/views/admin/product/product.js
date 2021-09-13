/**
 *  상품명으로 검색
 */
function go_search(){
	$("#prod_form").attr("action", "admin_product_list").submit();
}

/*
 * 상품 전체 조회
 */
function go_total(){
	$("#key").val("");
	$("#prod_form").attr("action", "admin_product_list").submit();
}

/*
 *  상품 등록 페이지 요청 - productList.js에서 go_wrt()부분
 */
function go_wrt(){
	$("#prod_form").attr("action", "admin_product_write_form").submit();
}

/*
 * 판매가(price2) - 원가(price1)를 계산하여 price3에 표시하는 함수
 * 계산 시 콤마를 제거하고 계산하도록 함.
 */
function go_ab(){
	var a = remove_comma($("#price2").val());
	var b = remove_comma($("#price1").val());
	
    $("#price3").val(a-b);
}

/*
 * 정규표현식을 통해 문자열 내에 들어있는 콤마를 제거
 * input : 입력 문자열
 */
function remove_comma(input) {
	return input.replace(/,/gi, ""); // 슬래시 안에 들어있는 , 를 gi정규식표현으로 "" 빈문자로
	// input값에 ,가 포함되어있으면 제거하라는 뜻
}

/*
 * 상품등록 폼에 항목이 입력되었는지 확인  
 */
function go_save(){
	var $price1 = $("#price1");
	var $price2 = $("#price2");
	var $price3 = $("#price3");
	
	if($("#kind").val() == "") {
		alert("상품종류를 입력하세요!");
		$("#kind").focus();
	} else if ($("#name").val() == "") {
		alert("상품명을 입력하세요!");
		$("#name").focus();
	} else if ($price1.val == "") {
		alert("원가를 입력하세요!");
		$price1.focus();
	} else if ($price2.val == "") {
		alert("판매가를 입력하세요!");
		$price2.focus();
	} else if ($("#content").val == "") {
		alert("상품상세를 입력하세요!");
		$("#content").focus();
	} else if ($("#product_image").val == "") {
		alert("이미지를 입력하세요!");
		$("#product_image").focus();
	} else {
		$price1.val(remove_comma($price1.val()));
		$price2.val(remove_comma($price2.val()));
		$price3.val(remove_comma($price3.val()));
		
		$("#write_form").attr("encoding", "multipart/form-data");
		$("#write_form").attr("action", "admin_product_write").submit();
	}
}

/*
 * t- 화면에 입력된 금액
 * D - 정규표현식으로 숫자가 아닌 문자는 모두 제거
 * 화면에서 입력된 값을 이용해 3자리 이상일 경우, 3자리마다 컴마를 찍는 함수
 */
function NumFormat(t) {
	num = t.value;
	num = num.replace(/\D/g, ''); // 정규식 표현법 : \D
	len = num.length - 3;
	while(len > 0) {
		num = num.substr(0,len) + "," + num.substr(len); // 문자열 인덱스는 0부터 시작
		len -= 3;
	}
	
	t.value = num;
	
	return t;
}

/* 상품 상세보기 화면으로 이동 */
function go_detail(pseq){
	$("#prod_form").attr("action", "admin_product_detail?pseq="+pseq).submit();
}

/* 상품정보 수정화면 요청 구현 */
function go_mod(pseq){
	$("#detail_form").attr("action", "admin_product_update_form?pseq="+pseq).submit();
}

/* 상품정보 수정 구현 */
function go_mod_save(pseq) {
   var $price1 = $("#price1");
   var $price2 = $("#price2");
   var $price3 = $("#price3");
   
   if ($("#kind").val()=="") {
      alert("상품종류를 입력하세요!");
      $("#kind").focus();
   } else if ($("#name").val()=="") {
      alert("상품명을 입력하세요!");
      $("#name").focus();
   } else if ($price1.val()=="") {
      alert("원가를 입력하세요!");
      $price1.focus();
   } else if ($price2.val()=="") {
      alert("판매가를 입력하세요!");
      $price2.focus();
   } else if ($("#content").val()=="") {
      alert("상품상세를 입력하세요!");
      $("#content").focus();
   } else {
      if (confirm("수정하시겠습니까?")) {
         if ($("#bestyn").is(":checked")) {
            $("#bestyn").val("y");
         } else {
            $("#bestyn").val("n");
         }
         
         if ($("#useyn").is(":checked")) {
            $("#useyn").val("y");
         } else {
            $("#useyn").val("n");
         }
         
         $price1.val(remove_comma($price1.val()));
         $price2.val(remove_comma($price2.val()));
         $price3.val(remove_comma($price3.val()));
         
         $("#update_form").attr("encoding", "multipart/form-data");
         $("#update_form").attr("action", "admin_product_update").submit();
      }
   }   
}

/* 상품상세보기 화면 > 상품목록 요청 화면 이동 메소드 */
function go_list(page, items_cnt){
	$("#detail_form").attr("action", "admin_product_list").submit();
}

/*
 * 등록 취소버튼 
 */
function go_mov(){
	$("#write_form").attr("action", "admin_product_list").submit();
	$("#update_form").attr("action", "admin_product_list").submit();
}


