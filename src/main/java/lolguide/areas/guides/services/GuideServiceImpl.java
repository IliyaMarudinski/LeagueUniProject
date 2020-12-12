package lolguide.areas.guides.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lolguide.areas.gamedata.entities.Hero;
import lolguide.areas.gamedata.entities.Items;
import lolguide.areas.gamedata.repositories.HeroRepository;
import lolguide.areas.gamedata.repositories.ItemsRepository;
import lolguide.areas.gamedata.repositories.RunesRepository;
import lolguide.areas.guides.entities.Guide;
import lolguide.areas.guides.model.binding.UpdateGuideBindingModel;
import lolguide.areas.guides.repositories.GuideRepositories;
import lolguide.areas.users.entities.User;
import lolguide.areas.users.repositories.UserRepository;

@Service
public class GuideServiceImpl implements GuideService {

	private final GuideRepositories guideRepo;
	private final ItemsRepository   itemsRepo;
	private final RunesRepository   runeRepo;
	private final UserRepository    userRepo;
	
	@Autowired
	public GuideServiceImpl(GuideRepositories guideRepo, ItemsRepository itemsRepo, RunesRepository runeRepo, UserRepository userRepo) {
		this.guideRepo = guideRepo;
		this.itemsRepo = itemsRepo;
		this.runeRepo  = runeRepo;
		this.userRepo  = userRepo;
	}


	@Override
	public void newGuide(UpdateGuideBindingModel guide) {
		
		Guide newGuide = new Guide();
		User user = userRepo.findByUsername(guide.getUserId());
		
		newGuide.setName(guide.getName());
		newGuide.setDescription(guide.getDescription());
		newGuide.setMaxAbility(guide.getMaxAbility());
		newGuide.setStartWithAbility(guide.getStartWithAbility());
		newGuide.setUser(user);
		newGuide.setHeroId(guide.getHeroId());
	//	newGuide.getUserId()
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!" +guide.getItems().size());
		for(int item : guide.getItems()) {
			newGuide.addItem(itemsRepo.findById(item));
		}
		
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!" +guide.getRune());
		newGuide.addRune(runeRepo.findById(guide.getRune()));

		user.addGuideToUser(newGuide);
		guideRepo.save(newGuide);
		
	}


	@Override
	public Guide getGuide(int id) {
		Guide guide = guideRepo.findById(id);
		return guide;
	}


	@Override
	public void updateGuide(UpdateGuideBindingModel guide) {
		
		Guide newGuide = guideRepo.findById(guide.getId());
		User user = userRepo.findByUsername(guide.getUserId());
		
		newGuide.setName(guide.getName());
		newGuide.setDescription(guide.getDescription());
		newGuide.setMaxAbility(guide.getMaxAbility());
		newGuide.setStartWithAbility(guide.getStartWithAbility());
		newGuide.setUser(user);
		newGuide.setHeroId(guide.getHeroId());
	//	newGuide.getUserId()
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!" +guide.getItems().size());
        newGuide.clearItems();
        newGuide.clearRunes();
		for(int item : guide.getItems()) {
			newGuide.addItem(itemsRepo.findById(item));
		}
		
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!" +guide.getRune());
		newGuide.addRune(runeRepo.findById(guide.getRune()));

		user.addGuideToUser(newGuide);
		guideRepo.save(newGuide);
		
	}


	@Override
	public void deleteGuide(int id) {		
		guideRepo.deleteById(id);
		
	}


}
