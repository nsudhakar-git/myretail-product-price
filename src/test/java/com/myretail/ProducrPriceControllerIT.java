package com.myretail;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.net.URL;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.myretail.products.Product;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes=Application.class )

public class ProducrPriceControllerIT {

    private static final double DELTA = 1e-15;

	@LocalServerPort
    private int port;

    private URL base;

    @Autowired
    private TestRestTemplate template;

    @Before
    public void setUp() throws Exception {
        this.base = new URL("http://localhost:" + port + "/");
    }

    @Test
    public void getHello() throws Exception {
        ResponseEntity<String> response = template.getForEntity(base.toString(),
                String.class);
        assertEquals(response.getBody().toString(), "My Retail Application to manage product prices");
    }
    
    @Test
    public void getProductwithPrice() throws Exception {
    	long productID=10024;
        ResponseEntity<Product> response = template.getForEntity(base.toString()+"/product/"+productID,
        		Product.class);
        Product responseProd = response.getBody();
        assertNotNull("Object null",responseProd);
        assertEquals(response.getHeaders().getContentType(),TestUtil.APPLICATION_JSON_UTF8);
        assertEquals(responseProd.getClass(), Product.class);
		assertEquals("Error comparing price",responseProd.getPrice().getAmount(),24024.24,DELTA);
    }
    
    @Test
    public void getProductwithPrice2() throws Exception {
    	long productID=10025;
        ResponseEntity<Product> response = template.getForEntity(base.toString()+"/product/"+productID,
        		Product.class);
        Product responseProd = response.getBody();
        assertNotNull("Object null",responseProd);
        assertEquals(response.getHeaders().getContentType(),TestUtil.APPLICATION_JSON_UTF8);
        assertEquals(responseProd.getClass(), Product.class);
		assertEquals("Error comparing price",responseProd.getPrice().getAmount(),25025.22,DELTA);
    }
    
    
    @Test()
    public void getProductwithNoProduct() throws Exception {
    	long productID=100;
        ResponseEntity<Product> response = template.getForEntity(base.toString()+"/product/"+productID,
        		Product.class);
        Product responseProd = response.getBody();
        assertEquals(response.getStatusCode(),HttpStatus.NOT_FOUND);
      	assertNull("Verifying  price",responseProd.getPrice());
    }
    
    @Test
    public void getProductwithNoPrice() throws Exception {
    	long productID=10026;
        ResponseEntity<Product> response = template.getForEntity(base.toString()+"/product/"+productID,
        		Product.class);
        Product responseProd = response.getBody();
        assertNotNull("Object null",responseProd);
        assertEquals(response.getHeaders().getContentType(),TestUtil.APPLICATION_JSON_UTF8);
        assertEquals(responseProd.getClass(), Product.class);
		assertNull("Verifying price",responseProd.getPrice());
    }
}
