package lv.flyfishingteam.app.stage.session;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

public interface StageSessionService {

	StageSession save(@Valid StageSession stageSession);

	List<StageSession> findAll();

	Optional<StageSession> findById(Long id);

	void delete(Long stageSessionId);

	List<StageSession> findByStageId(Long stageId);
}
