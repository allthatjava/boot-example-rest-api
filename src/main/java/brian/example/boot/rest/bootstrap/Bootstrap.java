package brian.example.boot.rest.bootstrap;

import brian.example.boot.rest.domain.Person;
import brian.example.boot.rest.service.BootService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * When the Spring Boot application starts, this will be triggered first.
 * [[[ You can pick up VM arguments and stuff here as well. ]]]
 */
@Component
public class Bootstrap implements CommandLineRunner {

    private BootService service;

    @Autowired
    public Bootstrap(BootService service){
        this.service = service;
    }

    @Override
    public void run(String... args) throws Exception {
        service.addPersonalInfo(new Person("Bob", 20));
        service.addPersonalInfo(new Person("Harry", 35));
        service.addPersonalInfo(new Person("Barnie", 44));
    }
}
