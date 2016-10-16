package com.myretail.products;
/**
 * 
 * @author Sudhakar
 *
 */
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="product")
public class Product {

	@Id long productID ;
	
	
	public Price getPrice() {
		return price;
	}
	public void setPrice(Price price) {
		this.price = price;
	}
	Price price;
	public long getProductID() {
		return productID;
	}
	public void setProductID(long productID) {
		this.productID = productID;
	}
	

	
	@Override
	public String toString(){
		return productID+":"+price.getAmount()+" "+price.getCurrencycode();
	}
}
