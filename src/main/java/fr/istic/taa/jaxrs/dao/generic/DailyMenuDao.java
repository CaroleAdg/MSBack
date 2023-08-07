package fr.istic.taa.jaxrs.dao.generic;

import fr.istic.taa.jaxrs.domain.DailyMenu;
import fr.istic.taa.jaxrs.domain.Formula;

public class DailyMenuDao extends AbstractJpaDao<Long,DailyMenu>{
	public DailyMenuDao() {
		this.setClazz(DailyMenu.class);
	}
}
