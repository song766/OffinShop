package com.green.biz.product.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.green.biz.dao.ProductDAO;
import com.green.biz.dto.ProductVO;
import com.green.biz.product.ProductService;
import com.green.biz.utils.Criteria;

@Service("productService") // spring에 등록
public class ProductServiceImpl implements ProductService {
	
	@Autowired //Repository로 등록한 ProductDAO클래스 사용위함
	private ProductDAO pDao;
	
	@Override
	public List<ProductVO> getNewProductList() {
		return pDao.getNewProductList();
	}

	@Override
	public List<ProductVO> getNewProductListAll() {
		return pDao.getNewProductListAll();
	}
	
	@Override
	public List<ProductVO> getBestProductList() {
		return pDao.getBestProductList();
	}

	@Override
	public List<ProductVO> getBestProductListAll() {
		return pDao.getBestProductListAll();
	}
	
	@Override
	public ProductVO getProduct(ProductVO vo) {
		return pDao.getProduct(vo);
	}

	@Override
	public List<ProductVO> productListAll() {
		return pDao.productListAll();
	}
	
	@Override
	public List<ProductVO> productListAllSearch(String name){
		return pDao.productListAllSearch(name);
	}
	
	@Override
	public List<ProductVO> getProductListByKind(String kind) {
		return pDao.getProductListByKind(kind);
	}

	@Override
	public int countProductList(String name) {
		return pDao.countProductList(name);
	}

	@Override
	public List<ProductVO> listProduct(String name) {
		return pDao.listProduct(name);
	}

	@Override
	public void insertProduct(ProductVO vo) {
		pDao.insertProduct(vo);
	}

	@Override
	public void updateProduct(ProductVO vo) {
		pDao.updateProduct(vo);
	}

	@Override
	public void deleteProduct(int pseq) {
		pDao.deleteProduct(pseq);
	}
	
	@Override
	public List<ProductVO> getListWithPaging(Criteria criteria, String key) {
		return pDao.getListWithPaging(criteria, key);
	}


}
