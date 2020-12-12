package lolguide.areas.users.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lolguide.areas.users.entities.Role;
import lolguide.areas.users.entities.User;
import lolguide.areas.users.models.binding.UserRegistrationBindingModel;
import lolguide.areas.users.repositories.UserRepository;

@Service
@Transactional
public class SecurityUserServiceImpl implements SecurityUserService {
	
	private final UserRepository userRepository;
	private final RoleServiceImpl roleService;
	private final ModelMapper modelMapper;
	
	@Autowired
	public SecurityUserServiceImpl(UserRepository userRepository, RoleServiceImpl roleService, ModelMapper modelMapper) {
		
		this.userRepository = userRepository;
		this.roleService = roleService;
		this.modelMapper = modelMapper;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {		
		
		User user = this.userRepository.findByUsername(username);
		
		if(user == null) {
			
			throw new UsernameNotFoundException("Invalid user");
		}
		
		return user;
	}

	@Override
	public void register(UserRegistrationBindingModel bindingModel) {
		
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		
		User user = new User(); 
		
		this.modelMapper.map(bindingModel, user);
		user.setPassword(bCryptPasswordEncoder.encode(bindingModel.getPassword()));
		
		Role role = this.roleService.getUserRole();
		
		user.addRole(role);
		
		this.userRepository.save(user);
	}

}
