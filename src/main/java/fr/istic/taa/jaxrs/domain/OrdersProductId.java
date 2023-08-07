package fr.istic.taa.jaxrs.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class OrdersProductId implements Serializable{
	private Orders order;
	private Product product;

	
	
	public OrdersProductId() {
		
	}
	public OrdersProductId(Orders order, Product product) {
		this.order = order;
		this.product = product;
	}
	@ManyToOne
	public Orders getOrder() {
		return order;
	}
	public void setOrder(Orders order) {
		this.order = order;
	}
	@ManyToOne
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	
	
}
