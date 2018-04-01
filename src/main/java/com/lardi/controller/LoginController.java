package com.lardi.controller;

import javax.validation.Valid;

import com.lardi.model.User;
import com.lardi.service.UserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

	private static final String REGISTRATION_SUCCESS = "Registration was success, please login";
	private static final String LOGIN_ERROR =  "*There is already a user registered with the login provided";
	private static final String LOGIN = "login";
	private static final String REGISTRATION = "registration";
	
	@Autowired
	private UserAuthService userAuthService;

	@RequestMapping(value={"/", "/login"}, method = RequestMethod.GET)
	public String login(){
		return LOGIN;
	}

	@RequestMapping(value="/registration", method = RequestMethod.GET)
	public String registration(ModelMap modelMap){
		User user = new User();
		modelMap.addAttribute("user", user);
		return REGISTRATION;
	}
	
	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public String registration(@Valid User user, BindingResult bindingResult, ModelMap modelMap) {
		User userExists = userAuthService.findUserByLogin(user.getLogin());
		if (userExists != null)
			bindingResult.rejectValue(LOGIN, "error.user", LOGIN_ERROR);

		if (bindingResult.hasErrors())
			return REGISTRATION;

		userAuthService.saveUser(user);
		modelMap.addAttribute("showMessage", true);
		modelMap.addAttribute("message", REGISTRATION_SUCCESS);
		return LOGIN;
	}
}
