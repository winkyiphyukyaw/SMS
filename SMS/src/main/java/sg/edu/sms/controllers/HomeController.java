package sg.edu.sms.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;

import sg.edu.sms.models.UserSession;


@Controller
@RequestMapping("/sms")
public class HomeController {
	
	@GetMapping("/home")
	public String getHome() {
		return "home";
	}
	
	/*
	 * @GetMapping("/login/{role}") public String getLoginPage(Model
	 * model,@PathVariable("role") Integer role) { model.addAttribute("user", new
	 * UserSession()); return "login"; }
	 */
	
	@GetMapping("/login")
	public String getLoginPage(Model model) {
		model.addAttribute("user", new UserSession());
		return "login";
	}
	
	@GetMapping("/logout")
	public String getLogoutPage(SessionStatus status) {
		status.setComplete();
		return "home";
	}
	
	@PostMapping("/authenticate")
	public String getAuthentication(@ModelAttribute("user") UserSession user, BindingResult bindingResult) {
		if(user.getName().equalsIgnoreCase("kyiphyu"))
			return "redirect:/faculty/masterlist";
		else
			return "login";
	}
}
