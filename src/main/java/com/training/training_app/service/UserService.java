package com.training.training_app.service;

import java.util.List;

import com.training.training_app.model.User;

public interface UserService {
	
	public User postUser(User user);
	public List<User> getAllPostedUser();
	public User findPostedUserById(Long id);
	public User updateUser(Long id,User user);
	public void deleteUser(Long id);

}
