package com.helmes.challenge.controller;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.helmes.challenge.model.Subscription;
import com.helmes.challenge.model.User;
import com.helmes.challenge.service.SectorService;
import com.helmes.challenge.service.UserService;

@Controller
public class LoginController {

	@Autowired
	private UserService userService;

	@Autowired
	private SectorService sectorService;

	@RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
	public String index(Model model) {
		User user = new User();
		Subscription subscription = new Subscription();
		subscription.setSectors(new HashSet<>());
		user.setSubscription(subscription);

		model.addAttribute("user", user);
		model.addAttribute("sectors", sectorService.getAllSectors());
		return "index";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(User user) {
		userService.createUser(user);
		return "redirect:/";
	}
}