package test;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import contact.Contact;

class ContactTest {

	@Test
	void testContactClass() {
		// Tests contact class
		Contact contactClass = new Contact("1", "John", "Jones", "5552444811", "333 North West Ave");
		assertEquals("1", contactClass.getContactID());
		assertEquals("John", contactClass.getFirstName());
		assertEquals("Jones", contactClass.getLastName());
		assertEquals("5552444811", contactClass.getPhoneNumber());
		assertEquals("333 North West Ave", contactClass.getAddress());
	}
	
	// These tests see if exceptions are thrown if argument length is too long (or too short for phone number)
	@Test
	void testContactClassIDTooLong() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Contact("a".repeat(11), "John", "Jones", "5552444811", "333 North West Ave");
		});
	}
	
	@Test
	void testContactClassFirstNameTooLong() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Contact("1", "a".repeat(11), "Jones", "5552444811", "333 North West Ave");
		});
	}
	
	@Test
	void testContactClassLastNameTooLong() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Contact("1", "John", "a".repeat(11), "5552444811", "333 North West Ave");
		});
	}
	
	@Test
	void testContactClassAddressTooLong() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Contact("1", "John", "Jones", "5552444811", "a".repeat(31));
		});
	}
	
	@Test
	void testContactClassPhoneNumberTooLong() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Contact("1", "John", "Jones", "1".repeat(11), "333 North West Ave");
		});
	}
	
	@Test
	void testContactClassPhoneNumberTooShort() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Contact("1", "John", "Jones", "1".repeat(9), "333 North West Ave");
		});
	}
	
	// These tests check exception boundaries
	@Test
	void testContactClassIDBoundary() {
		Assertions.assertDoesNotThrow(() -> {
			new Contact("a".repeat(10), "John", "Jones", "5552444811", "333 North West Ave");
		});
	}
	
	@Test
	void testContactClassFirstNameBoundary() {
		Assertions.assertDoesNotThrow(() -> {
			new Contact("1", "a".repeat(10), "Jones", "5552444811", "333 North West Ave");
		});
	}
	
	@Test
	void testContactClassLastNameBoundary() {
		Assertions.assertDoesNotThrow(() -> {
			new Contact("1", "John", "a".repeat(10), "5552444811", "333 North West Ave");
		});
	}
	
	@Test
	void testContactClassAddressBoundary() {
		Assertions.assertDoesNotThrow(() -> {
			new Contact("1", "John", "Jones", "5552444811", "a".repeat(30));
		});
	}
	
	@Test
	void testContactClassPhoneNumberBoundary() {
		Assertions.assertDoesNotThrow(() -> {
			new Contact("1", "John", "Jones", "1".repeat(10), "333 North West Ave");
		});
	}
	
	// Tests for non-digit inputs for phone number
	@Test
	void testContactClassPhoneNumberNonDigit() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Contact("1", "John", "Jones", "55524d4811", "333 North West Ave");
		});
	}
	
	// These tests see if exceptions are thrown if arguments are null
	@Test
	void testContactClassIDNull() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Contact(null, "John", "Jones", "5552444811", "333 North West Ave");
		});
	}
	
	@Test
	void testContactClassFirstNameNull() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Contact("1", null, "Jones", "5552444811", "333 North West Ave");
		});
	}
	
	@Test
	void testContactClassLastNameNull() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Contact("1", "John", null, "5552444811", "333 North West Ave");
		});
	}
	
	@Test
	void testContactClassPhoneNumberNull() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Contact("1", "John", "Jones", null, "333 North West Ave");
		});
	}
	
	@Test
	void testContactClassAddressNull() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Contact("1", "John", "Jones", "5552444811", null);
		});
	}
	
	// This tests sees if contactID is final and cannot be modified
	@Test
	void testContactIDFinal() throws NoSuchFieldException, SecurityException {
		for(Method m : Contact.class.getMethods()) {
			assertNotEquals("setContactID", m.getName());
		}
		
		assertTrue(Modifier.isFinal(
				Contact.class.getDeclaredField("contactID").getModifiers()));
	}
}
