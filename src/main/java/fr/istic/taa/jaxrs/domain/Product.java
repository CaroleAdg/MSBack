package fr.istic.taa.jaxrs.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;


@Entity
public class Product implements Serializable{
	
	private Long productId;
	private String productName;
	private int productPrice;
	private String description;
	private String type;
	private LocalDateTime createdDate=LocalDateTime.now();
	private LocalDateTime updatedDate;
	private List<Orders_Product> op =new ArrayList<Orders_Product>();
	
	public Product() {
		
	}
	
	@OneToMany(mappedBy="ordersProductId.product")
	public List<Orders_Product> getOp() {
		return op;
	}

	public void setOp(List<Orders_Product> op) {
		this.op = op;
	}

	@Id
	@GeneratedValue
	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
