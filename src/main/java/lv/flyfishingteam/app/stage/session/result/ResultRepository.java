package lv.flyfishingteam.app.stage.session.result;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ResultRepository extends JpaRepository<Result, Long> {

	List<Result> findByStageSessionId(Long sessionId);
}
