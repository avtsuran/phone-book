package com.lardi.service;

import com.lardi.model.Contact;
import com.lardi.model.User;

public interface UserAuthService {
	User findUserByLogin(String login);
	void saveUser(User user);
	User getAuthUser();
	boolean getAccess(Contact contact);
}
