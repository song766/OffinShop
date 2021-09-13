<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>  

<!-- Start Content Page -->
<div class="container-fluid bg-light py-5">
    <div class="col-md-6 m-auto pt-3 text-center">
        <h2 class="h2">Join</h2>
    </div>
</div>

<!-- Start Contact -->
<div class="container py-4 mb-4">
    <div class="row py-4">
        <form class="col-md-9 m-auto" method="post" role="form" id="join" action="join" name="formm">
            <div class="row">
	            <div class="form-group col-md-12 mb-3">
	            	<label for="inputid">ID</label>
	                <div class="input-group">
	                   	 <input type="text" class="form-control" id="id" name="id" value="${id}">
						 <input type="hidden" name="reid" id="reid" value="${reid}">
						 <div class="input-group-prepend">
							<button type="button" class="btn btn-box btn-outline-secondary" onclick="idcheck()" aria-describedby="basic-addon1" style="height:41px">중복 체크</button>
						 </div>
	                </div>
				</div>
				<div class="form-group col-md-12 mb-3">
				    <label for="inputpassword">PASSWORD</label>
				    <input type="password" class="form-control mt-1" id="pwd" name="pwd">
				</div>
				<div class="form-group col-md-12 mb-3">
				    <label for="inputpasswordcheck">PASSWORD CHECK</label>
				    <input type="password" class="form-control mt-1" id="pwdCheck" name="pwdCheck">
				</div>
				<div class="form-group col-md-12 mb-3">
				    <label for="inputname">NAME</label>
				    <input type="text" class="form-control mt-1" id="name" name="name">
				</div>
				<div class="form-group col-md-12 mb-3">
				    <label for="inputname">E-MAIL</label>
				    <input type="text" class="form-control mt-1" id="email" name="email">
				</div>
            </div>
            
            <!-- 우편번호찾기 -->
            <div class="row mt-3">
	            <div class="form-group col-md-12 mb-3">
	            	<label for="inputzipcode">ZIP CODE</label>
	                <div class="input-group">
	                   	 <input type="text" class="form-control" id="zip_num" name="zip_num">
						 <div class="input-group-prepend">
							<button type="button" class="btn btn-box btn-outline-secondary" onclick="post_zip()" aria-describedby="basic-addon1" style="height:41px">주소 찾기</button>
						 </div>					 
	                </div>
				</div>
				<div class="form-group col-md-12 mb-3">
				    <label for="inputaddress">ADDRESS</label>
				    <input type="text" class="form-control mt-1" id="addr1" name="addr1">
				    <input type="text" class="form-control mt-1" id="addr2" name="addr2">
				</div>
				<div class="form-group col-md-12 mb-3">
				    <label for="inputphone">PHONE</label>
				    <input type="text" class="form-control mt-1" id="phone" name="phone">
				</div>
            </div>
            <!-- 우편번호찾기 끝 -->
            
            <div class="row">
                <div class="col text-center mt-2">
                    <button type="button" class="btn btn-success btn-lg btn-full" onclick="go_save()">회원가입</button>
                </div>
            </div>
        </form>
    </div>
</div>
<!-- End Contact -->
    
<%@ include file="../footer.jsp" %> 