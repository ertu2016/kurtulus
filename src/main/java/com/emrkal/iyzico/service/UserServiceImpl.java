package com.emrkal.iyzico.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.emrkal.iyzico.model.User;
import com.emrkal.iyzico.repository.UserRepository;

/**
 * User {@link com.emrkal.iyzico.model.User} modeline ait servis sınıfıdır.
 * <p>
 * 
 * @see UserService
 * @version 1.0
 * @author Emrullah KALKAN
 */
@Service
public class UserServiceImpl implements UserService, UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	private static final Logger logger = LoggerFactory.getLogger(UserService.class);

	@Override
	public User getUserById(long id) {
		return userRepository.findOne(id);
	}

	public User getUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public User addNewUser(User user) {
		return userRepository.save(user);
	}

	@Override
	@SuppressWarnings("unchecked")
	public UserDetails loadUserByUsername(String username) {
		User user = getUserByUsername(username);
		List<SimpleGrantedAuthority> auth = null;
		try {
			auth = (List<SimpleGrantedAuthority>) user.getAuthorities();

		} catch (UsernameNotFoundException e) {
			logger.error("User bulunamadı!", e);
		}
		return buildUserForAuth(user, auth);

	}

	private org.springframework.security.core.userdetails.User buildUserForAuth(com.emrkal.iyzico.model.User user, List<SimpleGrantedAuthority> auth) {
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), true, true, true, true, auth);
	}

}
