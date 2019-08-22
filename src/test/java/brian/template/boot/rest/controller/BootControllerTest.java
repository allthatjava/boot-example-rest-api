package brian.template.boot.rest.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

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
	
	//getPerson()
	@Test
	public void testGetPerson_shouldReturnHardCodedValue() {

		// Given
		Person expected = new Person("Hello~", 99);
		
		// Test
		Person actual = controller.getPerson();

		// Assert
		Assert.assertEquals(expected, actual);
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
	public void testAddPerson_withProperName_shouldReturnGivenPerson() throws SamePersonAlreadyExistException {

		Person newPerson = new Person("Brian", 44);
		
		// Given
		Mockito.when(service.addPersonalInfo(newPerson)).thenReturn(newPerson);
		
		// Test
		Person actual = controller.addPerson(newPerson);
		
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
