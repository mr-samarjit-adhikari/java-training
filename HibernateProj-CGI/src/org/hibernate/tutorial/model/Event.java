package org.hibernate.tutorial.model;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Event {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)	
	private long id;
	private String name;
	private Date startDate;
	private int duration;
	
	@OneToMany(cascade=CascadeType.PERSIST)
	@JoinTable(name="EVENT_SPEAKERS",
		joinColumns=@JoinColumn(name="EVENTID"),
		inverseJoinColumns=@JoinColumn(name="SPEAKERID")
	)
	private Set<Speaker> speakers;
	
	@OneToMany(mappedBy="event")
	private Set<Attendee> attendees;
	
	//@ManyToMany(mappedBy="eventList",cascade=CascadeType.PERSIST)
	@ManyToMany(cascade=CascadeType.PERSIST)
	private List<Location> localtionList;
	
	public List<Location> getLocaltionList() {
		return localtionList;
	}
	public void setLocaltionList(List<Location> localtionList) {
		this.localtionList = localtionList;
	}
	
	public Set<Attendee> getAttendees() {
		return attendees;
	}
	public void setAttendees(Set<Attendee> attendees) {
		this.attendees = attendees;
	}
	
	public Set<Speaker> getSpeakers() {
		return speakers;
	}
	public void setSpeakers(Set<Speaker> speakers) {
		this.speakers = speakers;
	}	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}

}
