package fr.istic.taa.jaxrs.dao.generic;

import fr.istic.taa.jaxrs.domain.Orders;
import fr.istic.taa.jaxrs.domain.Product;

public class OrdersDao extends AbstractJpaDao<Long,Orders> {
	public OrdersDao() {
		this.setClazz(Orders.class);
	}
}
