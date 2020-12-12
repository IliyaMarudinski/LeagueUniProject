package lolguide.areas.guides.model.binding;

import java.util.List;

import lolguide.areas.gamedata.entities.Hero;


public class UpdateGuideBindingModel {

	private int    id;
	private String name;
	private String description;
	private char   maxAbility;
	private char   startWithAbility;
	private String   userId;
	private Hero   heroId;
	private List<Integer> items;
	private int rune;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public char getMaxAbility() {
		return maxAbility;
	}
	public void setMaxAbility(char maxAbility) {
		this.maxAbility = maxAbility;
	}
	public char getStartWithAbility() {
		return startWithAbility;
	}
	public void setStartWithAbility(char startWithAbility) {
		this.startWithAbility = startWithAbility;
	}

	public List<Integer> getItems() {
		return items;
	}
	public void setItems(List<Integer> items) {
		this.items = items;
	}
	public int getRune() {
		return rune;
	}
	public void setRune(int rune) {
		this.rune = rune;
	}


	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Hero getHeroId() {
		return heroId;
	}

	public void setHeroId(Hero heroId) {
		this.heroId = heroId;
	}

	
	
}
