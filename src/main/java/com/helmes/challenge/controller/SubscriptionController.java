package com.helmes.challenge.controller;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.helmes.challenge.model.Sector;
import com.helmes.challenge.model.Subscription;
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

		if (Objects.isNull(user)) {
			user = new User();
			Subscription subscription = new Subscription();
			subscription.setSectors(new HashSet<>());
			user.setSubscription(subscription);
		}
		model.addAttribute("user", user);

		if (sectors.isEmpty()) {
			sectors.addAll(SectorUtil.sortSectors(sectorService.getAllSectors()));
		}

		model.addAttribute("sectors", sectors);
		return "index";
	}

	@PostMapping("/index")
	public String save(@ModelAttribute("user") @Valid User user, @ModelAttribute("sectors") Set<Sector> sectors,
			BindingResult bindingResult, Model model, RedirectAttributes attributes) {

		validateFields(user, bindingResult);

		model.addAttribute("sectors", sectors);

		if (bindingResult.hasErrors()) {
			return "index";
		}

		attributes.addAttribute("user", userService.createUser(user));

		return "index";
	}

	private void validateFields(User user, BindingResult bindingResult) {
		if (!user.getSubscription().isTermAgreement()) {
			bindingResult
					.addError(new ObjectError("subscription.termAgreement", "You need to accept the agreement term!"));
		}

		if (user.getSubscription().getSectors().isEmpty()) {
			bindingResult.addError(new ObjectError("subscription.sectors", "You need to select at least one sector!"));
		}
	}

	@ModelAttribute("user")
	public User sessionUser() {
		return new User();
	}

	@ModelAttribute("sectors")
	public Set<Sector> sessionSector() {
		return new LinkedHashSet<>();
	}
}