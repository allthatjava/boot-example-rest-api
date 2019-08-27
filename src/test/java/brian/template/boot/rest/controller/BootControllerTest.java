package brian.template.boot.rest.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import brian.template.boot.rest.controller.exception.PersonNotFoundException;
import brian.template.boot.rest.controller.exception.SamePersonAlreadyExistException;
import brian.template.boot.rest.domain.Person;
import brian.template.boot.rest.service.BootService;

@RunWith(MockitoJUnitRunner.class)
public class BootControllerTest {

	@Mock
	BootService service;
	BootController controller;
	
	@Before
	public void before() {
		controller = new BootController(service);
	}
	
	//getHello()
	@Test
	public void testGetPerson_shouldReturnHardCodedValue() {

		// Given
		Person expected = new Person("Hello~", 99);
		
		// Test
		Person actual = controller.getHello();

		// Assert
		Assert.assertEquals(expected, actual);
	}
	
	//getPerson()
	@Test
	public void testGetPerson_shouldReturnProperItem() throws PersonNotFoundException {

		// Given
		String searchName = "Barnie";
		Optional<Person> expected = Optional.of( new Person( searchName, 44) );
		
		Mockito.when(service.getPerson(searchName)).thenReturn(expected);
		
		// Test
		Person actual = controller.getPerson(searchName);

		// Assert
		Assert.assertEquals( expected.get(), actual);
	}
	
	//getPerson()
	@Test
	public void testGetPerson_withWrongName_shouldReturnNull(){

		// Given
		String searchName = "B";
		Optional<Person> expected = null;
		Mockito.when(service.getPerson(searchName)).thenReturn(expected);
		
		// Test
		try {
			controller.getPerson(searchName);
		} catch (PersonNotFoundException e) {
			return;
		}

		// Assert
		Assert.fail();
	}
	
	//searchByName(@PathVariable("name") String name)
	@Test
	public void testSearchByName_withProperName_shouldReturnMultiplePerson() {

		List<Person> expected = new ArrayList<>();
		expected.add(new Person("Bob", 20));
		expected.add(new Person("Barnie", 44));
		
		// Given
		Mockito.when(service.searchPersonalInfo("B")).thenReturn(expected);
		
		// Test
		List<Person> actual = controller.searchByName("B");
		
		// Assert
		Mockito.verify(service).searchPersonalInfo("B");
		
		Assert.assertEquals( expected.size(), actual.size() );
		Assert.assertTrue( expected.containsAll(actual) );
	}
	
	
	//addPerson(Person person) throws SamePersonAlreadyExistException
	@Test
	public void testAddPerson_withProperName_shouldReturnGivenPerson() throws SamePersonAlreadyExistException{

		Person newPerson = new Person("Brian", 44);
		
		// Given
		Mockito.when(service.addPersonalInfo(newPerson)).thenReturn(newPerson);
		
		// Test
		Person actual = null;;
		try {
			actual = controller.addPerson(newPerson);
		} catch (SamePersonAlreadyExistException e) {
			Assert.fail("Should not throw an Exception");
		}
		
		// Assert
		Assert.assertSame(newPerson, actual);
	}
	
	@Test
	public void testAddPerson_withExistingPerson_shouldThrowSamePersonAlreadyExistException() throws SamePersonAlreadyExistException {

		Person newPerson = new Person("Barnie", 44);
		
		// Given
		Mockito.when(service.addPersonalInfo(newPerson)).thenThrow(new SamePersonAlreadyExistException());
		
		// Test
		try {
			controller.addPerson(newPerson);
		} catch (SamePersonAlreadyExistException e) {
			return;
		}
		
		// Assert
		Assert.fail("It should have been thrown SamePersonAlreadyExistException.");
	}
	
}
