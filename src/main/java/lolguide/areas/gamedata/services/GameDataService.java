package lolguide.areas.gamedata.services;

import java.util.List;

import lolguide.areas.gamedata.entities.Hero;
import lolguide.areas.gamedata.entities.Items;
import lolguide.areas.gamedata.entities.Runes;
import lolguide.areas.gamedata.model.binding.HeroApiModelBinding;
import lolguide.areas.gamedata.model.binding.ItemsApiModelBinding;
import lolguide.areas.gamedata.model.binding.RuneAddModelBinding;

public interface GameDataService {
	
	void importHeroes (HeroApiModelBinding data);
	void importItems  (ItemsApiModelBinding data);
	void addRune(RuneAddModelBinding model);
	void deleteRune(int id);
	Hero findHeroById(int id);
	List<Runes> getAllRunes();
	List<Hero>  getAllHeroes();
	List<Items> getAllItems();
	
}
