package com.sample.demo.controller;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sample.demo.exception.PersistException;
import com.sample.demo.exception.RecordNotFoundException;
import com.sample.demo.exception.UnauthorizedException;
import com.sample.demo.model.Product;
import com.sample.demo.model.User;
import com.sample.demo.service.IProductService;
import com.sample.demo.service.IUserService;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    IProductService productService;
    
    @Autowired
    IUserService userService;

    @GetMapping
    public @NotNull ResponseEntity<List<Product>> getProducts(@RequestHeader("token") String token) {
    	List<Product> list = productService.getAllProducts();
		return new ResponseEntity<List<Product>>(list, new HttpHeaders(), HttpStatus.OK);
    }
    
	@GetMapping("/{id}")
	public ResponseEntity<Product> getProductById(@RequestHeader("token") String token, @PathVariable("id") Long id) throws RecordNotFoundException {
		Product entity = productService.getProductById(id);
		return new ResponseEntity<Product>(entity, new HttpHeaders(), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Product> createProduct(@RequestHeader("token") String token, @RequestBody Product product) throws PersistException, UnauthorizedException, RecordNotFoundException {
		User user = userService.getUserByToken(token);
		if (user.isSeller()) {
			Product created = productService.addProduct(product, user.getId());
			return new ResponseEntity<Product>(created, new HttpHeaders(), HttpStatus.CREATED);
		} else {
			throw new UnauthorizedException("You do not have permission to add product");
		}
	}

}