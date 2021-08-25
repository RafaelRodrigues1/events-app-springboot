package br.com.eventsspring.eventsapp.configuration;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.eventsspring.eventsapp.models.entities.Event;
import br.com.eventsspring.eventsapp.models.entities.Guest;
import br.com.eventsspring.eventsapp.models.repositories.EventRepository;

@Configuration
@Profile("test")
public class TestConfiguration implements CommandLineRunner {

	@Autowired
	private EventRepository eventRepository;
	
	@Override
	public void run(String... args) throws Exception {
		Event event1 = new Event("Palestra de Java 8", "Auditório 3", 
				LocalDateTime.of(2021, Month.OCTOBER, 27, 14, 0));
		Event event2 = new Event("Aula de API Rest", "Auditório 1", 
				LocalDateTime.of(2021, Month.OCTOBER, 25, 16, 30));
		Event event3 = new Event("Palestra de Spring Boot", "Auditório 3", 
				LocalDateTime.of(2021, Month.OCTOBER, 27, 16, 0));
		Event event4 = new Event("Palestra de JPA/Hibernate", "Auditório 2", 
				LocalDateTime.of(2021, Month.OCTOBER, 27, 19, 30));
		
		Guest guest1 = new Guest("Rafael Rodrigues", "8.549.878", "855998866", event2);
		Guest guest2 = new Guest("Janaina Santos", "1.235.253", "85234516", event4);
		Guest guest3 = new Guest("Júlio Carlos", "6.234.853", "555263466", event4);
		Guest guest4 = new Guest("Tibério Cavalcante", "6.534.823", "726348866", event1);
		
		event1.getGuests().add(guest4);
		event2.getGuests().add(guest1);
		event4.getGuests().add(guest2);
		event4.getGuests().add(guest3);
		
		eventRepository.saveAll(Arrays.asList(event1, event2, event3, event4));
	}
}
