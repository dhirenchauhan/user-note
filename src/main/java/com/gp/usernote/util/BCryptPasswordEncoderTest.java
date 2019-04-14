package com.gp.usernote.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Class : BCryptPasswordEncoderTest
 * Purpose : BCrypt utility to generate encropted passsword
 * @author dhiren
 *
 */

public class BCryptPasswordEncoderTest {

	public static void main(String[] args) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		System.out.println(encoder.encode("testpassword"));
	}
}
