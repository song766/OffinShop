<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>  

<!-- Start Content Page -->
<div class="container-fluid bg-light py-5">
    <div class="col-md-6 m-auto pt-3 text-center">
        <h2 class="h2">Product</h2>
    </div>
</div>

<!-- Start Content -->
<div class="container py-4">
  <div class="row">
    
    <div class="col-lg-12 t-a-c">
      <h1 class="h2 pb-4"></h1>
      <ul class="list-unstyled templatemo-accordion">
        <li class="pb-3">
          <a class="collapsed d-flex justify-content-between h3 text-decoration-none" href="#">Categories
          <i class="fa fa-fw fa-chevron-circle-down mt-1"></i></a>
          <ul class="collapse show list-unstyled pl-3">
          <li><a class="text-decoration-none" href="productListAll">ALL</a></li>
          	<li><a class="text-decoration-none" href="category?kind=1">OUTER</a></li>
          	<li><a class="text-decoration-none" href="category?kind=2">TOP</a></li>
          	<li><a class="text-decoration-none" href="category?kind=3">PANTS</a></li>
          	<li><a class="text-decoration-none" href="category?kind=4">SKIRT</a></li>
          	<li><a class="text-decoration-none" href="category?kind=5">DRESS</a></li>
          </ul>
        </li>
      </ul>
    </div>
    
    <form name="formm" method="post" id="productListform">
    
    <div class="col-lg-12">  
      
      <div class="row">
      <c:forEach items="${productKindList}" var="productVO">
        <div class="col-md-4">
          <div class="card mb-4 product-wap rounded-0">
            <div class="card rounded-0">
              <img class="card-img rounded-0 img-fluid" src="product_images/${productVO.image}">
              <div class="card-img-overlay rounded-0 product-overlay d-flex align-items-center justify-content-center">
                <ul class="list-unstyled">
                  <li>
                  	<a class="btn btn-success text-white mt-2" href="product_detail?pseq=${productVO.pseq}">
                  	<input  type="hidden" name="pseq" value="${productVO.pseq}">
                  	<i class="far fa-eye"></i></a>
                  </li>
                  <li><a class="btn btn-success text-white mt-2" href="javascript:list_go_cart();"><i class="fas fa-cart-plus"></i></a></li>
                </ul>
              </div>
            </div>
            <div class="card-body">
              <a href="product_detail?pseq=${productVO.pseq}" class="h3 text-decoration-none">${productVO.name}</a>
              <p class="mb-0">${productVO.price2} won</p>
              <input type="hidden" name="pseq" value="${productVO.price2}">
            </div>
          </div>
        </div>
      </c:forEach>
      </div>
      
    </div>
    </form>

  </div>
</div>
<!-- End Content -->

<%@ include file="../footer.jsp" %>    