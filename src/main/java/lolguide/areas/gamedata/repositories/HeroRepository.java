package lolguide.areas.gamedata.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import lolguide.areas.gamedata.entities.Hero;

@Repository
public interface HeroRepository extends JpaRepository<Hero, Integer> {
	
	Hero findByName(String name);
	
	@Query(value = "select NAME from HEROES", nativeQuery = true)
	public List<String> getAllHeroesNames();
	
}
