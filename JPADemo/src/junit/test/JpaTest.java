package junit.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.BeforeClass;
import org.junit.Test;

import com.hmk.bean.Person;

public class JpaTest {

//	@BeforeClass
//	public static void setUpBeforeClass() throws Exception {
//	}

	@Test
	public void createTable() {
		// ������֤���ɱ��Ƿ���ȷ
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("mysqlJPA");
		factory.close();
	}

	@Test
	public void save() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("mysqlJPA");
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Person person = new Person(); // personΪnew״̬
		person.setName("zhang san");
		em.persist(person); // �־û�ʵ��
		em.getTransaction().commit();
		em.close();
		factory.close();
	}
	// new ���йܡ��ѹܡ�ɾ��

	@Test
	public void update() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("mysqlJPA");
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Person person = em.find(Person.class, 1);
		person.setName("hmk"); // personΪ�й�״̬
		em.getTransaction().commit();
		em.close();
		factory.close();
	}

	@Test
	public void update2() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("mysqlJPA");
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Person person = em.find(Person.class, 1);
		em.clear(); // ��ʵ��������е�����ʵ���Ϊ�ѹ�״̬
		person.setName("hmk2");
		em.merge(person); // ���ѹ�״̬��Ϊ�й�״̬,merge�����Զ�ѡ��insert or update ����
		em.getTransaction().commit();
		em.close();
		factory.close();
	}

	@Test
	public void remove() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("mysqlJPA");
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Person person = em.find(Person.class, 1);
		em.remove(person); // ɾ��ʵ��
		em.getTransaction().commit();
		em.close();
		factory.close();
	}

	@Test
	public void find() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("mysqlJPA");
		EntityManager em = factory.createEntityManager();
		Person person = em.find(Person.class, 2); // ������hibernate��get����,û�ҵ�����ʱ������null
		System.out.println(person.getName());
		em.close();
		factory.close();
	}

	@Test
	public void find2() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("mysqlJPA");
		EntityManager em = factory.createEntityManager();
		Person person = em.getReference(Person.class, 2); // ������hibernate��load����,�ӳټ���.û��Ӧ����ʱ������쳣
		System.out.println(person.getName()); // ��������ʱ�Ų�������
		em.close();
		factory.close();
	}
}
