package lv.flyfishingteam.app.team;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {

	@Override
	List<Team> findAll();
}
