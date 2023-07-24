package org.jsp.userproduct.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.jsp.userproduct.dto.User;
import org.w3c.dom.ElementTraversal;

public class UserDao {

	EntityManager m = Persistence.createEntityManagerFactory("dev").createEntityManager();

	public User saveUser(User u) {
		EntityTransaction t = m.getTransaction();
		m.persist(u);
		t.begin();
		t.commit();
		return u;
	}
	
	public User updateUser(User u) {
		EntityTransaction t = m.getTransaction();
		m.merge(u);
		t.begin();
		t.commit();
		return u;
	}

	public User verifyUser(long phone,String password) {
		String query = "select u from User u where u.phone=?1 and u.password=?2";
		Query q = m.createQuery(query);
		q.setParameter(1, phone);
		q.setParameter(2, password);
		try {
		 return  (User)q.getSingleResult();
		}catch(NoResultException e) {
			return null;
		}
	}
	public User verifyUser(String email,String password) {
		String query = "select u from User u where u.email=?1 and u.password=?2";
		Query q = m.createQuery(query);
		q.setParameter(1, email);
		q.setParameter(2, password);
		try {
		 return  (User)q.getSingleResult();
		}catch(NoResultException e) {
			return null;
		}
	}
}
