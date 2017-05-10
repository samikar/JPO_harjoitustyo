package model;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

/*
 * Class implements DAO (data access object) method to CRUD POJO to database
 */
public class StudentDao {
	private Student dao;
	
	public Student getDao() {
		return dao;
	}
	
	public void setDao(Student dao) {
		this.dao = dao;
	}
	
	private EntityManager entityManager;
	
	public void init(){
		entityManager = Persistence.createEntityManagerFactory("JPO_harjoitustyo").createEntityManager();
	}
	
	public List<Student> getDaos(){
		entityManager.getTransaction().begin();
		Query query = entityManager.createNamedQuery("Student.findAll");
		//Query query = entityManager.createQuery("from Product c", Product.class);
		List<Student> result = query.getResultList(); 
		entityManager.getTransaction().commit();
		return result;
	}
	
	public int persist(Student dao){
		entityManager.getTransaction().begin();
		entityManager.persist(dao);
		entityManager.getTransaction().commit();
		return dao.getStudentId();
	}
	
	public void initialize(int daoNumber){
		dao = entityManager.find(Student.class, daoNumber);
		  if(dao == null)throw new IllegalStateException
		   ("Dao number ("+daoNumber+") not found");		
	}
	
	public void update(Student dao){
		//just checking that the dao really has is
		if(dao.getStudentId()>0){
			//get the actual entity from database to a dao-named attribute
			initialize(dao.getStudentId());
			//start database transaction
			entityManager.getTransaction().begin();
			dao.setName(dao.getName());
			dao.setEmail(dao.getEmail());
			dao.setMobile(dao.getMobile());
			dao.setCredits(dao.getCredits());
			dao.setGrade(dao.getGrade());
			dao.setAvailable(dao.getAvailable());
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
