package lv.flyfishingteam.app.stage.session.result;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

public interface ResultService {

	Result save(@Valid Result result);

	List<Result> findAll();

	Optional<Result> findById(Long id);

	void delete(Long id);

	List<Result> findBySessionId(Long sessionId);
}
