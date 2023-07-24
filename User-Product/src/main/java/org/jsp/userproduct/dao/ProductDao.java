package org.jsp.userproduct.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.jsp.userproduct.dto.Product;
import org.jsp.userproduct.dto.User;

public class ProductDao {

	EntityManager m = Persistence.createEntityManagerFactory("dev").createEntityManager();
	
	public Product saveProduct(Product p,int u_id) {
		User u = m.find(User.class, u_id);
		if(u!=null) {
		EntityTransaction t = m.getTransaction();
		p.setUser(u);
		u.getProducts().add(p);
		m.persist(p);
		t.begin();
		t.commit();
		return p;
	}
		else
			return null;
}
	
	public List<Product> fetchProducts(int u_id){
		String query = "select u.products from User u where u.id=?1";
		Query q = m.createQuery(query);
		q.setParameter(1,u_id);
		List<Product> ps = q.getResultList();
		if(ps.size()>0) {
			return ps;
		}
		else
			return null;
	}
	
	public List<Product> fetchProducts(String category){
		String query = "select p from Product p where p.category=?1";
		Query q = m.createQuery(query);
		q.setParameter(1,category);
		List<Product> ps = q.getResultList();
		if(ps.size()>0) {
			return ps;
		}
		else
			return null;
	}
	
	

}