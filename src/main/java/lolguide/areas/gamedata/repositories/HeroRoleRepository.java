package lolguide.areas.gamedata.repositories;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lolguide.areas.gamedata.entities.HeroRole;

@Repository
public interface HeroRoleRepository extends JpaRepository<HeroRole, Integer> {
	
	HeroRole findByName(String name);
//	List<String> findNames();
	
}
