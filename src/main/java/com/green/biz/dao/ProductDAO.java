package com.green.biz.dao;

import java.util.*;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.green.biz.dto.ProductVO;
import com.green.biz.utils.Criteria;

@Repository
public class ProductDAO {
	
	@Autowired // mybatis ��ü���� (applicationContext�� �ִ� sqlsessiontemplate ��ü�� �ڵ����� �޾ƿ�)
	private SqlSessionTemplate mybatis;
	
	// product-mapping.xml���� getNewProductListȣ��
	// �Ż�ǰ ���
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
	
	// ���θ����� ��ǰ��� ��ȸ (�˻�)
	public List<ProductVO> productListAllSearch(String name){
		return mybatis.selectList("ProductDAO.productListAllSearch", name);
	}
	
	// ��ȸ�� �� ��ǰ ���� - ���� name(��ȸ�� return�� �ʿ�)
	public int countProductList(String name) {
		return mybatis.selectOne("ProductDAO.countProductList", name); //�Է°�name
	}
	
	// ��ǰ ��� ��ȸ
	public List<ProductVO> listProduct(String name){
		return mybatis.selectList("ProductDAO.listProduct", name);
	}
	
	// ��ǰ �߰� - insert�� void�� 
	public void insertProduct(ProductVO vo) { // �Է°� ProductVO
		mybatis.insert("ProductDAO.insertProduct", vo);
	}
	
	// ��ǰ���� ���� - update
	public void updateProduct(ProductVO vo) {
		mybatis.update("ProductDAO.updateProduct", vo);
	}
	
	// ��ǰ���� ���� - delete
	public void deleteProduct(int pseq) {
		mybatis.delete("ProductDAO.deleteProduct", pseq);
	}
	
	/* ����¡ó��*/
	public List<ProductVO> getListWithPaging(Criteria criteria, String key){
		HashMap<String, Object> map = new HashMap<>();
		
		map.put("criteria", criteria);
		map.put("key", key);
		
		return mybatis.selectList("ProductDAO.listWithPaging", map);
	}
	
	/*
	 * ��ǰ�� �Ǹ� ���� 
	 
	public List<SalesQuantity> getProductSales(){
		return mybatis.selectList("ProductDAO.getProductSales");
	}
	*/
}
