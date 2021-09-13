<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>  

    <!-- Start Content Page -->
    <div class="container-fluid bg-light py-5">
        <div class="col-md-6 m-auto pt-3 text-center">
            <h2 class="h2">LOGIN</h2>
        </div>
    </div>

    <!-- Start Contact -->
    <div class="container py-4 mb-4">
        <div class="row py-4">
            <form class="col-md-9 m-auto" method="post" role="form" action="login">
                <div class="row">
                    <div class="form-group col-md-6 mb-3">
                        <label for="inputid">ID</label>
                        <input type="text" class="form-control mt-1" id="id" name="id" placeholder="id" value="${id}">
                    </div>
	                <div class="form-group col-md-6 mb-3">
	                    <label for="inputpassword">PASSWORD</label>
	                    <input type="password" class="form-control mt-1" id="pwd" name="pwd" placeholder="password">
	                </div>
                </div>
                <div class="row">
                    <div class="col text-center mt-2">
                        <button type="submit" class="btn btn-success btn-lg btn-full">Login</button>
                    </div>
                </div>
                <div class="row">
                    <div class="col text-center mt-4">
                        <button type="button" class="btn btn-sm btn-outline-success btn-full" onclick="location='contract'">회원 가입</button>
                    </div>
                </div>
                <div class="row">
                    <div class="col text-center mt-2">
                        <button type="button" class="btn btn-sm btn-outline-success btn-full" onclick="find_id_form()">ID/PW 찾기</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <!-- End Contact -->
    
<%@ include file="../footer.jsp" %> 