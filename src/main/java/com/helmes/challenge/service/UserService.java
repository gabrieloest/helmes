package com.helmes.challenge.service;

import java.util.List;
import java.util.Optional;

import com.helmes.challenge.model.User;

public interface UserService {
	User createUser(User user);

	Optional<User> getUser(Long id);

	User editUser(Long id, User user);

	void deleteUser(User user);

	void deleteUser(Long id);

	List<User> getAllUsers(int pageNumber, int pageSiz);

	List<User> getAllUsers();
}