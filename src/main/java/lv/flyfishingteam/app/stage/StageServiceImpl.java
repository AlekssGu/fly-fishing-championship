package lv.flyfishingteam.app.stage;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

@Service
public class StageServiceImpl implements StageService {

	private final StageRepository stageRepository;

	StageServiceImpl(StageRepository stageRepository) {
		this.stageRepository = stageRepository;
	}

	@Override
	public Stage save(@Valid Stage stage) {
		return stageRepository.save(stage);
	}

	@Override
	public List<Stage> findAll() {
		return stageRepository.findAll();
	}

	@Override
	public Optional<Stage> findById(Long id) {
		return stageRepository.findById(id);
	}

	@Override public void delete(Long teamId) {
		stageRepository.deleteById(teamId);
	}
}
