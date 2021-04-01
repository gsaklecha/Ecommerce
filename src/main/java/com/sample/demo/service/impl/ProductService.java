package com.sample.demo.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sample.demo.exception.RecordNotFoundException;
import com.sample.demo.model.Product;
import com.sample.demo.model.User;
import com.sample.demo.repository.IGenericDao;
import com.sample.demo.service.IProductService;

@Service
@Transactional
public class ProductService implements IProductService {
	
	IGenericDao<Product> productDao;
	
	@Autowired
	public void setDao(IGenericDao<Product> daoToSet) {
		productDao = daoToSet;
		productDao.setClazz(Product.class);
	}
	
    @Override
    public List<Product> getAllProducts() {
        return productDao.findAll();
    }

    @Override
    public Product getProductById(long id) throws RecordNotFoundException {
        return productDao.findById(id);
    }

    @Override
    public Product addProduct(Product product, User user) throws RecordNotFoundException {
    	product.setVersionId(UUID.randomUUID().toString());
    	product.setUser(user);
        return productDao.save(product);
    }

}
