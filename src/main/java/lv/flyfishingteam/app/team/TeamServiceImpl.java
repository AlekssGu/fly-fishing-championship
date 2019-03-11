package lv.flyfishingteam.app.team;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

@Service
public class TeamServiceImpl implements TeamService {

	private final TeamRepository teamRepository;

	TeamServiceImpl(TeamRepository teamRepository) {
		this.teamRepository = teamRepository;
	}

	@Override
	public Team save(@Valid Team team) {
		return teamRepository.save(team);
	}

	@Override
	public List<Team> findAll() {
		return teamRepository.findAll();
	}

	@Override
	public Optional<Team> findById(Long id) {
		return teamRepository.findById(id);
	}

	@Override public void delete(Long teamId) {
		teamRepository.deleteById(teamId);
	}
}
