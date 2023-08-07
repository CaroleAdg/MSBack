package fr.istic.taa.jaxrs.dao.generic;

import fr.istic.taa.jaxrs.domain.Formula;
import fr.istic.taa.jaxrs.domain.Orders;

public class FormulaDao extends AbstractJpaDao<Long,Formula> {
	public FormulaDao() {
		this.setClazz(Formula.class);
	}
}
