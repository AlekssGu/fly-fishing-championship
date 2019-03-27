package lv.flyfishingteam.app.stage;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StageRepository extends JpaRepository<Stage, Long> {

	List<Stage> findAllByChampionshipId(Long championshipId);

}
