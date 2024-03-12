package com.grp3.bid.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.grp3.bid.entities.User;
import com.grp3.bid.repositories.UserDAOInterface;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private UserDAOInterface userRepository;

	@Override
	public UserDetails loadUserByUsername(String pseudo) {

		User user;
		try {
			user = userRepository.findByPseudo(pseudo);
			if (user == null) {
				throw new UsernameNotFoundException(pseudo);
			}
		} catch (Exception e) {
			throw new UsernameNotFoundException(pseudo);
		}
		return new MyUserDetail(user);
	}
}