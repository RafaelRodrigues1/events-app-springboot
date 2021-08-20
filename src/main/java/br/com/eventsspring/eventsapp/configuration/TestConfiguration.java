package br.com.eventsspring.eventsapp.configuration;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.eventsspring.eventsapp.models.entities.Event;
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
		
		eventRepository.saveAll(Arrays.asList(event1, event2, event3, event4));
	}
}
