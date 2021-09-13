<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>  

    <!-- Start Banner Hero -->
    <div id="template-mo-zay-hero-carousel" class="carousel slide" data-bs-ride="carousel">
        <ol class="carousel-indicators">
            <li data-bs-target="#template-mo-zay-hero-carousel" data-bs-slide-to="0" class="active"></li>
            <li data-bs-target="#template-mo-zay-hero-carousel" data-bs-slide-to="1"></li>
        </ol>
        <div class="carousel-inner">
            <div class="carousel-item active">
                <div class="container">
                    <div class="row p-5">
                        <div class="mx-auto col-md-8 col-lg-6 order-lg-last">
                            <img class="img-fluid" src="images/banner_img_06.jpg" alt="">
                        </div>
                        <div class="col-lg-6 mb-0 d-flex align-items-center">
                            <div class="text-align-left align-self-center">
                                <h1 class="h1 text-success"><b>Offin</b> it`s NEW!</h1>
                                <h3 class="h2">옷핀에서 만나는 특별한 혜택</h3>
                                <p>
									차별화된 디자인으로 매달 출시하는 <br>옷핀만의 새로운 신상품 확인하러 가기<br>
                                    <a class="text-success" href="productListNew" target="_blank">NEW PRODUCT ></a>
                                </p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="carousel-item">
                <div class="container">
                    <div class="row p-5">
                        <div class="mx-auto col-md-8 col-lg-6 order-lg-last">
                            <img class="img-fluid" src="images/banner_img_07.jpg" alt="">
                        </div>
                        <div class="col-lg-6 mb-0 d-flex align-items-center">
                            <div class="text-align-left">
                                <h1 class="h1">체크라인 미니 숏팬츠</h1>
                                <h3 class="h2">꾸안꾸 레이어드 스타일의 편안함</h3>
                                <p>
                                	부담스럽지않은 기장감으로 자주 입어지는
                                	<span class="f-w-b text-success">A라인</span>
                                	스커트로 편안함까지 더해진 만능 아이템
                                </p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <a class="carousel-control-prev text-decoration-none w-auto ps-3" href="#template-mo-zay-hero-carousel" role="button" data-bs-slide="prev">
            <i class="fas fa-chevron-left"></i>
        </a>
        <a class="carousel-control-next text-decoration-none w-auto pe-3" href="#template-mo-zay-hero-carousel" role="button" data-bs-slide="next">
            <i class="fas fa-chevron-right"></i>
        </a>
    </div>
    <!-- End Banner Hero -->


    <!-- Start Categories of The Month -->
    <section class="container py-5">
        <div class="row text-center pt-3">
            <div class="col-lg-6 m-auto">
                <h1 class="h1">이달의 신상품</h1>
                <p>
                - New Product List -
                </p>
            </div>
        </div>
        <div id="newProduct" class="row">
        <!-- 신상품 3개 forEach코드로 -->
	        <c:forEach items="${newProList}"  var="productVO">
	            <div class="col-12 col-md-4 p-5">
	                <a href="product_detail?pseq=${productVO.pseq}">
	                	<img src="product_images/${productVO.image}" class="rounded-circle img-fluid border">
	                </a>
	                <h5 class="text-center mt-3">${productVO.name}</h5>
	                <p class="text-center mb-3">${productVO.price2}원</p>
	                <p class="text-center"><a class="btn btn-success" href="product_detail?pseq=${productVO.pseq}">보러가기</a></p>
	            </div>
	        </c:forEach>
        </div>
    </section>
    <!-- End Categories of The Month -->


    <!-- Start Featured Product -->
    <section class="bg-light">
        <div class="container py-5">
            <div class="row text-center py-3">
                <div class="col-lg-6 m-auto">
                    <h1 class="h1">베스트 상품 추천</h1>
                    <p>
                    - Best Product List -
                    </p>
                </div>
            </div>
            <div id="bestProduct" class="row">
            	<c:forEach items="${bestProList}"  var="productVO">
	            	<div class="col-12 col-md-4 mb-4">
	                    <div class="card h-100">
	                        <a href="product_detail?pseq=${productVO.pseq}">
	                            <img src="product_images/${productVO.image}" class="card-img-top" alt="${productVO.name}">
	                        </a>
	                        <div class="card-body">
	                            <a href="product_detail?pseq=${productVO.pseq}" class="fs-16 text-decoration-none text-dark">${productVO.name}</a>
	                            <p class="card-text">${productVO.content}</p>
	                        </div>
	                    </div>
	                </div>
                </c:forEach>
            </div>
        </div>
    </section>
    <!-- End Featured Product -->
    
    
<%@ include file="footer.jsp" %> 
    