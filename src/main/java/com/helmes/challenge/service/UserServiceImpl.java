package com.helmes.challenge.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.helmes.challenge.model.User;
import com.helmes.challenge.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;

	@Override
	public User createUser(User user) {
		return this.userRepository.save(user);
	}

	@Override
	public Optional<User> getUser(Long id) {
		return this.userRepository.findById(id);
	}

	@Override
	@Transactional
	public User editUser(Long id, User user) {
		userRepository.findById(id);
		return this.userRepository.save(user);
	}

	@Override
	public void deleteUser(User user) {
		this.userRepository.delete(user);
	}

	@Override
	public void deleteUser(Long id) {
		this.userRepository.deleteById(id);
	}

	@Override
	public List<User> getAllUsers(int pageNumber, int pageSize) {
		return this.userRepository.findAll(PageRequest.of(pageNumber, pageSize)).getContent();
	}

	@Override
	public List<User> getAllUsers() {
		return this.userRepository.findAll();
	}
}