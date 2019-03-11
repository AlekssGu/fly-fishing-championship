package lv.flyfishingteam.app.team;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lv.flyfishingteam.app.participant.Participant;

@Entity
@Table(name = "team")
public class Team {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String teamName;

	private String representingCountry;

	@OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
	private Set<Participant> participants;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public Set<Participant> getParticipants() {
		return participants;
	}

	public String getRepresentingCountry() {
		return representingCountry;
	}

	public void setRepresentingCountry(String representingCountry) {
		this.representingCountry = representingCountry;
	}
}