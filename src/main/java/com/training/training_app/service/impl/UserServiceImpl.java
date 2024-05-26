package com.training.training_app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.training_app.dto.UserDTO;
import com.training.training_app.model.User;
import com.training.training_app.repository.UserRepository;
import com.training.training_app.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public User postUser(User user) {
		User postedUser = userRepository.save(user);
		return postedUser;
	}

	@Override
	public List<User> getAllPostedUser() {
		return userRepository.findAll();
	}

	@Override
	public User findPostedUserById(Long id) {
		User getUser = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User Id not found"));
		return getUser;
	}

	@Override
	public User updateUser(Long id, UserDTO userdto) {
		User updatedUser = userRepository.findById(id).map(x -> {
			x.setEmail(userdto.getEmail());
			x.setName(userdto.getName());
			x.setPassword(userdto.getPassword());
			return userRepository.save(x);
		}).orElseThrow(() -> new RuntimeException("User Id not found"));
		return updatedUser;
	}

	@Override
	public void deleteUser(Long id) {
		User getUser = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User Id not found"));
		if (getUser != null)
			userRepository.deleteById(id);

	}

}
