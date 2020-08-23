package com.example.authserver.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.authserver.models.CustomUser;
import com.example.authserver.models.UserEntity;
import com.example.authserver.repos.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;
	

	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity user = userRepository.findByUserName(username);
		if (user != null) {
			CustomUser customUser = new CustomUser(user.getUserName(), user.getPassword(), user.getEnabled(),
					!user.getAccountExpired(), !user.getCredentialsExpired(), !user.getAccountLocked(),
					user.getAuthorities());
			return customUser;
		}
		throw new UsernameNotFoundException(username);
	}

}
