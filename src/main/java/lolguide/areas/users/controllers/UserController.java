package lolguide.areas.users.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lolguide.areas.users.entities.User;
import lolguide.areas.users.models.binding.UpdateProfileBindingModel;
import lolguide.areas.users.services.UserService;
import lolguide.constants.ViewConstants;

@Controller
@RequestMapping("user")
public class UserController {
	
	
	private final UserService userService;
	
	
	@Autowired
	public UserController(UserService userService) {
		
		this.userService = userService;
	}
	
	@GetMapping("profile")
	public String profile(Model model, Principal logUser) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = this.userService.getUserByUsername(logUser.getName());
		model.addAttribute("followed", userService.getFollowed(auth.getName()));
		model.addAttribute("user", user);
		model.addAttribute("isEditable", false);
		model.addAttribute("guides", user.getMyGuides());
		model.addAttribute("favoritesGuides", user.getFavoriteGuides());
		model.addAttribute("view",ViewConstants.ProfileView);
		
		return  ViewConstants.BaseLayoutView;
	}
	
	@GetMapping("profile/{id}")
	public String userProfile(Model model, Principal logUser, @PathVariable Integer id) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = this.userService.getUserById(id);
		model.addAttribute("followed", userService.getFollowed(auth.getName()));
		model.addAttribute("user", user);
		model.addAttribute("isEditable", true);
		model.addAttribute("guides", user.getMyGuides());
		model.addAttribute("favoritesGuides", user.getFavoriteGuides());
		model.addAttribute("view",ViewConstants.ProfileView);
		
		return  ViewConstants.BaseLayoutView;
	}
	
	@PostMapping("profile")
	public String updateProfile(UpdateProfileBindingModel bindingModel, BindingResult result) {
		
//		if(result.hasErrors()) {
//			
//		}else {
//			
//		}
		
		userService.updateUser(bindingModel);
		
		return "redirect:/user/profile";
		
	}
	@PostMapping("guide")
	public String clickGuide(UpdateProfileBindingModel bindingModel, BindingResult result) {
		
//		if(result.hasErrors()) {
//			
//		}else {
//			
//		}
		return "redirect:/";
		
	}
	
	@RequestMapping("addToFavorites/{id}")
	public String addGuideToFavorites(@PathVariable Integer id, Principal principal, RedirectAttributes redirectAttributes) {
		
		if(userService.addToFavorites(id, principal.getName())) {
			
			redirectAttributes.addFlashAttribute("msg","success");
			
			
		}else {
			redirectAttributes.addFlashAttribute("msg","warning");
		}
		
		
		return "redirect:/pages/guide/" +id;
	}
	
	@RequestMapping("favorite/delete/{id}")
	public String deleteFavorites(@PathVariable Integer id, Principal principal, RedirectAttributes redirectAttributes) {
		
		userService.deleteFavorite(id, principal.getName());
		redirectAttributes.addFlashAttribute("msg","success");

		return "redirect:/user/profile";
	}
	
	@GetMapping("searchResult")
	public String searchResult(Model model) {
		
		model.addAttribute("view",ViewConstants.SearchResultView);
		
		return ViewConstants.BaseLayoutView;
	}
	
	@PostMapping("searchUser")
	public String search(@RequestParam(value = "searchword", required = false) String searchWord, RedirectAttributes redirectAttributes) {
		
		List<User>users = this.userService.searchUserByName(searchWord);
		
		redirectAttributes.addFlashAttribute("users", users);
		
		return "redirect:/user/searchResult";
		
	}
	
	
	
}
