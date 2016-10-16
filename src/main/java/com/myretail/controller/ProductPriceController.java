package com.myretail.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.myretail.Application;
import com.myretail.exception.MyRetailException;
import com.myretail.products.Product;
import com.myretail.products.dao.ProductDAO;

@RestController
public class ProductPriceController {
	static Logger logger = LoggerFactory.getLogger(Application.class);

	@Autowired
	private ProductDAO productDAO;

	@Autowired
	private ApplicationContext appContext;

	public ApplicationContext getAppContext() {
		return appContext;
	}

	public void setAppContext(ApplicationContext appContext) {
		this.appContext = appContext;
	}

	@RequestMapping("/")
	public String index() {
		return "My Retail Application to manage product prices";
	}

	
	
	@RequestMapping(value="/product/{productID}",produces = "application/text",method=RequestMethod.PUT)
	public String addProduct(@PathVariable(value="productID") long productID, @RequestBody Product productb) throws MyRetailException {
		logger.info("Update product..."+productID);
		
		productb.setProductID(productID);
		
		try {
			productDAO.update(productb);
		} catch ( MyRetailException e) {
			logger.error( e.getMessage(),e);
			return "{Error in updating}";
		}
		
		return "{updated: " + productb.toString()+"}";
	}
	
	
	@RequestMapping(value = "/product/{productID}", produces = "application/JSON",method=RequestMethod.GET)
	public Product getProductPrice(@PathVariable(value="productID") long productID) {
		logger.info( "Fetch product..."+productID);
		Product prod = productDAO.readById(productID);
		logger.debug("Get:"+prod);
		if(prod==null){
			//throw 404 error
			throw new ResourceNotFoundException(); 
		
		}
		return prod;
	}
	
	@RequestMapping(value = "/products", produces = "application/JSON")
	public List<Product> listProducts() {
		logger.info( "Fetch products...");

		return productDAO.findAll();
	}
	
}
