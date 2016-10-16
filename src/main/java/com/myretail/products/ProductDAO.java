package com.myretail.products;

import java.util.List;

import com.myretail.exception.MyRetailException;

public interface ProductDAO {
	
	public List<Product> findAll();

	public void create(Product p) throws MyRetailException;
	
	public Product readById(long id);
	
	public void update(Product p) throws MyRetailException;
	
	public int deleteById(String id);
}
