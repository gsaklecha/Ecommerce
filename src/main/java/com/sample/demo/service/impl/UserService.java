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
	public User getUserByToken(String token) throws RecordNotFoundException {
		if (token.equals("SELLER")) {
			return this.getUserById(1);
		} else {
			return this.getUserById(2);
		}
	}

	@Override
	public User getUserById(long userId) throws RecordNotFoundException {
		return userDao.findById(userId);
	}

}
