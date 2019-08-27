package brian.template.boot.rest.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import brian.template.boot.rest.controller.exception.SamePersonAlreadyExistException;
import brian.template.boot.rest.domain.Person;

@Service
public class BootService {

	List<Person> people = null;
	
	public BootService() {
		people = new ArrayList<>();
		people.add(new Person("Bob", 20));
		people.add(new Person("Harry", 35));
		people.add(new Person("Barnie", 44));
	}
	
	public List<Person> searchPersonalInfo(String name) {
		
		return people.stream()
				.filter(h -> h.getName().toLowerCase().indexOf(name.toLowerCase()) > -1 )
				.collect(Collectors.toList());
	}
	
	public Person addPersonalInfo(Person person) throws SamePersonAlreadyExistException{
		
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
