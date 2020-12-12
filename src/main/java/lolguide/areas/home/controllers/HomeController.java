package lolguide.areas.home.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lolguide.constants.ViewConstants;

@Controller
public class HomeController {
	
	
	@Autowired
	public HomeController() {
	
	}
	
	
	@GetMapping("/")
	public String index(Model model) { 
		
		model.addAttribute("view", ViewConstants.HomeIndexView);
		
		return ViewConstants.BaseLayoutView;
		
	}
	
}
