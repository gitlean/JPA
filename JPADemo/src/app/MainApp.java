package app;

import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.hmk.bean.Person;

final class JpaUtil {
	private static EntityManagerFactory em;
	static {
		em = Persistence.createEntityManagerFactory("mysqlJPA");
	}

	public static EntityManager getEntityManager() {
		return em.createEntityManager();
	}
}

public class MainApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		testJPLUpdate();
		System.out.println("<<<<<end>>>>>");

	}

	static void testJPLQuery() {
		EntityManager em = null;
		EntityTransaction tx = null;
		try {
			em = JpaUtil.getEntityManager();
			String jpl = "select p from Person p";
			Query q = em.createQuery(jpl);
			List<Person> all = q.getResultList();
			Iterator<Person> it = all.iterator();
			while (it.hasNext()) {
				Person p = it.next();
				System.out.println(p);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (em != null) {
				em.close();
			}
		}

	}

	static void testJPLUpdate() {
		EntityManager em = null;
		EntityTransaction tx = null;
		try {
			em = JpaUtil.getEntityManager();
			tx = em.getTransaction();
			tx.begin();
			String jpl = "update Person u set name=:name where id=:id";
			Query q = em.createQuery(jpl);
			q.setParameter("name", "加菲猫");
			q.setParameter("id", 5);
			q.executeUpdate();
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	static void insert() {
		EntityManagerFactory factory = Persistence
				.createEntityManagerFactory("mysqlJPA");
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Person person = new Person(); // person为new状态
		person.setName("zhang san");
		em.persist(person); // 持久化实体
		em.getTransaction().commit();
		em.close();
		factory.close();
	}

	static void call() {
		EntityManagerFactory factory = Persistence
				.createEntityManagerFactory("mysqlJPA");
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Person person = em.find(Person.class, 2);
		person.setName("hmk");
		// System.out.println(person);
		em.getTransaction().commit();
		em.close();
		factory.close();
	}

}
