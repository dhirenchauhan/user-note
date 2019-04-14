package com.gp.usernote.security.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

import com.gp.usernote.service.UserDetailsServiceImpl;


@Configuration
@EnableWebSecurity
public class BasicAuthSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	@Autowired
	public void configureGlobal(final AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(encoder());
	}

	public PasswordEncoder encoder() {
	    String encodingId = "bcrypt";
	    Map<String, PasswordEncoder> encoders = new HashMap<>();
	    encoders.put(encodingId, new BCryptPasswordEncoder());
	    encoders.put("ldap", new org.springframework.security.crypto.password.LdapShaPasswordEncoder());
	    encoders.put("MD4", new org.springframework.security.crypto.password.Md4PasswordEncoder());
	    encoders.put("MD5", new org.springframework.security.crypto.password.MessageDigestPasswordEncoder("MD5"));
	    encoders.put("noop", org.springframework.security.crypto.password.NoOpPasswordEncoder.getInstance());
	    encoders.put("SHA-1", new org.springframework.security.crypto.password.MessageDigestPasswordEncoder("SHA-1"));
	    encoders.put("SHA-256", new org.springframework.security.crypto.password.MessageDigestPasswordEncoder("SHA-256"));
	    encoders.put("sha256", new org.springframework.security.crypto.password.StandardPasswordEncoder());

	    return new DelegatingPasswordEncoder(encodingId, encoders);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable().authorizeRequests()
				// .antMatchers("/notes/**").hasRole("USER")
				.anyRequest().authenticated().and().httpBasic();
	}

}