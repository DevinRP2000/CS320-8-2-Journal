package contact;

public class Contact {
	private final String contactID;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String address;
	
	// Constructor class
	public Contact(String id, String firstN, String lastN, String phoneNum, String address) {
		if(id == null || id.length() > 10) {
			throw new IllegalArgumentException("Invalid ContactID");
		}
		if(firstN == null || firstN.length() > 10) {
			throw new IllegalArgumentException("Invalid First Name");
		}
		if(lastN == null || lastN.length() > 10) {
			throw new IllegalArgumentException("Invalid Last Name");
		}
		if(phoneNum == null || !(phoneNum.matches("\\d{10}"))) {
			throw new IllegalArgumentException("Invalid Phone Number");
		}
		if(address == null || address.length() > 30) {
			throw new IllegalArgumentException("Invalid Address");
		}
		
		this.contactID = id;
		this.firstName = firstN;
		this.lastName = lastN;
		this.address = address;
		this.phoneNumber = phoneNum;
	}
	
	// Getter Functions
	public String getFirstName() {return firstName;}
	public String getLastName() {return lastName;}
	public String getContactID() {return contactID;}
	public String getAddress() {return address;}
	public String getPhoneNumber() {return phoneNumber;}
}
