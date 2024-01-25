package com.school.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.school.Repository.UserRepository;

@Service
public class CustomUserDetailService  implements  UserDetailsService {
	@Autowired
	private UserRepository userRepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//map is used to throw custom exception given by user(exception threw from only within the map)
		return userRepository.findByUserName(username).map(user-> new CustomUserDetails(user)).orElseThrow(
				()->new UsernameNotFoundException("Failed to authenticate the user"));

	}
}
