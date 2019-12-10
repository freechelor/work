package stx.annotation.baeldung;

@JsonSerializable
public class Person {
	
	@JsonElement
	private String firstName;
	@JsonElement
	private String lastName;
	@JsonElement
	private String age;
	@JsonElement
	public String address;
	
	public Person(String firstName, String lastName, String age, String address) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.address = address;
	}

	@Init
	private void initNames() {
		this.firstName = this.firstName.substring(0,1).toUpperCase() + this.firstName.substring(1);
		this.lastName = this.lastName.substring(0,1).toUpperCase() + this.lastName.substring(1);
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}
}
