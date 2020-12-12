package lolguide.areas.users.controllers;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lolguide.areas.users.models.binding.UserRegistrationBindingModel;
import lolguide.areas.users.services.SecurityUserService;
import lolguide.constants.ViewConstants;

@Controller
@RequestMapping("user")
public class SecurityUserController {
	
	private final SecurityUserService securityUserService;
	
	@Autowired
	public SecurityUserController(SecurityUserService securityUserService) {
		this.securityUserService = securityUserService;
	}
	
	@GetMapping("/login")
	public String login(Model model, Principal principal) {
		
		if(principal != null) {
			
			model.addAttribute("view", ViewConstants.HomeIndexView);
			
		}else {
			model.addAttribute("view", ViewConstants.LoginView);
		}
		
		return ViewConstants.BaseLayoutView;
	}
	
	@GetMapping("/register")
	public String register(Model model, Principal principal) {
		
		if(principal != null) {
			
			model.addAttribute("view", ViewConstants.HomeIndexView);
			
		}else {
			model.addAttribute("registrationModel", new UserRegistrationBindingModel());
			model.addAttribute("view", ViewConstants.RegisterView);
		}
		
		return ViewConstants.BaseLayoutView;
	}
	
	@PostMapping("/register")
    public String registerProcess(UserRegistrationBindingModel userRegistrationModel, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userRegistrationModel", userRegistrationModel);            
            return "redirect:/user/register";
        }

        this.securityUserService.register(userRegistrationModel);

        return "redirect:/user/login";
    }
	
	@GetMapping("/logout")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response, Authentication auth) {

        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }

        return "redirect:/";
    }
	
}
