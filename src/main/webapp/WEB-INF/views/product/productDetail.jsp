<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>

<!-- Open Content -->
<section class="bg-light">
    <div id="itemdetail" class="container pb-5">
    <form  method="post" name="formm" id="theform">
        <div class="row">
            <div class="col-lg-5 mt-5">
                <div class="card mb-3">
                    <img class="card-img img-fluid" src="product_images/${productVO.image}" id="product-detail">
                </div>

            </div>
            <!-- col end -->
            <div class="col-lg-7 mt-5">
                <div class="card">
                    <div class="card-body">
                    	<input  type="hidden" name="pseq" value="${productVO.pseq}"><br>
                        <h1 class="h2">${productVO.name}</h1>
                        <p class="h3 py-2">${productVO.price2} won</p>
                        <p class="py-2">
                            <span class="list-inline-item text-dark">리뷰 2</span>
                        </p>

                        <h6>Description:</h6>
                        <p>${productVO.content}</p>
                        <ul class="list-inline">
                            <li class="list-inline-item">
                                <h6>Inventory quantity :</h6>
                            </li>
                            <li class="list-inline-item">
                                <p class="text-muted"><strong>10개</strong></p>
                            </li>
                        </ul>

                        <input type="hidden" name="product-title" value="Activewear">
                        <div class="row">
                            <div class="col-auto">
                                <ul class="list-inline pb-3">
                                    <li class="list-inline-item text-right">
									수량 <input type="hidden" name="product-quanity" id="product-quanity" value="1">
                                    </li>
                                    <li class="list-inline-item"><span class="btn btn-success" id="btn-minus">-</span></li>
                                    <li class="list-inline-item">
                                    	<span class="badge bg-secondary" id="var-value">1</span>
                                    	<input type="hidden" name="quantity" id="quantity" value="1">
                                    </li>
                                    <li class="list-inline-item"><span class="btn btn-success" id="btn-plus">+</span></li>
                                </ul>
                            </div>
                        </div>
                        <div class="row pb-3">
                            <div class="col d-grid">
                                <button type="submit" class="btn btn-success btn-lg" name="submit" value="buy" class="submit" onclick="go_order()">Buy</button>
                            </div>
                            <div class="col d-grid">
                                <button type="submit" class="btn btn-success btn-lg" name="submit" value="addtocard"  class="submit" onclick="go_cart()">Add To Cart</button>
                            </div>
                        </div>

                    </div>
                </div>
                

                
            </div>
        </div>
    </form>
    
<!-- 상품평 처리 -->
<%@ include file="comment.jsp" %>

    </div>
</section>
<!-- Close Content -->



<!-- Start Article 
<section class="py-5">
    <div class="container">
        <div class="row text-left p-2 pb-3">
            <h4>상품 리뷰</h4>
            <button type="submit" class="btn btn-sm btn-outline-success">댓글 2</button>
            
        </div>

        <div id="carousel-related-product">


        </div>
    </div>
</section>
End Article -->


<%-- 상품평 처리 
<%@ include file="comment.jsp" %> 
--%>
<%@ include file="../footer.jsp" %> 
