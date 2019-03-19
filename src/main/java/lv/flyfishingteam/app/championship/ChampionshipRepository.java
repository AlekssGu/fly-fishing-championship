package lv.flyfishingteam.app.championship;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ChampionshipRepository extends JpaRepository<Championship, Long> {

	@Override
	List<Championship> findAll();
}
