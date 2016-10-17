package com.myretail.products.dao;

import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.mongodb.WriteResult;
import com.myretail.Application;
import com.myretail.exception.MyRetailException;
import com.myretail.products.Product;

@Component
public class ProductDAOImpl implements ProductDAO {
	static Logger logger = Logger.getLogger(Application.class);
	@Autowired
	private MongoOperations mongoOps;

	private static final String PERSON_COLLECTION = "Product";

	public ProductDAOImpl(MongoOperations mongoOps) {
		this.mongoOps = mongoOps;
	}
	
	
	@Override
	public void create(Product p) throws MyRetailException {
		try {
			this.mongoOps.insert(p, PERSON_COLLECTION);
		} catch (Exception e) {
			logger.log(Level.ERROR, e.getMessage());
			if (e.getMessage().contains("Duplicate")) {
				throw new MyRetailException(e);
			}
		}
	}
	
	@Override
	public Product readById(long id) {
		Query query = new Query(Criteria.where("_id").is(id));
		return this.mongoOps.findOne(query, Product.class, PERSON_COLLECTION);
	}
	
	@Override
	public void update(Product p) throws MyRetailException {
		try {

			this.mongoOps.save(p, PERSON_COLLECTION);
		} catch (Exception e) {
			logger.log(Level.ERROR, e.getMessage());
			if (e.getMessage().contains("Duplicate")) {
				throw new MyRetailException(e);
			}
		}
	}

	@Override
	public int deleteById(String id) {
		Query query = new Query(Criteria.where("_id").is(id));
		WriteResult result = this.mongoOps.remove(query, Product.class, PERSON_COLLECTION);
		return result.getN();
	}

    @Override
	public List<Product> findAll() {
		return mongoOps.findAll(Product.class, PERSON_COLLECTION);
	}

}
