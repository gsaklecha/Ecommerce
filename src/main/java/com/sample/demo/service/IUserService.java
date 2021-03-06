package com.sample.demo.service;

import com.sample.demo.exception.RecordNotFoundException;
import com.sample.demo.model.User;

public interface IUserService {
	
	/**
	 * Method to extract user information from token
	 * @param token
	 * @return
	 * @throws RecordNotFoundException 
	 */
	public User getUserByToken(String token) throws RecordNotFoundException;
	
	/**
	 * Method to get User by user id
	 * @param userId
	 * @return
	 * @throws RecordNotFoundException
	 */
	public User getUserById(long userId) throws RecordNotFoundException;

}
