package lv.flyfishingteam.app.stage.session.result;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lv.flyfishingteam.app.stage.session.StageSession;
import lv.flyfishingteam.app.stage.session.participant.SessionParticipant;

@Entity
@Table(name = "championship_session_results")
public class Result {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private StageSession stageSession;

	@ManyToOne
	private SessionParticipant sessionParticipant;

	private String species;

	private Integer size;

	private Date timeCaught;

	public StageSession getStageSession() {
		return stageSession;
	}

	public SessionParticipant getSessionParticipant() {
		return sessionParticipant;
	}

	public void setSessionParticipant(SessionParticipant sessionParticipant) {
		this.sessionParticipant = sessionParticipant;
	}

	public void setStageSession(StageSession stageSession) {
		this.stageSession = stageSession;
	}

	public String getSpecies() {
		return species;
	}

	public void setSpecies(String species) {
		this.species = species;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public Date getTimeCaught() {
		return timeCaught;
	}

	public void setTimeCaught(Date timeCaught) {
		this.timeCaught = timeCaught;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
