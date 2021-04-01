package com.sample.demo.repository;

import java.util.List;

import com.sample.demo.model.CartItem;
import com.sample.demo.model.User;

public interface ICartItemDao {
	
	/**
	 * Method to get cart items
	 * @param user
	 * @return
	 */
	public List<CartItem> getAllCartItems(User user);

}
