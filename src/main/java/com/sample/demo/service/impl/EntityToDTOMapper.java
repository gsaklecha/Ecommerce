package com.sample.demo.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.sample.demo.dto.OrderDTO;
import com.sample.demo.dto.OrderProductDTO;
import com.sample.demo.model.Order;
import com.sample.demo.model.OrderProduct;

/**
 * Mapper class
 * @author qe3nrt
 *
 */
public class EntityToDTOMapper {
	
	public static OrderDTO mapOrderEntityToDTO(Order order) {
		OrderDTO orderDTO = new OrderDTO();
		orderDTO.setOrderId(order.getId());
		orderDTO.setOrderDate(order.getDateCreated());
		orderDTO.setStatus(order.getStatus());
		orderDTO.setTotalPrice(order.getTotalOrderPrice());
		List<OrderProductDTO> products = new ArrayList<OrderProductDTO>();
		for (OrderProduct orderProduct : order.getOrderProducts()) {
			OrderProductDTO orderProductDTO = new OrderProductDTO();
			orderProductDTO.setProductId(orderProduct.getProduct().getId());
			orderProductDTO.setProductName(orderProduct.getProduct().getProductName());
			orderProductDTO.setQuantity(orderProduct.getQuantity());
			products.add(orderProductDTO);
		}
		orderDTO.setProducts(products);
		return orderDTO;
	}

}
