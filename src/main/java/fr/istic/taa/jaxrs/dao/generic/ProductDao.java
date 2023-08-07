package fr.istic.taa.jaxrs.dao.generic;

import fr.istic.taa.jaxrs.domain.Product;
import fr.istic.taa.jaxrs.domain.User;

public class ProductDao extends AbstractJpaDao<Long,Product> {
	public ProductDao() {
		this.setClazz(Product.class);
	}
}
