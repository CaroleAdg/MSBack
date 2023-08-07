package fr.istic.taa.dto;

import java.util.ArrayList;
import java.util.List;

import fr.istic.taa.jaxrs.domain.Orders;
import fr.istic.taa.jaxrs.domain.User;

public class UserOneDto {
	private Long userId;
	private String email;
	private String firstName;
	private String lastName;
	private int number;
	private String password;
	private String role;
	private List<Orders> orders = new ArrayList<Orders>();
	
	
	public UserOneDto() {

	}
	public UserOneDto(User u) {
		this.userId=u.getUserId();
		this.email=u.getEmail();
		this.firstName=u.getEmail();
		this.lastName=u.getLastName();
		this.number=u.getNumber();
		this.password=u.getPassword();
		this.role=u.getRole();
		this.orders=u.getOrders();
		
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public List<Orders> getOrders() {
		return orders;
	}
	public void setOrders(List<Orders> orders) {
		this.orders = orders;
	}
	
	

}
