package lolguide.areas.guides.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lolguide.areas.guides.entities.Guide;
import lolguide.areas.users.entities.User;

@Repository
public interface GuideRepositories extends JpaRepository<Guide, Integer> {
	
	Guide findByName(String name);
	Guide findByUserId(User userId);
	Guide findById(int id);

	
}
