package fr.istic.taa.dto;

import java.util.ArrayList;
import java.util.List;

import fr.istic.taa.jaxrs.domain.Formula;
import fr.istic.taa.jaxrs.domain.Product;

public class FormulaOneDto {
	private Long id_formula;
	private String formulaName;
	private int formulaPrice;
	private List<Product> p = new ArrayList<Product>();
	
	
	public FormulaOneDto(Formula formula) {
		this.formulaName=formula.getFormulaName();
		this.id_formula=formula.getFormulaId();
		this.formulaPrice= formula.getFormulaPrice();
		this.p=formula.getP();
	}
	
	public Long getId_formula() {
		return id_formula;
	}

	public void setId_formula(Long id_formula) {
		this.id_formula = id_formula;
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
	public List<Product> getP() {
		return p;
	}
	public void setP(List<Product> p) {
		this.p = p;
	}
	
	
}
