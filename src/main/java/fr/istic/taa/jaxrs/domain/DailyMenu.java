package fr.istic.taa.jaxrs.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class DailyMenu implements Serializable {
	private Long dailyMenuId;
	private LocalDateTime jour;
	private List<Product> p = new ArrayList<Product>();
	private List<Formula_DailyMenu> fdm = new ArrayList<Formula_DailyMenu>();
	
	public DailyMenu() {
	
	}
	
	@ManyToMany
	@JoinTable(
			name="dailyMenu_product",
			joinColumns=@JoinColumn(name="dailyMenuId"),
			inverseJoinColumns=@JoinColumn(name="productId"))
	public List<Product> getP() {
		return p;
	}
	
	public void setP(List<Product> p) {
		this.p = p;
	}
	
	@OneToMany(mappedBy="formulaDailyMenuId.dailyMenu")
	public List<Formula_DailyMenu> getFdm() {
		return fdm;
	}

	public void setFdm(List<Formula_DailyMenu> fdm) {
		this.fdm = fdm;
	}

	@Id
	@GeneratedValue
	public Long getDailyMenuId() {
		return dailyMenuId;
	}

	public void setDailyMenuId(Long dailyMenuId) {
		this.dailyMenuId = dailyMenuId;
	}

	public LocalDateTime getJour() {
		return jour;
	}

	public void setJour(LocalDateTime jour) {
		this.jour = jour;
	}
	
	
	
	
}
