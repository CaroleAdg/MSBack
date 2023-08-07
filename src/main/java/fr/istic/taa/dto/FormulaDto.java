package fr.istic.taa.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import fr.istic.taa.jaxrs.domain.Formula;
import fr.istic.taa.jaxrs.domain.Product;

public class FormulaDto {
	private String formulaName;
	private int formulaPrice;
	private List<Integer> idProduct = new ArrayList<Integer>();
	
	
	public FormulaDto() {
		
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
	public List<Integer> getIdProduct() {
		return idProduct;
	}
	public void setIdProduct(List<Integer> idProduct) {
		this.idProduct = idProduct;
	}
	
	
	
}
