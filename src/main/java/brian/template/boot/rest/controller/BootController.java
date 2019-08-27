package brian.template.boot.rest.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import brian.template.boot.rest.controller.exception.PersonNotFoundException;
import brian.template.boot.rest.controller.exception.SamePersonAlreadyExistException;
import brian.template.boot.rest.domain.Person;
import brian.template.boot.rest.service.BootService;

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
		if( person != null && person.isPresent() )
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
