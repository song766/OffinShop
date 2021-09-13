<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
    
<div id="layoutSidenav">
    <div id="layoutSidenav_nav">
        <nav class="sb-sidenav accordion sb-sidenav-dark" id="sidenavAccordion">
            <div class="sb-sidenav-menu">
                <div class="nav">
                    <div class="sb-sidenav-menu-heading">Admin Setting</div>
                    <a class="nav-link" href="admin_product_list">
                        <span class="sb-nav-link-icon"><i class="fas fa-list"></i></span>
                                          상품리스트
                    </a>
                    <a class="nav-link" href="admin_order_list">
                        <span class="sb-nav-link-icon"><i class="fas fa-table"></i></span>
                        	주문리스트
                    </a>
                    <a class="nav-link" href="admin_member_list">
                        <span class="sb-nav-link-icon"><i class="fas fa-user"></i></span>
                                          회원리스트
                    </a>
                    <a class="nav-link" href="admin_qna_list">
                        <span class="sb-nav-link-icon"><i class="fas fa-question"></i></span>
                                          Q&amp;A리스트
                    </a>
                    <hr class="border border-white">
                    <a class="nav-link" href="index.html" target="_blank">
                        <span class="sb-nav-link-icon"><i class="fas fa-store"></i></span>
                        	쇼핑몰 >
                    </a>
                </div>
            </div>
        </nav>
    </div>