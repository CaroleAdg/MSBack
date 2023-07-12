package fr.istic.taa.jaxrs.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class OrdersFormulaId implements Serializable{
	private Orders order;
	private Formula formula;
	
	@ManyToOne
	public Orders getOrder() {
		return order;
	}
	public void setOrder(Orders order) {
		this.order = order;
	}
	@ManyToOne
	public Formula getFormula() {
		return formula;
	}
	public void setFormula(Formula formula) {
		this.formula = formula;
	}
	
	
}
