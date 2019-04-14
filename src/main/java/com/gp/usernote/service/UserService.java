package com.gp.usernote.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gp.usernote.model.User;
import com.gp.usernote.repository.UserRepository;
import com.gp.usernote.util.ServiceConditionCheck;

/**
 * Class : UserService
 * Purpose : This is service class to Support User Service Operation
 * @author dhiren
 *
 */

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public User findUserByEmail(String email) {
		Optional<User> user = userRepository.findUserByEmail(email);
		ServiceConditionCheck.checkOkArgument(user.isPresent());
		return user.get();
	}
}
