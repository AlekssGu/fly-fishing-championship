package lv.flyfishingteam.app.auth.user;

import java.util.List;

import javax.validation.Valid;

public interface UserService {
	User save(@Valid User user);

	User findByUsername(String username);

	List<User> findAll();
}