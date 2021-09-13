/**
 * 
 */
function go_cart(){
	/*
	 *  quantity 입력필드에 값이 있는지 확인
	 *  값이 없으면 alert 출력
	 *  값이 있으면 : url => "cart_insert" submit 구현
	 */
	if ($("#quantity").val() == ""){
		alert("수량을 입력해 주세요!");
		$("#quantity").focus();
	} else {
		$("#theform").attr("action", "cart_insert").submit();
	}
}

function list_go_cart(){
	$("#producttheform").attr("action", "cart_insert_product").submit();
}

function go_order(){
	alert("지금은 구매 불가합니다. 장바구니 기능을 이용해주세요!");
	$("#theform").attr("action", "product_detail").submit();
}