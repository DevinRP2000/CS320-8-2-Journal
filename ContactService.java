package contact;

import java.util.HashMap;
import java.util.Map;

public class ContactService {
	private Map<String, Contact> contacts = new HashMap<>();
	
	// Adds a contact to contacts map, throws exception if contactID already exists.
	public void addContact(String contactID, String firstName, String lastName, String phoneNumber, String address) {
		if(contacts.containsKey(contactID)) {
			throw new IllegalArgumentException("Contact ID already exists");
		}
		
		Contact contact = new Contact(contactID, firstName, lastName, phoneNumber, address);
		contacts.put(contactID, contact);
	}
	
	
	// Deletes a contact by contactID, throws exception if contacts doesn't contain contactID
	public void deleteContact(String contactID) {
		if(!contacts.containsKey(contactID)) {
			throw new IllegalArgumentException("Contact ID does not exist");
		}
		
		contacts.remove(contactID);
	}
	
	// Updates a contact with new values without changing contactID.
	public void updateContact(String contactID, String firstName, String lastName, String phoneNumber, String address) {
		Contact existingContact = contacts.get(contactID);
		if(existingContact == null) {
			throw new IllegalArgumentException("Contact ID does not exist");
		}
		
		// If new values are null, replace with existing values
		firstName = (firstName != null) ? firstName : existingContact.getFirstName();
		lastName = (lastName != null) ? lastName : existingContact.getLastName();
		phoneNumber = (phoneNumber != null) ? phoneNumber : existingContact.getPhoneNumber();
		address = (address != null) ? address : existingContact.getAddress();
		
		
		Contact updated = new Contact(contactID, firstName, lastName, phoneNumber, address);
		contacts.put(contactID, updated);
	}
	
	// Getter function
	public Contact getContact(String contactID) {
		if(!contacts.containsKey(contactID)) {
			throw new IllegalArgumentException("Contact ID does not exist");
		}
		return contacts.get(contactID);
	}
}
