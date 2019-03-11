package lv.flyfishingteam.app.team;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

public interface TeamService {

	Team save(@Valid Team team);

	List<Team> findAll();

	Optional<Team> findById(Long id);

	void delete(Long teamId);
}
