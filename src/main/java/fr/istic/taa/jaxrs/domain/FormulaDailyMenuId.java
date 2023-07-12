package fr.istic.taa.jaxrs.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class FormulaDailyMenuId implements Serializable {
	private DailyMenu dailyMenu;
	private Formula formula;
	
	@ManyToOne
	public DailyMenu getDailyMenu() {
		return dailyMenu;
	}
	public void setDailyMenu(DailyMenu dailyMenu) {
		this.dailyMenu = dailyMenu;
	}
	@ManyToOne
	public Formula getFormula() {
		return formula;
	}
	public void setFormula(Formula formula) {
		this.formula = formula;
	}
	
	
}
