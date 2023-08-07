package fr.istic.taa.dto;

import fr.istic.taa.jaxrs.domain.Product;

public class ProductOneDto {
	private Long id_product;
	private String productName;
	private int productPrice;
	private String description;
	private String type;
	
	public ProductOneDto(Product product) {
		this.id_product= product.getProductId();
		this.productName= product.getProductName();
		this.productPrice = product.getProductPrice();
		this.description= product.getDescription();
		this.type=product.getType();
	}
	public Long getId_product() {
		return id_product;
	}
	public void setId_product(Long id_product) {
		this.id_product = id_product;
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
	
	
}
