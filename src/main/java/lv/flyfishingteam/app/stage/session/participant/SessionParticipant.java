package lv.flyfishingteam.app.stage.session.participant;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lv.flyfishingteam.app.participant.Participant;
import lv.flyfishingteam.app.stage.session.StageSession;
import lv.flyfishingteam.app.stage.session.result.Result;

@Entity
@Table(name = "championship_session_participants")
public class SessionParticipant {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private StageSession stageSession;

	@ManyToOne
	private Participant participant;

	@OneToMany
	private List<Result> participantResults;

	private String sector;

	private String zone;

	public StageSession getStageSession() {
		return stageSession;
	}

	public List<Result> getParticipantResults() {
		return participantResults;
	}

	public void setParticipantResults(List<Result> participantResults) {
		this.participantResults = participantResults;
	}

	public void setStageSession(StageSession stageSession) {
		this.stageSession = stageSession;
	}

	public Participant getParticipant() {
		return participant;
	}

	public void setParticipant(Participant participant) {
		this.participant = participant;
	}

	public String getSector() {
		return sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

	public String getZone() {
		return zone;
	}

	public void setZone(String zone) {
		this.zone = zone;
	}
}