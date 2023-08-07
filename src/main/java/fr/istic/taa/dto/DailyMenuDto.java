package fr.istic.taa.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import fr.istic.taa.jaxrs.domain.Formula_DailyMenu;

public class DailyMenuDto {
	private List<Long> id_Product = new ArrayList<Long>();
	private List<FormulaDailyMenuStatusDto> fdstatus = new ArrayList<FormulaDailyMenuStatusDto>();
	
	
	public DailyMenuDto() {
		
	}
	
	public List<Long> getId_Product() {
		return id_Product;
	}
	public void setId_Product(List<Long> id_Product) {
		this.id_Product = id_Product;
	}

	public List<FormulaDailyMenuStatusDto> getFdstatus() {
		return fdstatus;
	}
	public void setFdstatus(List<FormulaDailyMenuStatusDto> fdstatus) {
		this.fdstatus = fdstatus;
	}
	
	

}
