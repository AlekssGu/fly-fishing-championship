package lv.flyfishingteam.app.championship;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

public interface ChampionshipService {

	Championship save(@Valid Championship championship);

	List<Championship> findAll();

	Optional<Championship> findById(Long id);

	void delete(Long championshipId);
}
