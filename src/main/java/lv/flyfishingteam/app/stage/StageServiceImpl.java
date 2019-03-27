package lv.flyfishingteam.app.stage;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import lv.flyfishingteam.app.stage.session.StageSessionService;

@Service
public class StageServiceImpl implements StageService {

	private final StageRepository stageRepository;
	private final StageSessionService stageSessionService;

	StageServiceImpl(StageRepository stageRepository, StageSessionService stageSessionService) {
		this.stageRepository = stageRepository;
		this.stageSessionService = stageSessionService;
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

	@Override
	public void delete(Long stageId) {
		stageSessionService.findByStageId(stageId).forEach(stageSession -> stageSessionService.delete(stageSession.getId()));
		stageRepository.deleteById(stageId);
	}

	@Override
	public List<Stage> findByChampionshipId(Long championshipId) {
		return stageRepository.findAllByChampionshipId(championshipId);
	}
}
