package brian.example.boot.rest.controller;

import brian.example.boot.rest.controller.exception.CustomerRestTemplateException;
import brian.example.boot.rest.controller.exception.PersonNotFoundException;
import brian.example.boot.rest.controller.exception.RestTemplateResponseaErrorHandler;
import brian.example.boot.rest.controller.exception.SamePersonAlreadyExistException;
import brian.example.boot.rest.domain.Person;
import brian.example.boot.rest.service.BootService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.SocketTimeoutException;
import java.util.List;
import java.util.Optional;

@RestController
public class BootController 
{
	private BootService service;

	@Autowired
	private RestTemplate restTemplate;
	
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

	@GetMapping("/timeout")
	public ResponseEntity<String> timeoutTest(){
		String url = "http://httpstat.us/200?sleep=10000";

		try {
			return restTemplate.exchange(url, HttpMethod.GET, HttpEntity.EMPTY, String.class);
		} catch (RestClientException e) {
			if( e.getCause() instanceof SocketTimeoutException){
				return ResponseEntity.ok().body("Third party call not answering within given time");
			}else{
				return ResponseEntity.ok().body("Third party call answering within error message:"+e.getMessage());
			}
		}
	}

	@GetMapping("/restError")
	public ResponseEntity<String> restErrorTest(){
		String url = "http://httpstat.us/500";

		restTemplate.setErrorHandler(new RestTemplateResponseaErrorHandler());

		try {
			return restTemplate.exchange(url, HttpMethod.GET, HttpEntity.EMPTY, String.class);
		} catch (CustomerRestTemplateException e) {
			return ResponseEntity.ok().body(e.getMessage());
		}
	}
}
