package lolguide.areas.gamedata.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import org.springframework.data.relational.core.sql.TrueCondition;

import lolguide.areas.guides.entities.Guide;

@Entity
@Table(name = "items")
public class Items {
	
	private int id;
	private String name;
	private String itemNum;
	private boolean active;
	private Set<Guide> guides = new HashSet<Guide>();
	
	public Items() {
	}
	
	public Items(String name, String itemNum) {
		this.name = name;
		this.itemNum = itemNum;
		this.active = true;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name = "name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name ="itemNum")
	public String getItemNum() {
		return itemNum;
	}

	public void setItemNum(String itemNum) {
		this.itemNum = itemNum;
	}

	@Column(name = "active", nullable = false)
	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@ManyToMany(mappedBy = "items")
	public Set<Guide> getGuides() {
		return guides;
	}

	public void setGuides(Set<Guide> guides) {
		this.guides = guides;
	}

}
