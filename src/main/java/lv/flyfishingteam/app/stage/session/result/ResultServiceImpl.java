package lv.flyfishingteam.app.stage.session.result;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

@Service
public class ResultServiceImpl implements ResultService {

	private final ResultRepository resultRepository;

	ResultServiceImpl(ResultRepository resultRepository) {
		this.resultRepository = resultRepository;
	}

	@Override
	public Result save(@Valid Result result) {
		return resultRepository.save(result);
	}

	@Override
	public List<Result> findAll() {
		return resultRepository.findAll();
	}

	@Override
	public Optional<Result> findById(Long id) {
		return resultRepository.findById(id);
	}

	@Override
	public void delete(Long id) {
		resultRepository.deleteById(id);
	}

	@Override
	public List<Result> findBySessionId(Long sessionId) {
		return resultRepository.findByStageSessionId(sessionId);
	}
}
