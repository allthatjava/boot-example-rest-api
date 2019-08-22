package brian.template.boot.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

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
	public Person getPerson()
	{
		return new Person("Hello~", 99);
	}
	
	@GetMapping("/search/{name}")
	public List<Person> searchByName(@PathVariable("name") String name){

		return service.searchPersonalInfo(name);
	}
	
	@PostMapping("/person")
	public Person addPerson(Person person) throws SamePersonAlreadyExistException
	{
		return service.addPersonalInfo(person);
	}
}
