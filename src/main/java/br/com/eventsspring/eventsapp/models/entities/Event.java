package br.com.eventsspring.eventsapp.models.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Event implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String local;
	
	@Column(nullable = false)
	private LocalDateTime dateTime;
	
	@OneToMany(mappedBy = "event",cascade = CascadeType.ALL)
	private List<Guest> guests = new ArrayList<>();
	
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
	private static DateTimeFormatter detFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
	
	public Event() {}

	public Event(String name, String local, LocalDateTime dateTime) {
		this.name = name;
		this.local = local;
		this.dateTime = dateTime;
	}
	
	public Event(Long id) {
		this.id = id;
	}

	public String getDate() {
		return dateTime.format(formatter).split(" ")[0];
	}
	
	public String getTime() {
		return dateTime.format(formatter).split(" ")[1];
	}
	
	public String getDateTimeFormat() {
		return dateTime.format(detFormatter).replace(" ", "T");
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	public List<Guest> getGuests() {
		return guests;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Event other = (Event) obj;
		return Objects.equals(id, other.id);
	}
}
