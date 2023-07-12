package fr.istic.taa.jaxrs.domain;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class Orders_Product implements Serializable {
	private OrdersProductId ordersProductId;
	private int quantityOfProduct;
	
	@EmbeddedId
	public OrdersProductId getOrdersProductId() {
		return ordersProductId;
	}
	public void setOrdersProductId(OrdersProductId ordersProductId) {
		this.ordersProductId = ordersProductId;
	}
	public int getQuantityOfProduct() {
		return quantityOfProduct;
	}
	public void setQuantityOfProduct(int quantityOfProduct) {
		this.quantityOfProduct = quantityOfProduct;
	}
	
	
	
}
