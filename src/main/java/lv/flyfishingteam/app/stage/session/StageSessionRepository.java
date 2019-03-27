package lv.flyfishingteam.app.stage.session;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StageSessionRepository extends JpaRepository<StageSession, Long> {

	List<StageSession> findByStageId(Long stageId);
}
