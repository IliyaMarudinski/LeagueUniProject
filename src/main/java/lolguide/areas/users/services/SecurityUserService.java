package lolguide.areas.users.services;

import org.springframework.security.core.userdetails.UserDetailsService;

import lolguide.areas.users.models.binding.UserRegistrationBindingModel;

public interface SecurityUserService extends UserDetailsService {
	
	void register(UserRegistrationBindingModel bindingModel);
	
}
