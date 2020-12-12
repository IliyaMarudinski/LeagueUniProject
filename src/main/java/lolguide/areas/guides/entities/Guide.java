package lolguide.areas.guides.entities;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.attoparser.dom.Text;

import lolguide.areas.gamedata.entities.Hero;
import lolguide.areas.gamedata.entities.Items;
import lolguide.areas.gamedata.entities.Runes;
import lolguide.areas.users.entities.User;

@Entity
@Table(name = "guides")
public class Guide{	
	
	private int    id;
	private String name;
	private User   user;
	private Hero   heroId;
	private String description;
	private char   maxAbility;
	private char   startWithAbility;
	private Set<User> users = new HashSet<User>();
	private Set<Runes> runes = new HashSet<>();
	private List<Items> items = new ArrayList<>();
	
	public Guide() {
		
	}
	
	public Guide(Hero heroId, String description, char maxAbility, char startWithAbility) {
		this.heroId = heroId;
		this.description = description;
		this.maxAbility = maxAbility;
		this.startWithAbility = startWithAbility;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name = "name", nullable = false)
    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToOne()
	@JoinColumn(name = "hero_Id", referencedColumnName = "id")
	public Hero getHeroId() {
		return heroId;
	}
	public void setHeroId(Hero heroId) {
		this.heroId = heroId;
	}
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="guide_runes")
	public Set<Runes> getRunes() {
		return runes;
	}
	
	public void setRunes(Set<Runes> runes) {
		this.runes = runes;
	}
	
	@Transient
	public void addRune(Runes rune) {
		this.runes.add(rune);
	}	
	
	@Column(name = "description")
	@Lob
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	@Column(name="maxAbility")
	public char getMaxAbility() {
		return maxAbility;
	}
	public void setMaxAbility(char maxAbility) {
		this.maxAbility = maxAbility;
	}
	@Column(name="startWithAbility")
	public char getStartWithAbility() {
		return startWithAbility;
	}
	public void setStartWithAbility(char startWithAbility) {
		this.startWithAbility = startWithAbility;
	}
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="guide_items")
	public List<Items> getItems() {
		return items;
	}

	public void setItems(List<Items> items) {
		this.items = items;
	}
	
	@Transient
	public void addItem(Items item) {
		this.items.add(item);
	}
	
	@ManyToOne
	@JoinColumn(name="created_by")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
			
	@ManyToMany(mappedBy = "favoriteGuides")
    public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	@Transient
	public void clearItems() {
		this.items.clear();
	}
    @Transient
	public void clearRunes() {
		this.runes.clear();
	}
    
   
	

}
