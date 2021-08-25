package br.com.eventsspring.eventsapp.models.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.eventsspring.eventsapp.models.entities.Event;
import br.com.eventsspring.eventsapp.models.entities.Guest;

@Repository
public interface GuestRepository extends JpaRepository<Guest, Long> {

	List<Guest> findByEvent(Event event);
}
