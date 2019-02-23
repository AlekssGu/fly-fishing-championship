package lv.flyfishingteam.app.auth.user;

public interface UserService {
	void save(User user);

	User findByUsername(String username);
}