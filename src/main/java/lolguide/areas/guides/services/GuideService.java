package lolguide.areas.guides.services;

import java.util.List;

import lolguide.areas.gamedata.entities.Hero;
import lolguide.areas.guides.entities.Guide;
import lolguide.areas.guides.model.binding.UpdateGuideBindingModel;

public interface GuideService {
	
	void newGuide(UpdateGuideBindingModel guide);
	void updateGuide(UpdateGuideBindingModel guide);
	void deleteGuide(int id);
	Guide getGuide(int id);

}
