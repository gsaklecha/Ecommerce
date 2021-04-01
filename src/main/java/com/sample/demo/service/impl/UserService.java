package com.sample.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sample.demo.exception.RecordNotFoundException;
import com.sample.demo.model.User;
import com.sample.demo.repository.IGenericDao;
import com.sample.demo.service.IUserService;

@Service
@Transactional
public class UserService implements IUserService {

	@Autowired
	UserService userService;

	IGenericDao<User> userDao;

	@Autowired
	public void setDao(IGenericDao<User> daoToSet) {
		userDao = daoToSet;
		userDao.setClazz(User.class);
	}
	
	@Override
	public User getUserByToken(String token) {
		User user = new User();
		if (token.equals("SELLER")) {
			user.setId(1L);
			user.setName("Seller");
			user.setSeller(true);
		} else {
			user.setId(2L);
			user.setName("Normal User");
			user.setSeller(false);
		}
		
		return user;
	}

	@Override
	public User getUserById(long userId) throws RecordNotFoundException {
		return userDao.findById(userId);
	}

}
