package fr.istic.taa.jaxrs.domain;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class Formula_DailyMenu implements Serializable {
	private FormulaDailyMenuId formulaDailyMenuId;
	private int status;
	
	@EmbeddedId
	public FormulaDailyMenuId getFormulaDailyMenuId() {
		return formulaDailyMenuId;
	}
	public void setFormulaDailyMenuId(FormulaDailyMenuId formulaDailyMenuId) {
		this.formulaDailyMenuId = formulaDailyMenuId;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	
	
}
