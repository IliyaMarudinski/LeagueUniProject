package lolguide.areas.gamedata.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lolguide.areas.guides.entities.Guide;

@Entity
@Table(name = "runes")
public class Runes {
	private int id;
	private String name;
	private boolean active;
	private String img;
	private Set<Guide> guides = new HashSet<Guide>();
	
	public Runes() {
	}
	
	public Runes(String name) {
		this.name = name;
		this.active = true;
		this.img = "";
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "name", nullable = false, unique = true)
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name = "img", nullable = true)
	public String getImg() {
		return img;
	}
	
	public void setImg(String img) {
		this.img = img;
	}
	
	@ManyToMany(mappedBy = "runes")
	public Set<Guide> getGuides() {
		return guides;
	}

	public void setGuides(Set<Guide> guides) {
		this.guides = guides;
	}

    @Column(name = "active")
	public boolean isActive() {
		return active;
	}


	public void setActive(boolean active) {
		this.active = active;
	}
	

}
