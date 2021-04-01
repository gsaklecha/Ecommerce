package com.sample.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sample.demo.dto.CartDTO;
import com.sample.demo.exception.RecordNotFoundException;
import com.sample.demo.model.CartItem;
import com.sample.demo.model.Product;
import com.sample.demo.model.User;
import com.sample.demo.repository.impl.CartItemDao;
import com.sample.demo.service.ICartService;

@Service
@Transactional
public class CartService implements ICartService {

	@Autowired
	ProductService productService;

	@Autowired
	UserService userService;
	
	@Autowired
	CartItemDao cartDao;

	@Override
	public List<CartItem> getCartItems(User user) {
		return cartDao.getAllCartItems(user);
	}

	@Override
	public CartItem addItemInCart(CartDTO cartDTO) throws RecordNotFoundException {
		Product product = productService.getProductById(cartDTO.getProductId());
		User user = userService.getUserById(cartDTO.getUserId());
		CartItem cartItem = new CartItem();
		cartItem.setProduct(product);
		cartItem.setUser(user);
		return cartDao.save(cartItem);
	}

}
