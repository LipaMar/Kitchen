package main;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Set;

import javax.persistence.*;
import javax.swing.*;

import Entities.*;

public class Repository {
	private final EntityManagerFactory emf;

	public Repository() {
		EntityManagerFactory emf = null;
		try {
			emf = Persistence.createEntityManagerFactory("kitchen");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(new JFrame(), "Błąd połączenia z bazą danych", "Błąd",
					JOptionPane.ERROR_MESSAGE);

			e.printStackTrace();
		}
		this.emf = emf;

	}

	// ------Product--------


	public void addProduct(String name,Unit unit) throws SQLIntegrityConstraintViolationException {
		EntityManager em = emf.createEntityManager();
		EntityTransaction transaction = em.getTransaction();
		try {
			transaction.begin();
			Product product = new Product(name,unit);
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

	public List<Product> getAllProducts() {
		EntityManager em = emf.createEntityManager();
		TypedQuery<Product> query = em.createQuery("SELECT p FROM Product p WHERE p.id IS NOT NULL", Product.class);
		List<Product> result = query.getResultList();
		em.close();
		return result;
	}

	public void delAllProducts() {
		for (Product p : getAllProducts()) {
			delProduct(p.getId());
		}
	}

	// ------Recipe--------
	public void addRecipe(String title, String steps, Set<Ingredient> ingredients) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction transaction = em.getTransaction();
		try {
			transaction.begin();
			Recipe recipe = new Recipe(title,steps,ingredients);
			em.persist(recipe);
			transaction.commit();
		}catch (Exception e) {
			if(transaction!=null)
				transaction.rollback();
		}finally {
			em.close();
		}
	}

	/*public Recipe getRecipe(int Id) {
		return null;
	}

	public void delRecipe(int Id) {

	}*/

	public List<Recipe> getAllRecipes() {
		EntityManager em = emf.createEntityManager();
		TypedQuery<Recipe> query = em.createQuery("FROM Recipe r WHERE r.id IS NOT NULL", Recipe.class);
		List<Recipe> result = query.getResultList();
		return result;
	}

	// ------Unit--------
	public List<Unit> getAllUnits() {
		EntityManager em = emf.createEntityManager();
		TypedQuery<Unit> query = em.createQuery("FROM Unit u WHERE u.id IS NOT NULL", Unit.class);
		List<Unit> result = query.getResultList();
		return result;
	}

	// ------Ingredient--------
}
