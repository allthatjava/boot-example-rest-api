package brian.template.boot.rest.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import brian.template.boot.rest.controller.exception.SamePersonAlreadyExistException;
import brian.template.boot.rest.domain.Person;

public class BootServiceTest {
	
	BootService service;

	List<Person> expected; 
	
	@Before
	public void before() {
		service = new BootService();
		
		expected = new ArrayList<>();
		expected.add(new Person("Bob", 20));
		expected.add(new Person("Harry", 35));
		expected.add(new Person("Barnie", 44));
	}
	
	// Constructor Test
	@Test
	public void testConstructor_withNothing_returnsListWithThreeItems() {
		Assert.assertEquals( 3 , service.people.size());
	}
	
	// searchPersonalInfo(String name)
	@Test 
	public void testSearch_withProperValue_returnsMultipleItems() {
		List<Person> actual = service.searchPersonalInfo("b");
		
		Assert.assertTrue(actual.size() > 1);
	}
	
	public void testSearch_withNotExistingValue_returnsEmptyList() {
		List<Person> actual = service.searchPersonalInfo("x");
		
		Assert.assertEquals( 0, actual.size());
	}
	
	// addPersonalInfo(..)
	@Test
	public void testAddPerson_withNew_returnsAddedItem() {
		// Given
		Person person = new Person("Jackson", 33);
		
		// Test
		Person actual = null;
		try {
			actual = service.addPersonalInfo(person);
		} catch (SamePersonAlreadyExistException e) {
			Assert.fail("It should not throw exception");
		}
		
		// Assert
		Assert.assertSame(person, actual);
	}
	
	@Test
	public void testAddPerson_withExisting_shouldThrowSamePersonAlreadyExistException() {
		try {
			service.addPersonalInfo(new Person("Barnie", 44));
		} catch (SamePersonAlreadyExistException e) {
			return;
		}
		
		Assert.fail("The exception must have been thrown.");
	}
	
	
}
