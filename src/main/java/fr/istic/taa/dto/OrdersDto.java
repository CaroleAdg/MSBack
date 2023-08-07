package fr.istic.taa.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import fr.istic.taa.jaxrs.domain.Formula;
import fr.istic.taa.jaxrs.domain.Product;
import fr.istic.taa.jaxrs.domain.User;

public class OrdersDto {
	private int orderStatus;
	private int orderPrice;
	private Long userId;
	private List<ProductQuantityDto> productQuantityId = new ArrayList<ProductQuantityDto>();
	private List<FormulaQuantityDto> formulaQuantityId = new ArrayList<FormulaQuantityDto>();
	
	
	public OrdersDto() {

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


	public Long getUserId() {
		return userId;
	}


	public void setUserId(Long userId) {
		this.userId = userId;
	}


	public List<ProductQuantityDto> getProductQuantityId() {
		return productQuantityId;
	}


	public void setProductQuantityId(List<ProductQuantityDto> productQuantityId) {
		this.productQuantityId = productQuantityId;
	}


	public List<FormulaQuantityDto> getFormulaQuantityId() {
		return formulaQuantityId;
	}


	public void setFormulaQuantityId(List<FormulaQuantityDto> formulaQuantityId) {
		this.formulaQuantityId = formulaQuantityId;
	}


	

	

	
	
}
