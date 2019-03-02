package lv.flyfishingteam.app.auth.user;

import javax.validation.Valid;

public interface UserService {
	User save(@Valid User user);

	User findByUsername(String username);
}