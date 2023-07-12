package fr.istic.taa.jaxrs.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity
public class Orders implements Serializable {
	public void setOp(List<Orders_Product> op) {
		this.op = op;
	}

	private Long orderId;
	private int orderStatus;
	private int orderPrice;
	private LocalDateTime createdDate;
	private LocalDateTime updatedDate;
	private User u;
	private List<Orders_Product> op = new ArrayList<Orders_Product>();
	private List<Orders_Formula> of = new ArrayList<Orders_Formula>();
	
	public Orders() {
	
	}
	
	@ManyToOne
	public User getU() {
		return u;
	}
	public void setU(User u) {
		this.u = u;
	}
	
	@OneToMany(mappedBy="ordersProductId.order")
	public List<Orders_Product> getOp() {
		return op;
	}
	
	public void setP(List<Orders_Product> op) {
		this.op = op;
	}
	
	@OneToMany(mappedBy="ordersFormulaId.order")
	public List<Orders_Formula> getF() {
		return of;
	}

	public void setF(List<Orders_Formula> of) {
		this.of = of;
	}

	@Id
	@GeneratedValue
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public int getOrderPrice() {
		return orderPrice;
	}
	public void setOrderPrice(int orderPrice) {
		this.orderPrice = orderPrice;
	}
	public LocalDateTime getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}
	public LocalDateTime getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(LocalDateTime updatedDate) {
		this.updatedDate = updatedDate;
	}

	public int getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(int orderStatus) {
		this.orderStatus = orderStatus;
	}
	
	
}
