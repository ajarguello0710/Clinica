package edu.udec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/logout")
public class LogoutController {
	
	@Autowired
	private ConsumerTokenServices tokenServices;
	
	@GetMapping("/cancel/{tokenId:.*}")
	public void revocarToken(@PathVariable("tokenId") String token) {
		tokenServices.revokeToken(token);
	}

}
