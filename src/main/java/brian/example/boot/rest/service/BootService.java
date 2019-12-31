package brian.example.boot.rest.service;

import brian.example.boot.rest.controller.exception.SamePersonAlreadyExistException;
import brian.example.boot.rest.domain.Person;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BootService {

	/**
	 * Default modifier, so it can be access from test class
	 */
	List<Person> people = new ArrayList<>();
	
	public List<Person> searchPersonalInfo(String name) {
		
		return people.stream()
				.filter(h -> h.getName().toLowerCase().indexOf(name.toLowerCase()) > -1 )
				.collect(Collectors.toList());
	}
	
	public Person addPersonalInfo(Person person) throws SamePersonAlreadyExistException {
		
		boolean alreadyExist = people.stream()
			.anyMatch(p -> p.getName().equals(person.getName()) && p.getAge() == person.getAge());
		
		if( alreadyExist )
			throw new SamePersonAlreadyExistException("Name:"+person.getAge()+",Age:"+person.getAge()+" already exists");
		else
			people.add(person);
		
		return person;
	}
	
	public Optional<Person> getPerson(String name) {
		return people.stream()
				.filter(p-> name.equals(p.getName()))
				.findFirst();
	}

}
