package lv.flyfishingteam.app.participant;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipantRepository extends JpaRepository<Participant, Long> {

	@Override
	List<Participant> findAll();
}
