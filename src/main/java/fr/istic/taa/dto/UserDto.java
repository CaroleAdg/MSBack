package fr.istic.taa.dto;

import fr.istic.taa.jaxrs.domain.User;

public class UserDto {
	private String email;
	private String firstName;
	private String lastName;
	private int number;
	private String password;
	private String role;
	
	
	public UserDto() {
		
	}
    public UserDto(User user) {
		this.email= user.getEmail();
		this.firstName= user.getFirstName();
		this.lastName=user.getLastName();
		this.number=user.getNumber();
		this.password=user.getPassword();
		this.role=user.getRole();
	}
    
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
