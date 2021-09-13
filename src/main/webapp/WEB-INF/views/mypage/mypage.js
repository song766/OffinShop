/**
 * 
 */
function go_cart_delete(){
	
	var count = 0; // 삭제할 항목 갯수 저장
	
	/*
	 * if문과 for문의 내용은 삭제할 항목이 체크가 되어있는지 확인.
	 * 체크가 하나도 안되어 있으면 삭제 처리 불가하도록 구현
	 */
	
	// 삭제할 항목이 0또는 하나만 체크되어 있을 경우
	if (document.formm.cseq.length == undefined) {
		// cseq가 하나만 체크 되어있는 경우
		if (document.formm.cseq.checked == true) {
			count++;
		}
	}
	
	// 삭제할 항목이 2개 이상인 경우 (cseq의 갯수에 따라)
	for (var i=0; i<document.formm.cseq.length; i++){
		if (document.formm.cseq[i].checked == true){
			count++;
		}
	}
	
	if(count == 0) {
		alert ("삭제할 항목을 선택해주세요!");
	} else {
		alert ("장바구니 목록을 삭제합니다.");
		document.formm.action = "cart_delete";
		document.formm.submit();
	}
}

/*
 * 장바구니에 저장된 내역 주문처리 요청
 */
function go_order_insert(){
	$("#theform").attr("action", "order_insert").submit();
}