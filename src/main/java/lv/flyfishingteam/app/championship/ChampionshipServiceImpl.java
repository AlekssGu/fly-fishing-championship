package lv.flyfishingteam.app.championship;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

@Service
public class ChampionshipServiceImpl implements ChampionshipService {

	private final ChampionshipRepository championshipRepository;

	ChampionshipServiceImpl(ChampionshipRepository championshipRepository) {
		this.championshipRepository = championshipRepository;
	}

	@Override
	public Championship save(@Valid Championship championship) {
		return championshipRepository.save(championship);
	}

	@Override
	public List<Championship> findAll() {
		return championshipRepository.findAll();
	}

	@Override
	public Optional<Championship> findById(Long id) {
		return championshipRepository.findById(id);
	}

	@Override
	public void delete(Long championshipId) {
		championshipRepository.deleteById(championshipId);
	}
}
