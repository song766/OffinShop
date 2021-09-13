<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
  <div class="clear"></div>
    
    <!-- Start Footer -->
    <footer class="bg-dark" id="tempaltemo_footer">
        <div class="container">
            <div class="row">

                <div class="col-md-4 py-5">
                    <h2 class="h2 text-success border-bottom pb-3 border-light logo" style="font-family: 'Kaushan Script', cursive;">Offin</h2>
                    <ul class="list-unstyled text-light footer-link-list">
                        <li>
                            <i class="fa fa-phone fa-fw"></i>
                            <a class="text-decoration-none" href="tel:010-123-4567">010-123-4567</a>
                        </li>
                        <li>
                            <i class="fa fa-envelope fa-fw"></i>
                            <a class="text-decoration-none" href="mailto:766song@gmail.com">766song@gmail.com</a>
                        </li>
                    </ul>
                </div>

                <div class="col-md-4 py-5">
                    <h2 class="h2 text-light border-bottom pb-3 border-light">Products</h2>
                    <ul class="list-unstyled text-light footer-link-list">
                        <li><a class="text-decoration-none" href="productListNew">NEW</a></li>
                        <li><a class="text-decoration-none" href="productListBest">BEST</a></li>
                        <li><a class="text-decoration-none" href="category?kind=1">OUTER</a></li>
                        <li><a class="text-decoration-none" href="category?kind=2">TOP</a></li>
                        <li><a class="text-decoration-none" href="category?kind=3">PANTS</a></li>
                        <li><a class="text-decoration-none" href="category?kind=4">SKIRT</a></li>
                        <li><a class="text-decoration-none" href="category?kind=5">DRESS</a></li>
                    </ul>
                </div>

                <div class="col-md-4 py-5">
                    <h2 class="h2 text-light border-bottom pb-3 border-light">MEMBER</h2>
                    <ul class="list-unstyled text-light footer-link-list">
                    <c:choose>
                    <c:when test="${empty sessionScope.loginUser}">
                        <li><a class="text-decoration-none" href="login_form">LOGIN</a></li>
                        <li><a class="text-decoration-none" href="admin_login_form">ADMIN</a></li>
                        <li><a class="text-decoration-none" href="contract">JOIN</a></li>
                    </c:when>
                    <c:otherwise>
                    	 <li><a class="text-decoration-none" href="logout">LOGOUT</a></li>
                    	 <li><a class="text-decoration-none" href="mypage">MY PAGE</a></li>
                    </c:otherwise>
                    </c:choose>
                    	<li><a class="text-decoration-none" href="cart_list">CART</a></li>
                    	<li><a class="text-decoration-none" href="qna_list">Q&amp;A</a></li>
                    </ul>
                </div>

            </div>

        </div>

        <div class="w-100 bg-black py-3">
            <div class="container">
                <div class="row pt-2">
                    <div class="col-12">
                        <p class="text-left text-light">
                            Copyright &copy; 2021 Offin
                            | Designed by 
                            <a href="https://technext.github.io/zay-shop/index.html" target="_blank">Demo</a>
                        </p>
                    </div>
                </div>
            </div>
        </div>

    </footer>
    <!-- End Footer -->


</body>
</html>