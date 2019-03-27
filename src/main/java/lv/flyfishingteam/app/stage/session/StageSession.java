package lv.flyfishingteam.app.stage.session;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lv.flyfishingteam.app.stage.Stage;
import lv.flyfishingteam.app.stage.session.participant.SessionParticipant;

@Entity
@Table(name = "championship_stage_session")
public class StageSession {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private int sequence;

	@ManyToOne
	private Stage stage;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "stageSession")
	private List<SessionParticipant> sessionParticipants;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public List<SessionParticipant> getSessionParticipants() {
		return sessionParticipants;
	}

	public void setSessionParticipants(
			List<SessionParticipant> sessionParticipants) {
		this.sessionParticipants = sessionParticipants;
	}
}
