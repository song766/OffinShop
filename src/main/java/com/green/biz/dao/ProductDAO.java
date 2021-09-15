package com.green.biz.dao;

import java.util.*;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.green.biz.dto.ProductVO;
import com.green.biz.utils.Criteria;

@Repository
public class ProductDAO {
	
	@Autowired // mybatis 객체생성 (applicationContext에 있는 sqlsessiontemplate 객체를 자동으로 받아옴)
	private SqlSessionTemplate mybatis;
	
	// product-mapping.xml에서 getNewProductList호출
	// 신상품 목록
	public List<ProductVO> getNewProductList(){
		return mybatis.selectList("ProductDAO.getNewProductList"); 
	}
	
	public List<ProductVO> getNewProductListAll(){
		return mybatis.selectList("ProductDAO.getNewProductListAll"); 
	}

	public List<ProductVO> getBestProductList(){
		return mybatis.selectList("ProductDAO.getBestProductList");
	}
	
	public List<ProductVO> getBestProductListAll(){
		return mybatis.selectList("ProductDAO.getBestProductListAll");
	}

	public ProductVO getProduct(ProductVO vo) {
		return mybatis.selectOne("ProductDAO.getProduct", vo);
	}

	public List<ProductVO> getProductListByKind(String kind){
		return mybatis.selectList("ProductDAO.getProductListByKind", kind);
	}
	
	public List<ProductVO> productListAll(){
		return mybatis.selectList("ProductDAO.productListAll");
	}
	
	// 쇼핑몰에서 상품목록 조회 (검색)
	public List<ProductVO> productListAllSearch(String name){
		return mybatis.selectList("ProductDAO.productListAllSearch", name);
	}
	
	// 조회한 총 상품 갯수 - 조건 name(조회엔 return값 필요)
	public int countProductList(String name) {
		return mybatis.selectOne("ProductDAO.countProductList", name); //입력값name
	}
	
	// 상품 목록 조회
	public List<ProductVO> listProduct(String name){
		return mybatis.selectList("ProductDAO.listProduct", name);
	}
	
	// 상품 추가 - insert는 void로 
	public void insertProduct(ProductVO vo) { // 입력값 ProductVO
		mybatis.insert("ProductDAO.insertProduct", vo);
	}
	
	// 상품정보 수정 - update
	public void updateProduct(ProductVO vo) {
		mybatis.update("ProductDAO.updateProduct", vo);
	}
	
	// 상품정보 삭제 - delete
	public void deleteProduct(int pseq) {
		mybatis.delete("ProductDAO.deleteProduct", pseq);
	}
	
	/* 페이징처리*/
	public List<ProductVO> getListWithPaging(Criteria criteria, String key){
		HashMap<String, Object> map = new HashMap<>();
		
		map.put("criteria", criteria);
		map.put("key", key);
		
		return mybatis.selectList("ProductDAO.listWithPaging", map);
	}
	
	/*
	 * 상품별 판매 실적 
	 
	public List<SalesQuantity> getProductSales(){
		return mybatis.selectList("ProductDAO.getProductSales");
	}
	*/
}
