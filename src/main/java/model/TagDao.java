package model;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

/*
 * Class implements DAO (data access object) method to CRUD POJO to database
 */
public class TagDao {
	private Tag dao;
	
	public Tag getDao() {
		return dao;
	}
	
	public void setDao(Tag dao) {
		this.dao = dao;
	}
	
	private EntityManager entityManager;
	
	public void init(){
		entityManager = Persistence.createEntityManagerFactory("JPO_harjoitustyo").createEntityManager();
	}
	
	public List<Tag> getDaos(){
		entityManager.getTransaction().begin();
		Query query = entityManager.createNamedQuery("Tag.findAll");
		//Query query = entityManager.createQuery("from Product c", Product.class);
		List<Tag> result = query.getResultList(); 
		entityManager.getTransaction().commit();
		return result;
	}
	
	public int persist(Tag dao){
		entityManager.getTransaction().begin();
		entityManager.persist(dao);
		entityManager.getTransaction().commit();
		return dao.getTagId();
	}
	
	public void initialize(int daoNumber){
		dao = entityManager.find(Tag.class, daoNumber);
		  if(dao == null)throw new IllegalStateException
		   ("Dao number ("+daoNumber+") not found");		
	}
	
	public void update(Tag dao){
		//just checking that the dao really has is
		if(dao.getTagId()>0){
			//get the actual entity from database to a dao-named attribute
			initialize(dao.getTagId());
			//start database transaction
			entityManager.getTransaction().begin();
			dao.setName(dao.getName());
			entityManager.merge(dao);
			entityManager.getTransaction().commit();
		}
	}
	
	public void delete(){
		entityManager.getTransaction().begin();
		entityManager.remove(dao);
		entityManager.getTransaction().commit();
	}
	
	public void destroy(){
		entityManager.close();
	}
}
