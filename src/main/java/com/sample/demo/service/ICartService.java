package com.sample.demo.service;

import java.util.List;

import com.sample.demo.dto.CartDTO;
import com.sample.demo.exception.RecordNotFoundException;
import com.sample.demo.model.CartItem;
import com.sample.demo.model.User;

public interface ICartService {
	
	/**
	 * Method to get cart items
	 * @param user
	 * @return
	 */
	public List<CartItem> getCartItems(User user);
	
	/**
	 * Method to add item in cart
	 * @param cartDTO
	 * @return
	 * @throws RecordNotFoundException
	 */
	public CartItem addItemInCart(CartDTO cartDTO) throws RecordNotFoundException;

}
