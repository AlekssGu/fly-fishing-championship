package lv.flyfishingteam.app.auth.user;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import lv.flyfishingteam.app.auth.role.RoleRepository;
import lv.flyfishingteam.app.auth.role.RoleType;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public User save(User user) {
		user.setUsername(user.getUsername().toLowerCase());
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setRoles(new HashSet<>(Arrays.asList(roleRepository.findByName(RoleType.ROLE_USER.toString()))));

		User createdUser = userRepository.save(user);

		return createdUser;
	}

	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override public List<User> findAll() {
		return userRepository.findAll();
	}
}