package com.sample.demo.service;

import java.util.List;

import com.sample.demo.dto.OrderDTO;
import com.sample.demo.exception.RecordNotFoundException;
import com.sample.demo.model.Order;
import com.sample.demo.model.User;

public interface IOrderService {
	
	/**
	 * Method to get all orders of given user
	 * @param user
	 * @return
	 */
	public List<OrderDTO> getOrders(User user);
	
	/**
	 * Method to add order
	 * @param orderDTO
	 * @param user
	 * @return
	 * @throws RecordNotFoundException
	 */
	public Order addOrder(OrderDTO orderDTO, User user) throws RecordNotFoundException;

}
