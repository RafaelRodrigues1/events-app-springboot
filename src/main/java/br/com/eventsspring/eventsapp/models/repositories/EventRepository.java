package br.com.eventsspring.eventsapp.models.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.eventsspring.eventsapp.models.entities.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

}
