package lolguide.areas.guides.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lolguide.areas.gamedata.repositories.ItemsRepository;
import lolguide.areas.gamedata.services.GameDataService;
import lolguide.areas.guides.entities.Guide;
import lolguide.areas.guides.model.binding.UpdateGuideBindingModel;
import lolguide.areas.guides.services.GuideService;
import lolguide.constants.ViewConstants;

@Controller
@RequestMapping("pages")
@PreAuthorize("isAuthenticated()")
public class GuideController {
	
	
	private final GuideService gService;
	private final GameDataService gdService;
	
	@Autowired
	public GuideController(GuideService gService, GameDataService gdService) {
		this.gService = gService;
		this.gdService = gdService;
	}

	@GetMapping("guide/{id}")
	public String guide(Model model, @PathVariable Integer id, Principal principal) {
		
		Guide guideSel = gService.getGuide(id);
		model.addAttribute("guide",guideSel);
		model.addAttribute("isEditable", principal.getName().equals(guideSel.getUser().getUsername()));
		model.addAttribute("hero", guideSel.getHeroId());
		model.addAttribute("startitem", guideSel.getItems().get(0));
		model.addAttribute("item1", guideSel.getItems().get(1));
		model.addAttribute("item2", guideSel.getItems().get(2));
		model.addAttribute("item3", guideSel.getItems().get(3));
		model.addAttribute("item4", guideSel.getItems().get(4));
		model.addAttribute("item5", guideSel.getItems().get(5));
		model.addAttribute("item6", guideSel.getItems().get(6));
		model.addAttribute("view", ViewConstants.GuideView);
		return ViewConstants.BaseLayoutView;
	}
	

	@GetMapping("editguide/{id}")
	public String editGuide(Model model,  Principal principal, @PathVariable Integer id) {
		Guide guideSel = gService.getGuide(id);
		if(!principal.getName().equals(guideSel.getUser().getUsername())) {
			return "redirect:/";
		}
		model.addAttribute("guideId", id);
		model.addAttribute("type", "edit");
		model.addAttribute("runes", gdService.getAllRunes());
		model.addAttribute("items", gdService.getAllItems());
		model.addAttribute("heroes", gdService.getAllHeroes());
		model.addAttribute("view", ViewConstants.EditGuideView);
		return ViewConstants.BaseLayoutView;
	}
	
	@PostMapping("editguide/{id}")
	public String editGuideDone(UpdateGuideBindingModel bindingModel, BindingResult result, @PathVariable Integer id){
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		bindingModel.setUserId(auth.getName());
		bindingModel.setId(id);
        gService.updateGuide(bindingModel);
		
		return "redirect:/pages/guide/" + id;
		
	}
	
	@RequestMapping("deleteguide/{id}")
	public String deleteGuideDone(@PathVariable Integer id){
					
        gService.deleteGuide(id);
		
		return "redirect:/user/profile";
		
	}
	
	
	@GetMapping("newguide")
	public String newGuide(Model model,  Principal principal) {
		
		model.addAttribute("runes", gdService.getAllRunes());
		model.addAttribute("items", gdService.getAllItems());
		model.addAttribute("heroes", gdService.getAllHeroes());
		model.addAttribute("view", ViewConstants.NewGuideView);
		return ViewConstants.BaseLayoutView;
	}
	
	@PostMapping("newguide")
	public String newGuideDone(UpdateGuideBindingModel bindingModel, BindingResult result){
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		bindingModel.setUserId(auth.getName());
        gService.newGuide(bindingModel);
		
		return "redirect:/";
		
	}
	

}
