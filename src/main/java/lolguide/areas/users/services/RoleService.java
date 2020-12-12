package lolguide.areas.users.services;

import lolguide.areas.users.entities.Role;

public interface RoleService {
	
	Role findByName(String name);
}
