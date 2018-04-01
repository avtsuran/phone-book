package com.lardi.service;

import java.util.Arrays;
import java.util.HashSet;

import com.lardi.model.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.lardi.model.Role;
import com.lardi.model.User;
import com.lardi.repository.RoleRepository;
import com.lardi.repository.UserRepository;

@Service("userService")
public class UserAuthServiceImpl implements UserAuthService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	protected AuthenticationManager authenticationManager;
	
	@Override
	public User findUserByLogin(String login) {
		return userRepository.findUserByLogin(login);
	}

	@Override
	public void saveUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(1);
        Role userRole = roleRepository.findByRole("ADMIN");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		userRepository.save(user);
	}

	@Override
	public User getAuthUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return userRepository.findUserByLogin(auth.getName());
	}

	@Override
	public boolean getAccess(Contact contact) {
		if(contact.getUser().getId().equals(this.getAuthUser().getId()))
			return true;
		else
			return false;
	}
}
