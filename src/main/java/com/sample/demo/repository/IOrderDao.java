package com.sample.demo.repository;

import java.util.List;

import com.sample.demo.model.Order;
import com.sample.demo.model.User;

public interface IOrderDao {
	
	/**
	 * Method to get all order by user
	 * @param user
	 * @return
	 */
	public List<Order> getOrders(User user);

}
