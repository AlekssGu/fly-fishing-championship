package lv.flyfishingteam.app.security;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lv.flyfishingteam.app.auth.role.Role;
import lv.flyfishingteam.app.auth.role.RoleRepository;
import lv.flyfishingteam.app.auth.role.RoleType;
import lv.flyfishingteam.app.auth.user.User;
import lv.flyfishingteam.app.auth.user.UserRepository;
import lv.flyfishingteam.app.auth.user.UserService;

@Component
public class InitialDataLoader implements ApplicationListener<ContextRefreshedEvent> {

	private static final String TEST_USERNAME = "test";
	private static final String TEST_PASSWORD = "test";
	boolean alreadySetup = false;

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	@Autowired
	private UserService userService;

	@Transactional
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {

		if (alreadySetup) {
			return;
		}

		createRoleIfNotFound(RoleType.ROLE_ADMIN.toString());
		createRoleIfNotFound(RoleType.ROLE_USER.toString());
		createTestUserIfNotFound();

		alreadySetup = true;
	}

	@Transactional
	Role createRoleIfNotFound(String name) {

		Role role = roleRepository.findByName(name);
		if (role == null) {
			role = new Role();
			role.setName(name);
			roleRepository.save(role);
		}
		return role;
	}

	private void createTestUserIfNotFound() {
		if (userService.findByUsername(TEST_USERNAME) == null) {
			Role adminRole = roleRepository.findByName(RoleType.ROLE_ADMIN.toString());
			User user = new User();
			user.setUsername(TEST_USERNAME);
			user.setPassword(passwordEncoder.encode(TEST_PASSWORD));
			user.setRoles(new HashSet(Arrays.asList(adminRole)));
			userRepository.save(user);
		}
	}
}