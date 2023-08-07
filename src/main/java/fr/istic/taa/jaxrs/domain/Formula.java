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
import javax.persistence.OneToMany;

@Entity
public class Formula implements Serializable{
	private Long formulaId;
	private String formulaName;
	private int formulaPrice;
	private LocalDateTime createdDate=LocalDateTime.now();
	private LocalDateTime updatedDate;
	private List<Product> p = new ArrayList<Product>();
	private List<Orders_Formula> of = new ArrayList<Orders_Formula>();
	private List<Formula_DailyMenu> fdm = new ArrayList<Formula_DailyMenu>();

	public Formula() {
		
	}
	
	@ManyToMany
	@JoinTable(
			name="formula_product",
			joinColumns=@JoinColumn(name="formulaId"),
			inverseJoinColumns=@JoinColumn(name="productId"))
	
	public List<Product> getP() {
		return p;
	}

	public void setP(List<Product> p) {
		this.p = p;
	}
	
	@OneToMany(mappedBy="ordersFormulaId.formula")
	public List<Orders_Formula> getOf() {
		return of;
	}

	public void setOf(List<Orders_Formula> of) {
		this.of = of;
	}
	
	@OneToMany(mappedBy="formulaDailyMenuId.formula")
	public List<Formula_DailyMenu> getFdm() {
		return fdm;
	}

	public void setFdm(List<Formula_DailyMenu> fdm) {
		this.fdm = fdm;
	}

	@Id
	@GeneratedValue
	public Long getFormulaId() {
		return formulaId;
	}
	public void setFormulaId(Long formulaId) {
		this.formulaId = formulaId;
	}
	public String getFormulaName() {
		return formulaName;
	}
	public void setFormulaName(String formulaName) {
		this.formulaName = formulaName;
	}
	public int getFormulaPrice() {
		return formulaPrice;
	}
	public void setFormulaPrice(int formulaPrice) {
		this.formulaPrice = formulaPrice;
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
	
	
}
