<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  
<!DOCTYPE html>
<html>
<head>
  <title>Offin</title>

  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <!-- google Login content:Oauth2.0 클라이언트ID -->
  <meta name ="google-signin-client_id" content="208832203723-mdgdkao9d50os82kqecsv622d69p5eh7.apps.googleusercontent.com">
  
    <link rel="apple-touch-icon" href="images/apple-icon.png">
    <link rel="shortcut icon" type="image/x-icon" href="images/favicon.ico">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/templatemo.css">
    <link rel="stylesheet" href="css/custom.css">
    <!-- Load fonts style after rendering the layout styles -->
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Roboto:wght@100;200;300;400;500;700;900&display=swap">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Lato&family=Noto+Sans+JP&family=Roboto&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Kaushan+Script&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css">
      
    <script type="text/javascript" src="js/jquery-3.6.0.min.js"></script>
    <script src="js/jquery-1.11.0.min.js"></script>
    <script src="js/jquery-migrate-1.2.1.min.js"></script>
    <script src="js/bootstrap.bundle.min.js"></script>
    <script src="js/templatemo.js"></script>
    <script src="js/custom.js"></script>
    
    <script type="text/javascript" src="member/member.js"></script>
    <script type="text/javascript" src="product/product.js"></script>
	<script type="text/javascript" src="member/findIdAndPassword.js"></script>
	<script type="text/javascript" src="mypage/mypage.js"></script> 
	<!-- 구글 api 로그인 사용 스크립트 -->
	<script src="https://apis.google.com/js/platform.js" async defer></script>
	<script src="https://apis.google.com/js/platform.js?onload=init" async defer></script>
</head>

<body>
<!--header start -->
  <header id="header">
    <!-- Start Top Nav -->
    <nav class="navbar navbar-expand-lg bg-dark navbar-light d-none d-lg-block" id="templatemo_nav_top">
        <div class="container text-light">
            <div class="w-100 d-flex justify-content-between">  
                <div>
					<a class="navbar-sm-brand text-light text-decoration-none" href="mailto:766song@gmail.com"><i class="fa fa-envelope mx-2"></i></a>
                    <a class="navbar-sm-brand text-light text-decoration-none" href="tel:010-1234-5678"><i class="fa fa-phone mx-2"></i></a>
                </div>
              
                <div class="t-a-r">
                	<c:choose>
                	<c:when test="${empty sessionScope.loginUser}">
                   		<a class="text-light mx-2" href="login_form">Login </a>
                   		<a class="text-light mx-2" href="admin_login_form">Admin </a>
                   		<a class="text-light mx-2" href="contract">Join </a>
                    </c:when>
                    <c:otherwise>
                    	<a class="text-light mx-2">${sessionScope.loginUser.name}(${sessionScope.loginUser.id})</a>
                    	<a class="text-light mx-2" href="logout">Logout </a>
                    </c:otherwise>
                    </c:choose>     
                    <a class="text-light mx-2" href="cart_list">Cart </a>
                    <a class="text-light mx-2" href="mypage">Mypage</a>
                </div>
            </div>
        </div>
    </nav>
    <!-- Close Top Nav -->

    <!-- Header -->
    <nav class="navbar navbar-expand-lg navbar-light shadow">
        <div class="container d-flex justify-content-between align-items-center">

            <a class="navbar-brand text-success logo h1 align-self-center" href="index.html" style="font-family: 'Kaushan Script', cursive;">
                Offin
            </a>

            <button class="navbar-toggler border-0" type="button" data-bs-toggle="collapse" data-bs-target="#templatemo_main_nav" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="align-self-center collapse navbar-collapse flex-fill  d-lg-flex justify-content-lg-between" id="templatemo_main_nav">
                <div class="flex-fill">
                    <ul class="nav navbar-nav d-flex justify-content-between mx-lg-auto">
                        <li class="nav-item">
                            <a class="nav-link" href="productListNew">NEW</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="productListBest">BEST</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="productListAll">PRODUCT</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="qna_list">Q&amp;A</a>
                        </li>
                    </ul>
                </div>
                <div class="navbar align-self-center d-flex">
                    <div class="d-lg-none flex-sm-fill mt-3 me-3 mb-4 col-7 col-sm-auto pr-3">
                        <div class="input-group">
                            <input type="text" class="form-control" id="inputMobileSearch" placeholder="Search ...">
                            <div class="input-group-text">
                                <i class="fa fa-fw fa-search"></i>
                            </div>
                        </div>
                    </div>
                    <a class="nav-icon d-none d-lg-inline" href="#" data-bs-toggle="modal" data-bs-target="#templatemo_search">
                        <i class="fa fa-fw fa-search text-dark mr-2"></i>
                    </a>
                    
                    <c:choose>
                    <c:when test="${empty sessionScope.loginUser}">
	                    <a class="nav-icon position-relative text-decoration-none" href="cart_list">
	                        <i class="fa fa-fw fa-cart-arrow-down text-dark mr-1"></i>
	                        <span class="d-none position-absolute top-0 left-100 translate-middle badge rounded-pill bg-light text-dark">
	                       		<fmt:formatNumber value="${totalCount}"/>
	                       	</span>                    
	                    </a>
                    </c:when>
                    <c:otherwise>
	                    <a class="nav-icon position-relative text-decoration-none" href="cart_list">
	                        <i class="fa fa-fw fa-cart-arrow-down text-dark mr-1"></i>
	                        <span class="position-absolute top-0 left-100 translate-middle badge rounded-pill bg-light text-dark">
	                       		<fmt:formatNumber value="${totalCount}"/>
	                       	</span>                    
	                    </a>
                    </c:otherwise>
                    </c:choose>

                </div>
            </div>

        </div>
    </nav>
    <!-- Close Header -->
  </header>
  <!--header end -->
  
	<!-- TOP Bar -->
	<div class="top_bar">
		<a href="#header" class="s_btn"><i class="fas fa-chevron-up"></i></a>
	</div>
	
  
      <!-- Modal -->
    <div class="modal fade bg-white" id="templatemo_search" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-lg" role="document">
            <div class="w-100 pt-1 mb-5 text-right">
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <form method="get" class="modal-content modal-body border-0 p-0" id="search_form">
                <div class="input-group mb-2">
                    <input type="text" class="form-control" id="inputModalSearch" name="key" placeholder="Search ...">
                    <button type="submit" class="input-group-text bg-success text-light" onclick="product_go_search()">
                        <i class="fa fa-fw fa-search text-white"></i>
                    </button>
                </div>
            </form>
        </div>
    </div>
    
<script>
function product_go_search() {
	$("#search_form").attr("action", "productListAll").submit();
}
</script>
