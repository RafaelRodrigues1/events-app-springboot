package br.com.eventsspring.eventsapp.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.eventsspring.eventsapp.models.entities.Event;
import br.com.eventsspring.eventsapp.models.services.EventService;
import javassist.NotFoundException;

@Controller
@RequestMapping("/event")
public class EventController {

	@Autowired
	private EventService eventService;
	
	@GetMapping("/register")
	public String registerEvent() {
		return "event/registerEvent";
	}
	
	@PostMapping("/register")
	public void saveEvent(HttpServletRequest request, HttpServletResponse response) 
			throws IOException {
		eventService.saveEvent(request);
		response.sendRedirect("/event");
	}
	
	@GetMapping
	public ModelAndView findAllEvents() {
		ModelAndView view = new ModelAndView("index");
		List<Event> events = eventService.findAllEvents();
		view.addObject("events", events);
		return view;
	}
	
	@GetMapping("/details/{id}")
	public ModelAndView findById(@PathVariable Long id) throws NotFoundException {
		ModelAndView view = new ModelAndView("event/details");
		Event event = eventService.findEventById(id);
		view.addObject("event", event);
		return view;
	}
	
	@PostMapping("/update/{id}")
	public void updateEvent(@PathVariable Long id,HttpServletRequest request, 
			HttpServletResponse response) throws NotFoundException, IOException {
		eventService.updateEvent(request, id);
		response.sendRedirect("/event");
	}
	
	@GetMapping("/delete/{id}")
	public void deleteEvent(@PathVariable Long id, HttpServletResponse response) 
			throws NotFoundException, IOException {
		eventService.deleteEvent(id);
		response.sendRedirect("/event");
	}
}











