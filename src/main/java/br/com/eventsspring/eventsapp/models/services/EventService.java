package br.com.eventsspring.eventsapp.models.services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.eventsspring.eventsapp.models.entities.Event;
import br.com.eventsspring.eventsapp.models.repositories.EventRepository;
import javassist.NotFoundException;

@Service
public class EventService {

	@Autowired
	private EventRepository eventRepository;
	
	public Event saveEvent(HttpServletRequest request) {
		Event event = instanceEvent(request);
		return eventRepository.save(event);
	}
	
	public List<Event> findAllEvents() {
		return eventRepository.findAll();
	}
	
	public Event findEventById(Long id) throws NotFoundException {	
		return verifyIfExists(id);
	}
	
	public Event updateEvent(HttpServletRequest request, Long id) throws NotFoundException {
		verifyIfExists(id);
		Event event = instanceEvent(request);
		event.setId(id);
		return eventRepository.save(event);
	}
	
	public void deleteEvent(Long id) throws NotFoundException {
		verifyIfExists(id);
		eventRepository.deleteById(id);
	}
	
	private Event verifyIfExists(Long id) throws NotFoundException {
		return eventRepository.findById(id)
					.orElseThrow(() -> new NotFoundException("Event not found. ID: " + id));
	}
	
	private Event instanceEvent(HttpServletRequest request) {
		String name = request.getParameter("name");
		String local = request.getParameter("local");
		LocalDateTime dateTime = LocalDateTime
					.parse(request.getParameter("dateTime").replace("T", " "), 
					DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
		Event event = new Event(name, local, dateTime);
		return event;
	}
}




