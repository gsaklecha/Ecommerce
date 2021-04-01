package com.sample.demo.repository.impl;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sample.demo.model.Order;
import com.sample.demo.model.User;
import com.sample.demo.repository.IOrderDao;

@Repository
@Transactional
public class OrderDao extends GenericJpaDao<Order> implements IOrderDao {
	

	@Override
	public List<Order> getOrders(User user) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Order> cr = cb.createQuery(Order.class);
		Root<Order> root = cr.from(Order.class);
		cr.select(root).where(cb.equal(root.get("user"), user));
		Query query = entityManager.createQuery(cr);
		return query.getResultList();
	}

}
