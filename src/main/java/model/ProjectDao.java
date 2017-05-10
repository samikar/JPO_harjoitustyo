package model;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

/*
 * Class implements DAO (data access object) method to CRUD POJO to database
 */
public class ProjectDao {
	private Project dao;
	
	public Project getDao() {
		return dao;
	}
	
	public void setDao(Project dao) {
		this.dao = dao;
	}
	
	private EntityManager entityManager;
	
	public void init(){
		entityManager = Persistence.createEntityManagerFactory("JPO_harjoitustyo").createEntityManager();
	}
	
	public List<Project> getDaos(){
		entityManager.getTransaction().begin();
		Query query = entityManager.createNamedQuery("Project.findAll");
		//Query query = entityManager.createQuery("from Product c", Product.class);
		List<Project> result = query.getResultList(); 
		entityManager.getTransaction().commit();
		return result;
	}
	
	public int persist(Project dao){
		entityManager.getTransaction().begin();
		entityManager.persist(dao);
		entityManager.getTransaction().commit();
		return dao.getProjectId();
	}
	
	public void initialize(int daoNumber){
		dao = entityManager.find(Project.class, daoNumber);
		  if(dao == null)throw new IllegalStateException
		   ("Dao number ("+daoNumber+") not found");		
	}
	
	public void update(Project dao){
		//just checking that the dao really has is
		if(dao.getProjectId()>0){
			//get the actual entity from database to a dao-named attribute
			initialize(dao.getProjectId());
			//start database transaction
			entityManager.getTransaction().begin();
			dao.setCompany(dao.getCompany());
			dao.setEmail(dao.getEmail());
			dao.setDescription(dao.getDescription());
			dao.setOpen(dao.getOpen());
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
