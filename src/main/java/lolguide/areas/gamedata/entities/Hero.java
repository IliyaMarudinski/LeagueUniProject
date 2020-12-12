package lolguide.areas.gamedata.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "heroes")
public class Hero {
	private int id;
	private String name;
	private String img;
	private String description;
	private String shortDescription;
	private Set<HeroRole> heroRoles = new HashSet<HeroRole>();
	
	public Hero() {
	}
	
	public Hero(String name, String imgPath, String description, String shortDescription) {
		this.name = name;
		this.img = imgPath;
		this.description = description;
		this.shortDescription = shortDescription;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "name", unique = true, nullable = false)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "hero_role")
	public Set<HeroRole> getHeroRoles() {
		return heroRoles;
	}
	public void setHeroRoles(Set<HeroRole> heroRoles) {
		this.heroRoles = heroRoles;
	}
	@Transient
	public void addHeroRole(HeroRole role) {
		this.heroRoles.add(role);
	}
	
	@Column(name = "img")
	public String getImg() {
		return img;
	}
	
	public void setImg(String img) {
		this.img = img;
	}

	@Column(name = "description")
	@Lob
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Column(name = "shortdescription")
	public String getShortDescription() {
		return shortDescription;
	}
	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}
   
}
