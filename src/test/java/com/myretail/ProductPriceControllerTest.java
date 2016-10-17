package com.myretail;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.myretail.controller.ProductPriceController;
import com.myretail.products.Price;
import com.myretail.products.Product;
import com.myretail.products.dao.ProductDAO;
import com.myretail.products.dao.ProductDAOImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
//@ContextConfiguration(classes = Application.class)
public class ProductPriceControllerTest {

	@Autowired
	private MockMvc mvc;

	
	
	
	
	@Mock
	private ProductDAO daoMock = new ProductDAOImpl(null);
	@Mock
	private ProductPriceController service;
	
	@Test
	public void testHome() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().string(equalTo("My Retail Application to manage product prices")));
	}

	
	@Test
	public void getProduct() throws Exception {
		long productID = 1241;
		Product testProduct = new Product();
		testProduct.setProductID(productID);
		Price price = new Price(241.99, "USD");
		testProduct.setPrice(price);
		mock(ProductDAOImpl.class);
		when(daoMock.readById(productID)).thenReturn(testProduct);

		mvc.perform(MockMvcRequestBuilders.get("/product/1241")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.productID", is(1241)))
				.andExpect(jsonPath("$.price.amount",is(241.99)) );
	}
	
	
	@Test
	public void getProductNotfound() throws Exception {
		long productID = 1238;
		
		when(daoMock.readById(productID)).thenReturn(null);

		mvc.perform(MockMvcRequestBuilders.get("/product/1238").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().is4xxClientError());
	}
}
