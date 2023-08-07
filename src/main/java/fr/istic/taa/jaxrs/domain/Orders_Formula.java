package fr.istic.taa.jaxrs.domain;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class Orders_Formula {
	private OrdersFormulaId ordersFormulaId;
	private int quantityOfFormula;
	
	@EmbeddedId
	public OrdersFormulaId getOrdersFormulaId() {
		return ordersFormulaId;
	}
	public void setOrdersFormulaId(OrdersFormulaId ordersFormulaId) {
		this.ordersFormulaId = ordersFormulaId;
	}
	public int getQuantityOfFormula() {
		return quantityOfFormula;
	}
	public void setQuantityOfFormula(int quantityOfFormula) {
		this.quantityOfFormula = quantityOfFormula;
	}
	
	
}

