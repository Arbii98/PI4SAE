package aces.esprit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import aces.esprit.entity.Users;
import aces.esprit.repository.UsersRepository;
import aces.esprit.utile.MyUserDetails;

@Service
public class MyUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UsersRepository ur;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users user = ur.getUserByUsername(username);
		
		if (user == null) {
			throw new UsernameNotFoundException("Could not find user");
		}
		
		return new MyUserDetails(user);
	}

}
