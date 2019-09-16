package brian.example.boot.rest.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import brian.example.boot.rest.domain.Hello;

@Service
public class BootService {

	List<Hello> people = Arrays.asList(new Hello[] {
			new Hello("Bob", 20),
			new Hello("Harry", 35),
			new Hello("Barnie", 44)
	});
	
	public List<Hello> searchPersonalInfo(String name) {
		return people.stream()
				.filter(h -> h.getName().toLowerCase().indexOf(name.toLowerCase()) > -1 )
				.collect(Collectors.toList());
	}
}
