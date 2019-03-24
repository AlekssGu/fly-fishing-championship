package lv.flyfishingteam.app.stage.session.result;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lv.flyfishingteam.app.stage.session.participant.SessionParticipant;

@Entity
@Table(name = "championship_session_results")
public class Result {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private SessionParticipant sessionParticipant;

	private String species;

	private Integer size;

	private Date timeCaught;
}
