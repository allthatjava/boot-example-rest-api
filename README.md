# Spring Boot Template - RESTful API

Simple Template for Spring __RESTfull Service__ with automatically generated(bottom-up approach) __Swagger UI__.
Please check the similar but, Top-to-bottom approach RESTful webservice [here](https://github.com/allthatjava/boot-example-rest-api2) to see how it can be implemented.

## Simple RESTful API
The following services are included
* __/__ : will return one hard coded Person object that has name "Hello~" and age 99
* __/search?name={name}__ : will return search results. (We have static names 'Bob', 'Harry', and 'Barnie')
* __/person/Bob__ : The JSON format of Person object will be returned
* __/person__ :  [POST] The JSON format of Person object is expected in request body and it will be saved in memory

```java
@GetMapping("/")
public Hello getHello() {...}
	
@GetMapping("/search")
public List<Person> searchByName(@RequestParam("name") String name){...}

@GetMapping("/person/{name}")
public Person getPerson(@PathVariable("name") String name) throws PersonNotFoundException{...}

@PostMapping("/person")
public Person addPerson(Person person) throws SamePersonAlreadyExistException{...}

@GetMapping("/timeout") // RestTemplate timeout example
public ResponseEntity<String> timeoutTest(){...}

@GetMapping("/restError")   // RestTempate server error handle example
public ResponseEntity<String> restErrorTest(){...}
```

## RESTful Service API by Swagger
To have automatically generated Swagger based on the provided service, the following dependencies are required

```
    compile('org.apache.httpcomponents:httpclient:4.3.4');  // To test RestTemplate timeout

    compile('io.springfox:springfox-swagger2:2.9.2')        // To usw Swagger
    compile('io.springfox:springfox-swagger-ui:2.9.2')      // To show Swagger UI
```

Also, it requires some configuration

```java
@Configuration
@EnableSwagger2
public class SwaggerConfig extends WebMvcConfigurationSupport {
	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors
				.basePackage("brian.example.boot.rest.controller"))		// Where to look for the service
				.build();
	}

	@Override
	protected void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html")
					.addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**")
					.addResourceLocations("classpath:/META-INF/resources/webjars/");
	}
}

```

## How to run ##
You can use either IDE or Command line. For the command line, type the following command

```
./gradlew clean build bootRun
```

## Swagger UI ##
After start this application, the following url will display the screenshot below


[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)


![alt swagger ui](docs/images/swagger-ui.PNG)

#### How to use Swagger UI ####
You can open the each service by clicking and click on "Try it out" button. It will let you enter the parameters.
Then hit "Execute" button below. You will see the result below.

![alt swagger ui opened service](docs/images/swagger-ui-opened-service.PNG)
