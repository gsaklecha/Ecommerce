package com.sample.demo.repository.impl;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sample.demo.model.CartItem;
import com.sample.demo.model.User;
import com.sample.demo.repository.ICartItemDao;

@Repository
@Transactional
public class CartItemDao extends GenericJpaDao<CartItem> implements ICartItemDao {
	

	@Override
	public List<CartItem> getAllCartItems(User user) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<CartItem> cr = cb.createQuery(CartItem.class);
		Root<CartItem> root = cr.from(CartItem.class);
		cr.select(root).where(cb.equal(root.get("user"), user));
		Query query = entityManager.createQuery(cr);
		return query.getResultList();
	}

}
