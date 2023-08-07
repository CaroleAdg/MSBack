package fr.istic.taa.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import fr.istic.taa.jaxrs.domain.Orders;
import fr.istic.taa.jaxrs.domain.Orders_Formula;
import fr.istic.taa.jaxrs.domain.Orders_Product;
import fr.istic.taa.jaxrs.domain.User;

public class OrdersOneDto {
	private Long orderId;
	private int orderStatus;
	private int orderPrice;
	private User u;
	private List<Orders_Product> op = new ArrayList<Orders_Product>();
	private List<Orders_Formula> of = new ArrayList<Orders_Formula>();
	
	
	public OrdersOneDto() {
		
	}
	public OrdersOneDto(Orders order) {
		this.orderId=order.getOrderId();
		this.orderStatus= order.getOrderStatus();
		this.orderPrice= order.getOrderPrice();
		this.of=order.getOf();
		this.op= order.getOp();
	}
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public int getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(int orderStatus) {
		this.orderStatus = orderStatus;
	}
	public int getOrderPrice() {
		return orderPrice;
	}
	public void setOrderPrice(int orderPrice) {
		this.orderPrice = orderPrice;
	}
	public User getU() {
		return u;
	}
	public void setU(User u) {
		this.u = u;
	}
	public List<Orders_Product> getOp() {
		return op;
	}
	public void setOp(List<Orders_Product> op) {
		this.op = op;
	}
	public List<Orders_Formula> getOf() {
		return of;
	}
	public void setOf(List<Orders_Formula> of) {
		this.of = of;
	}
	
	
}
