package lolguide.areas.users.entities;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lolguide.areas.gamedata.entities.Hero;
import lolguide.areas.guides.entities.Guide;


@Entity
@Table(name = "users")
public class User implements UserDetails {
	
	private int id;	
	private String name;
	private String username;
	private String email;		
	private String avatarPath;
	private String password;	
	private Set<Role> roles = new HashSet<>();
	private Set<User> following = new HashSet<>();
	private Set<User> followers = new HashSet<>();
	private Set<Hero> favoriteHeroes = new HashSet<>();
	private Set<Guide> favoriteGuides = new HashSet<>();
	private Set<Guide> myGuides = new HashSet<>();
	
	public User() {
	
	}
	
	public User(String name, String username, String email, String avatarPath, String password) {
		
		this.name = name;
		this.username = username;
		this.password = password;
		this.email = email;
		this.avatarPath = "";	
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
	
	@Column(name = "username", unique = true, nullable = false)
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Column(name = "email", unique = true, nullable = false)	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Column(name = "avatarPath")
	public String getAvatarPath() {
		return avatarPath;
	}

	public void setAvatarPath(String avatarPath) {
		this.avatarPath = avatarPath;
	}

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "users_roles")
	public Set<Role> getRoles() {
		return roles;
	}
	
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	@Transient
	public void addRole(Role role) {
		this.roles.add(role);
	}
	
	@Transient
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return this.roles;
	}

	
	@Override
	public String getPassword() {
		
		return this.password;
	}

	@Transient
	@Override
	public boolean isAccountNonExpired() {
		
		return true;
	}

	@Transient
	@Override
	public boolean isAccountNonLocked() {
		
		return true;
	}

	@Transient
	@Override
	public boolean isCredentialsNonExpired() {
		
		return true;
	}

	@Transient
	@Override
	public boolean isEnabled() {
		return true;
	}
    
//	@ManyToMany(fetch = FetchType.EAGER)
//	@JoinTable(name="user_followingUsers")
//	public Set<User> getFollowingUsers() {
//		return followingUsers;
//	}
//
//	public void setFollowingUsers(Set<User> followingUsers) {
//		this.followingUsers = followingUsers;
//	}
//	
//	public void addFollowingUser(User user) {
//		this.followingUsers.add(user);
//	}
	
//	@ManyToMany(cascade = CascadeType.ALL)
//	@JoinTable(name     = "user_relationship")
//	@JoinColumn(name    = "FOLLOWED_ID")
//    @JoinColumn(name    = "FOLLOWER_ID")
	
    @ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(     name="user_relationship", 
	         joinColumns=@JoinColumn(name="followed_id"),
	  inverseJoinColumns=@JoinColumn(name="follower_id"))  	
	public Set<User> getFollowers() {
		return followers;
	}

	public void setFollowers(Set<User> followers) {
	    this.followers = followers;
	}

    public void addFollower(User follower) {
        followers.add(follower);
        follower.following.add(this);
    }
 
    @ManyToMany(mappedBy = "followers")
    public Set<User> getFollowing() {
        return following;
    }

    public void setFollowing(Set<User> following) {
        this.following = following;
    }

    public void addFollowing(User followed) {
        followed.addFollower(this);
    }
    
    @OneToMany(cascade = CascadeType.ALL,
         orphanRemoval = true)
	public Set<Hero> getFavoriteHeroes() {
		return favoriteHeroes;
	}

	public void setFavoriteHeroes(Set<Hero> favoriteHeroes) {
		this.favoriteHeroes = favoriteHeroes;
	}
	
	public void addFavoriteHeroe(Hero hero) {
		this.favoriteHeroes.add(hero);
	}
	
	public void removeFavoriteHeroe(Hero hero) {
		this.favoriteHeroes.remove(hero);
	}

	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "users_favorite_guides")
	public Set<Guide> getFavoriteGuides() {
		return favoriteGuides;
	}

	public void setFavoriteGuides(Set<Guide> favoriteGuides) {
		this.favoriteGuides = favoriteGuides;
	}
	
	public void addFavoriteGuide(Guide guide) {
		this.favoriteGuides.add(guide);
	}
	
	public void removeFavoriteGuide(Guide guide) {
		this.favoriteGuides.remove(guide);
	}

	
	@OneToMany(cascade = CascadeType.ALL,
         orphanRemoval = true,
              mappedBy = "user")
	public Set<Guide> getMyGuides() {
		return myGuides;
	}

	public void setMyGuides(Set<Guide> myGuides) {
		this.myGuides = myGuides;
	}
	
	@Transient
	public void addGuideToUser(Guide guide) {
		this.myGuides.add(guide);
	}
	
	
}
