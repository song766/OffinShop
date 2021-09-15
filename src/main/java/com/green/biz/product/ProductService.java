package com.green.biz.product;

import java.util.List;

import com.green.biz.dto.ProductVO;
import com.green.biz.utils.Criteria;

public interface ProductService {

	// 신상품 목록
	List<ProductVO> getNewProductList();
	
	List<ProductVO> getNewProductListAll();

	List<ProductVO> getBestProductList();
	
	List<ProductVO> getBestProductListAll();

	ProductVO getProduct(ProductVO vo);

	List<ProductVO> getProductListByKind(String kind);
	
	List<ProductVO> productListAll();
	
	List<ProductVO> productListAllSearch(String name);

	// 조회한 총 상품 갯수 - 조건 name(조회엔 return값 필요)
	int countProductList(String name);

	// 상품 목록 조회
	List<ProductVO> listProduct(String name);

	// 상품 추가 - insert는 void로 
	void insertProduct(ProductVO vo);

	// 상품정보 수정 - update
	void updateProduct(ProductVO vo);
	
	// 상품정보 삭제 - delete
	public void deleteProduct(int pseq);
	
	//페이징처리
	public List<ProductVO> getListWithPaging(Criteria criteria, String key);

}