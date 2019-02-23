package lv.flyfishingteam.app.security;

public interface SecurityService {

	String findLoggedInUsername();

	void autoLogin(String username, String password);

}