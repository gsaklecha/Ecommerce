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

import com.sample.demo.dto.CartDTO;
import com.sample.demo.exception.PersistException;
import com.sample.demo.exception.RecordNotFoundException;
import com.sample.demo.exception.UnauthorizedException;
import com.sample.demo.model.CartItem;
import com.sample.demo.model.User;
import com.sample.demo.service.ICartService;
import com.sample.demo.service.IUserService;

@RestController
@RequestMapping("/api/cartItems")
public class CartController {

	@Autowired
	ICartService cartService;

	@Autowired
	IUserService userService;

	@GetMapping
	public @NotNull ResponseEntity<List<CartItem>> getCartItems(@RequestHeader("token") String token) throws RecordNotFoundException {
		User user = userService.getUserByToken(token);
		List<CartItem> list = cartService.getCartItems(user);
		return new ResponseEntity<List<CartItem>>(list, new HttpHeaders(), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<CartItem> addItemInCart(@RequestHeader("token") String token, @RequestBody CartDTO cartDTO)
			throws PersistException, UnauthorizedException, RecordNotFoundException {
		CartItem created = cartService.addItemInCart(cartDTO);
		return new ResponseEntity<CartItem>(created, new HttpHeaders(), HttpStatus.CREATED);
	}
}
