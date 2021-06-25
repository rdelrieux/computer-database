package com.excilys.computerDatabase.web.controller.api;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.excilys.computerDatabase.core.exception.security.AuthenticationException;
import com.excilys.computerDatabase.core.jwt.JwtUtil;
import com.excilys.computerDatabase.core.logger.LoggerCdb;
import com.excilys.computerDatabase.core.security.AuthenticationRequest;
import com.excilys.computerDatabase.core.security.AuthenticationToken;
import com.excilys.computerDatabase.service.UserService;

@RestController()
@RequestMapping("/api")
public class UserAPI {
	
	private AuthenticationManager authenticationManager;
	
	private UserService userService;
	
	private JwtUtil jwtUtil; 
	
	
	
	public UserAPI(AuthenticationManager authenticationManager, UserService userService, JwtUtil jwtUtil) {
		super();
		this.authenticationManager = authenticationManager;
		this.userService = userService;
		this.jwtUtil = jwtUtil;
	}



	@PostMapping("/login")
	public ResponseEntity<?> createAuthentificationToken(@RequestBody AuthenticationRequest authenticationRequest ) throws Exception{

		try {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken( authenticationRequest.getUsername(),authenticationRequest.getPassword()) );
		
		}catch(BadCredentialsException e) {
			LoggerCdb.logInfo(UserAPI.class.toString() , e);
			throw new AuthenticationException();	
		}
	
		final UserDetails userDetail = userService.loadUserByUsername(authenticationRequest.getUsername());
		
		final String jwtToken = jwtUtil.generateToken(userDetail);

		return ResponseEntity.ok().body(new AuthenticationToken(jwtToken)) ;
		
	}
	
	
	

}
