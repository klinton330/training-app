package com.training.training_app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.training.training_app.dto.UserDTO;
import com.training.training_app.model.User;
import com.training.training_app.service.UserService;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping
	public ResponseEntity<User> postNewUser(@RequestBody UserDTO userDTO) {
		System.out.println(userDTO.getEmail());
		User postedUser = userService.postUser(changeToUser(userDTO));
		return new ResponseEntity<User>(postedUser, HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<User>> getAllPostedUser() {
		return new ResponseEntity<List<User>>(userService.getAllPostedUser(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<User> getPostedUserById(@PathVariable(name = "id") Long userId) {
		return new ResponseEntity<User>(userService.findPostedUserById(userId), HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<User> updatePostedUser(@PathVariable(name = "id") Long userId, @RequestBody UserDTO userDTO) {
		User updatedUser = userService.updateUser(userId, userDTO);
		return new ResponseEntity<User>(updatedUser, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletePostedUser(@PathVariable(name = "id") Long userId) {
		return new ResponseEntity<String>("User deleted successfully", HttpStatus.OK);
	}

	public User changeToUser(UserDTO userDTO) {
		User user = new User();
		user.setName(userDTO.getName());
		user.setEmail(userDTO.getEmail());
		user.setPassword(userDTO.getPassword());
		return user;
	}

	public UserDTO changeToUserDTO(User user) {
		UserDTO userDTO = new UserDTO();
		userDTO.setEmail(user.getEmail());
		userDTO.setName(user.getName());
		userDTO.setPassword(user.getName());
		return userDTO;

	}

}
