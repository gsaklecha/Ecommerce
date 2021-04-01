package com.sample.demo.service.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sample.demo.dto.OrderDTO;
import com.sample.demo.dto.OrderProductDTO;
import com.sample.demo.exception.RecordNotFoundException;
import com.sample.demo.model.Order;
import com.sample.demo.model.OrderProduct;
import com.sample.demo.model.Product;
import com.sample.demo.model.User;
import com.sample.demo.repository.impl.OrderDao;
import com.sample.demo.service.IOrderService;

@Service
@Transactional
public class OrderService implements IOrderService {

	@Autowired
	ProductService productService;
	
	@Autowired
	OrderDao orderDao;

	@Autowired
	UserService userService;
	
	@Override
	public List<OrderDTO> getOrders(User user) {
		List<Order> orders = orderDao.getOrders(user);
		List<OrderDTO> orderDTOs = new ArrayList<OrderDTO>();
		for(Order order: orders) {
			OrderDTO orderDTO = EntityToDTOMapper.mapOrderEntityToDTO(order);
			orderDTOs.add(orderDTO);
		}
		return orderDTOs;
	}

	@Override
	public Order addOrder(OrderDTO orderDTO, long userId) throws RecordNotFoundException {
		Order order = new Order();
		order.setStatus("PENDING");
		order.setDateCreated(new Date(System.currentTimeMillis()));
		order.setUser(userService.getUserById(userId));
		List<OrderProduct> orderProducts = new ArrayList<OrderProduct>();
		for(OrderProductDTO orderProductDTO: orderDTO.getProducts()) {
			OrderProduct orderProduct = new OrderProduct();
			Product product = productService.getProductById(orderProductDTO.getProductId());
			orderProduct.setOrder(order);
			orderProduct.setProduct(product);
			orderProduct.setQuantity(orderProductDTO.getQuantity());
			orderProducts.add(orderProduct);
		}
		order.setOrderProducts(orderProducts);
		orderDao.save(order);
		return order;
	}
	
}
