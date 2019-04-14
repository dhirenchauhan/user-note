package com.gp.usernote.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.gp.usernote.service.UserDetailsServiceImpl;

/**
 * Class : BasicAuthSecurityConfiguration
 * Purpose : This class provide Basic Authentication for User
 * Before serving request, User is authentication with Username and Password
 * stored in DB. This class also support BCrypt password encription
 * @author dhiren
 *
 */

@Configuration
@EnableWebSecurity
public class BasicAuthSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	@Autowired
	public void configureGlobal(final AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}

/*	
	//If Above BCryptPassswordEncoder doesn't work then Comment Above configureGlobal function 
	// And uncomment below two functions to enable simple Password Authentication
  	@Autowired
	public void configureGlobal(final AuthenticationManagerBuilder auth) throws Exception {
		//auth.userDetailsService(userDetailsService).passwordEncoder(encoder());
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
*/
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()
				.anyRequest().authenticated().and().httpBasic();
	}

}