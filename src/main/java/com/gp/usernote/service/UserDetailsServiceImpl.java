package com.gp.usernote.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Class : UserDetailsServiceImpl
 * Purpose : This class is for supporting User Authentication
 * User details fetched from DB pass it on too UseerDetails Object
 * That will allow it to serve Authentication through WebSecurityConfigurer
 * @author dhiren
 *
 */

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserService userService;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		com.gp.usernote.model.User user = userService.findUserByEmail(email);
		User userDetails = new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), Arrays.asList());
		return userDetails;
	}

}
