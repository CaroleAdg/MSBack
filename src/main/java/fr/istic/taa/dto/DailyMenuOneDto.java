package fr.istic.taa.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import fr.istic.taa.jaxrs.domain.DailyMenu;
import fr.istic.taa.jaxrs.domain.Formula_DailyMenu;
import fr.istic.taa.jaxrs.domain.Product;

public class DailyMenuOneDto {
	private Long dailyMenuId;
	private LocalDateTime jour;
	
	
	public DailyMenuOneDto() {
		
	}
	public DailyMenuOneDto(DailyMenu dm) {
		this.dailyMenuId= dm.getDailyMenuId();
		this.jour= dm.getJour();
	}
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
