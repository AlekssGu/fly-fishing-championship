package lv.flyfishingteam.app.stage;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

public interface StageService {
	Stage save(@Valid Stage stage);

	List<Stage> findAll();

	Optional<Stage> findById(Long id);

	void delete(Long stageId);
}
