package main;

import javax.persistence.*;

public class KitchenModel {
	private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("kitchen");

	public KitchenModel() {

	}
	
	public void addProduct(String name) {
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
		Product product = em.find(Product.class, id);
		try {
			em.remove(product);
		} catch (Exception e) {
			e.printStackTrace();
		}
		em.close();
	}
}
