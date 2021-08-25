package br.com.eventsspring.eventsapp.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.eventsspring.eventsapp.models.entities.Event;
import br.com.eventsspring.eventsapp.models.entities.Guest;
import br.com.eventsspring.eventsapp.models.repositories.GuestRepository;
import javassist.NotFoundException;

@Service
public class GuestService {

	@Autowired
	private GuestRepository guestRepository;
	
	@Autowired
	private EventService eventService;
	
	public Guest saveGuest(Long id, Guest guest) throws NotFoundException {
		Event event = eventService.findEventById(id);
		guest.setEvent(event);
		return guestRepository.save(guest);
	}
	
	public List<Guest> findAllGuestsByEvent(Event event){
		return guestRepository.findByEvent(event);
	}
	
	public Guest findGuestById (Long id) throws NotFoundException {
		return verifyIfExists(id);
	}
	
	public Guest updateGuest(Guest guest, Long id) throws NotFoundException {
		verifyIfExists(id);
		guest.setId(id);
		return guestRepository.save(guest);
	}
	
	public void deleteGuest(Long id) throws NotFoundException {
		verifyIfExists(id);
		guestRepository.deleteById(id);
	}
	
	private Guest verifyIfExists(Long id) throws NotFoundException {
		return guestRepository.findById(id)
					.orElseThrow(() -> new NotFoundException("Event not found. ID: " + id));
	}
}
