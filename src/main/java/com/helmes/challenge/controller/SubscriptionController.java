package com.helmes.challenge.controller;

import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.helmes.challenge.model.Sector;
import com.helmes.challenge.model.User;
import com.helmes.challenge.service.SectorService;
import com.helmes.challenge.service.UserService;
import com.helmes.challenge.util.SectorUtil;

@Controller
@RequestMapping("/")
@SessionAttributes(value = { "user", "sectors" })
public class SubscriptionController {

	@Autowired
	private UserService userService;

	@Autowired
	private SectorService sectorService;

	@GetMapping(value = { "/index", "/" })
	public String index(Model model, @ModelAttribute("user") User user,
			@ModelAttribute("sectors") Set<Sector> sectors) {

		model.addAttribute("user", user);
		model.addAttribute("sectors", sectors);
		return "index";
	}

	@PostMapping("/index")
	public String save(@ModelAttribute("user") @Valid User user, BindingResult bindingResult,
			@ModelAttribute("sectors") Set<Sector> sectors, Model model) {

		user.validate().forEach(it -> bindingResult.addError(it));

		if (bindingResult.hasErrors()) {
			return "index";
		}

		model.addAttribute("sectors", sectors);
		model.addAttribute("user", userService.createUser(user));
		model.addAttribute("message", "Success");

		return "index";
	}

	@ModelAttribute("user")
	public User sessionUser() {
		return new User();
	}

	@ModelAttribute("sectors")
	public Set<Sector> sessionSector() {
		return SectorUtil.sortSectors(sectorService.getAllSectors());
	}
}