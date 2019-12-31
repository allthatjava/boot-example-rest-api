package brian.example.boot.rest.controller;

import brian.example.boot.rest.controller.exception.PersonNotFoundException;
import brian.example.boot.rest.controller.exception.SamePersonAlreadyExistException;
import brian.example.boot.rest.domain.Person;
import brian.example.boot.rest.service.BootService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BootController 
{
	private BootService service;
	
	@Autowired
	public BootController(BootService service) {
		this.service = service;
	}
	
	@GetMapping("/")
	public Person getHello()
	{
		return new Person("Hello~", 99);
	}
	
	@GetMapping("/search")
	public List<Person> searchByName(@RequestParam("name") String name){

		return service.searchPersonalInfo(name);
	}
	
	@GetMapping("/person/{name}")
	public Person getPerson(@PathVariable("name") String name) throws PersonNotFoundException
	{
		Optional<Person> person = service.getPerson(name);
		if( person.isPresent() )
			return person.get();
		else
			throw new PersonNotFoundException("Name:"+name+" was not found");
	}
	
	@PostMapping("/person")
	public Person addPerson(Person person) throws SamePersonAlreadyExistException
	{
		return service.addPersonalInfo(person);
	}
}
