package edu.udec.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import edu.udec.entity.Users;
import edu.udec.repository.IUserRepository;
import edu.udec.service.interfaces.IUserService;

@Service
public class UserServiceI implements IUserService, UserDetailsService {
	
	@Autowired
	private IUserRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users users = repository.findOneByNick(username);
		if(users == null)
			throw new UsernameNotFoundException("Usuario no encontrado");
		
		List<GrantedAuthority> roles = new ArrayList<>();
		roles.add(new SimpleGrantedAuthority(users.getRole().getNameRole()));
		
		UserDetails userDetails = new User(users.getNick(), users.getPassword(), roles);
		
		return userDetails;
	}
	
	

}
