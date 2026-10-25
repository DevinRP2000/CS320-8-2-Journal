package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import contact.Contact;
import contact.ContactService;

class ContactServiceTest {

	private ContactService contactService;
	
	@BeforeEach
	void setUp() {
		contactService = new ContactService();
	}
	
	@Test
	void testAddContact() {
		// Tests addContact function
		contactService.addContact("1", "John", "Jones", "5552444811", "333 North West Ave");
		Contact contact = contactService.getContact("1");
		assertEquals("John", contact.getFirstName());
		assertEquals("Jones", contact.getLastName());
		assertEquals("5552444811", contact.getPhoneNumber());
		assertEquals("333 North West Ave", contact.getAddress());
	}
	
	@Test
	void testAddContactDuplicateID() {
		// Tests if addContact throws duplicate ID
		contactService.addContact("1", "John", "Jones", "5552444811", "333 North West Ave");
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			contactService.addContact("1", "Jane", "Smith", "5552554833", "444 South West Ave");
		});
	}
	
	@Test
	void testAddContactInvalidContactField() {
		// Tests if Contact's validation still applies during the addContact function
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			contactService.addContact("1", "JohnnyJoeStar", "Smith", "5552554833", "444 South West Ave");
		});
	}
	
	@Test
	void testDeleteContact() {
		// Tests deleteContact function
		contactService.addContact("1", "John", "Jones", "5552444811", "333 North West Ave");
		contactService.deleteContact("1");
		// Sees if getContact throws Invalid ID since contact ID "1" no longer exists
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			contactService.getContact("1");
		});
	}
	
	@Test
	void testDeleteContactInvalidID() {
		// Tests if deleteContact throws invalid ID
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			contactService.deleteContact("InvalidID");
		});
	}
	
	@Test
	void testGetContactInvalidID() {
		// Tests if deleteContact throws invalid ID
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			contactService.getContact("InvalidID");
		});
	}
	
	@Test
	void testUpdateContactFirstName() {
		// Tests updateContact function properly updates firstName
		contactService.addContact("1", "John", "Jones", "5552444811", "333 North West Ave");
		contactService.updateContact("1", "Jonathan", null, null, null);
		assertEquals("Jonathan", contactService.getContact("1").getFirstName());
		// Unchanged/Null fields should remain the same
		assertEquals("Jones", contactService.getContact("1").getLastName());
	}
	
	@Test
	void testUpdateContactLastName() {
		// Tests updateContact function properly updates lastName
		contactService.addContact("1", "John", "Jones", "5552444811", "333 North West Ave");
		contactService.updateContact("1", null, "David", null, null);
		assertEquals("David", contactService.getContact("1").getLastName());
		// Unchanged/Null fields should remain the same
		assertEquals("333 North West Ave", contactService.getContact("1").getAddress());
	}
	
	@Test
	void testUpdateContactPhoneNumber() {
		// Tests updateContact function properly updates phoneNumber
		contactService.addContact("1", "John", "Jones", "5552444811", "333 North West Ave");
		contactService.updateContact("1", null, null, "5209914871", null);
		assertEquals("5209914871", contactService.getContact("1").getPhoneNumber());
		// Unchanged/Null fields should remain the same
		assertEquals("John", contactService.getContact("1").getFirstName());
	}
	
	@Test
	void testUpdateContactAddress() {
		// Tests updateContact function properly updates address
		contactService.addContact("1", "John", "Jones", "5552444811", "333 North West Ave");
		contactService.updateContact("1", null, null, null, "444 South West Ave");
		assertEquals("444 South West Ave", contactService.getContact("1").getAddress());
		// Unchanged/Null fields should remain the same
		assertEquals("5552444811", contactService.getContact("1").getPhoneNumber());
	}
	
	@Test
	void testUpdateContactInvalidID() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			contactService.updateContact("doesNotExist", "Jane", null, null, null);
		});
	}
	
	@Test
	void testUpdateContactInvalidContactField() {
		contactService.addContact("1", "John", "Jones", "5552444811", "333 North West Ave");
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			contactService.updateContact("1", "JohnnyJoeStar", null, null, null);
		});
	}
}
