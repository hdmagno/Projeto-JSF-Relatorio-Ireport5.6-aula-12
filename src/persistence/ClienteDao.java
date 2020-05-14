package persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import entity.Cliente;

public class ClienteDao {
	
	static EntityManagerFactory factory = Persistence.createEntityManagerFactory("aula-12");
	
	public static EntityManager getEntityManager() {
		return factory.createEntityManager();
	}
	
	EntityManager manager;
	TypedQuery<Cliente> query;
	
	public void create(Cliente c)throws Exception {
		manager = getEntityManager();
		manager.getTransaction().begin();
		manager.persist(c);
		manager.getTransaction().commit();
	}

	public void update(Cliente c)throws Exception {
		manager = getEntityManager();
		manager.getTransaction().begin();
		manager.merge(c);
		manager.getTransaction().commit();
	}
	
	public void delete(Cliente c)throws Exception {
		manager = getEntityManager();
		manager.getTransaction().begin();
		manager.remove(c);
		manager.getTransaction().commit();
	}
	
	public List<Cliente> findAll()throws Exception {
		manager = getEntityManager();
		List<Cliente> clientes = manager.createQuery("select c from Cliente c", Cliente.class).getResultList();
		return clientes;
	}
	
	public Cliente findByCode(Long cod)throws Exception {
		manager = getEntityManager();
		Cliente cliente = manager.find(Cliente.class, cod);
		return cliente;
	}
	
	public static void main(String[] args) {
		
		try {
			Cliente hilton = new Cliente(null, "Hilton", "hilton@gmail.com");
			Cliente amanda = new Cliente(null, "Amanda", "amanda@gmail.com");
			ClienteDao dao = new ClienteDao();
			dao.create(hilton);
			dao.create(amanda);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
