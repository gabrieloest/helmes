package com.helmes.challenge.challenge.service;

import java.util.List;
import java.util.Optional;

import com.helmes.challenge.challenge.model.User;

public interface UserService {
	User createUser(User user);

	Optional<User> getUser(Long id);

	User editUser(User user);

	void deleteUser(User user);

	void deleteUser(Long id);

	List<User> getAllUsers(int pageNumber, int pageSiz);

	List<User> getAllUsers();
}