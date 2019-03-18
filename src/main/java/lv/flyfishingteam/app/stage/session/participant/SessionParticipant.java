package lv.flyfishingteam.app.stage.session.participant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lv.flyfishingteam.app.participant.Participant;
import lv.flyfishingteam.app.stage.session.StageSession;

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

	private String sector;

	private String zone;

}