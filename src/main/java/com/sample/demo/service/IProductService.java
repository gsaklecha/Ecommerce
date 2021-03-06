package com.sample.demo.service;

import java.util.List;

import com.sample.demo.exception.RecordNotFoundException;
import com.sample.demo.model.Product;
import com.sample.demo.model.User;

public interface IProductService {
	
	/**
	 * Method to get all products
	 * @return
	 */
	 public List<Product> getAllProducts();
	 
	 /**
	  * Method to get product by id
	  * @param id
	  * @return
	  * @throws RecordNotFoundException
	  */
	 public Product getProductById(long id) throws RecordNotFoundException;
	 
	 /**
	  * method to add product
	  * @param product
	  * @param user
	  * @return
	  */
	 public Product addProduct(Product product, User user) throws RecordNotFoundException;

}
