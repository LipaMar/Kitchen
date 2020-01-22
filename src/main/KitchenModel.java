package main;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import javax.persistence.*;

import org.hibernate.exception.ConstraintViolationException;

public class KitchenModel {
	private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("kitchen");

	public KitchenModel() {
	}
	
	public void addProduct(String name) throws SQLIntegrityConstraintViolationException{
		EntityManager em = emf.createEntityManager();
		EntityTransaction transaction = em.getTransaction();
		try {
			transaction.begin();
			Product product = new Product(name.toLowerCase());
			em.persist(product);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
				throw new SQLIntegrityConstraintViolationException();
			}
			e.printStackTrace();
		} finally {
			em.close();
		}
	}

	public Product getProduct(int id) {
		EntityManager em = emf.createEntityManager();
		Product product = em.find(Product.class, id);
		em.close();
		return product == null ? new Product() : product;
	}

	public void delProduct(int id) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		Product product = em.find(Product.class, id);
		try {
			em.remove(product);
		} catch (Exception e) {
			e.printStackTrace();
		}
		transaction.commit();
		em.close();
	}

	public List<Product> getProducts()
	{
		EntityManager em = emf.createEntityManager();
		TypedQuery<Product> query = em.createQuery("SELECT p FROM Product p WHERE p.Id IS NOT NULL",Product.class);
		List<Product> result = query.getResultList();
		em.close();
		return result;		
	}
	
	public void delAllProducts() {
		for(Product p : getProducts()) {
			delProduct(p.getId());
		}
	}
}
