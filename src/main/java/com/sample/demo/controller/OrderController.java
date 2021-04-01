package com.sample.demo.controller;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sample.demo.dto.OrderDTO;
import com.sample.demo.exception.PersistException;
import com.sample.demo.exception.RecordNotFoundException;
import com.sample.demo.exception.UnauthorizedException;
import com.sample.demo.model.Order;
import com.sample.demo.model.User;
import com.sample.demo.service.IOrderService;
import com.sample.demo.service.IUserService;
import com.sample.demo.service.impl.EntityToDTOMapper;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

	@Autowired
	IOrderService orderService;

	@Autowired
	IUserService userService;

	@GetMapping
	public @NotNull ResponseEntity<List<OrderDTO>> getOrders(@RequestHeader("token") String token) {
		User user = userService.getUserByToken(token);
		List<OrderDTO> list = orderService.getOrders(user);
		return new ResponseEntity<List<OrderDTO>>(list, new HttpHeaders(), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<OrderDTO> createOrder(@RequestHeader("token") String token, @RequestBody OrderDTO orderDTO)
			throws PersistException, UnauthorizedException, RecordNotFoundException {
		User user = userService.getUserByToken(token);
		Order order = orderService.addOrder(orderDTO, user.getId());
		OrderDTO dto = EntityToDTOMapper.mapOrderEntityToDTO(order);
		return new ResponseEntity<OrderDTO>(dto, new HttpHeaders(), HttpStatus.CREATED);
	}
}
