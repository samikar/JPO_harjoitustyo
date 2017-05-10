package controller;

import java.io.FileReader;
import java.util.List;

import java.util.Scanner;

import model.Project;
import model.ProjectDao;
import model.Student;
import model.StudentDao;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProjectController {
    @RequestMapping("/test")
    public String hello(@RequestParam(value="name", defaultValue="World") String name) {
        return "{\"id\":\"hello\"}";
    }
    
    /* Listaa projektit JSONina*/
    @RequestMapping("/projects")
    public List<Project> projects() {
    	ProjectDao dao = new ProjectDao();
		dao.init();
		return dao.getDaos();
	}
    
    /* Projektinlisäyslomake*/
    @RequestMapping("/addproject")
    public String addProject() {
		StringBuffer sb = new StringBuffer();
		try{
			Scanner in = new Scanner(new FileReader("AddProject.html"));
			while(in.hasNext()){
				String rivi =in.nextLine();
				sb.append(rivi);
			}
			return sb.toString();	
		}
		catch(Exception e){
			return e.toString();
		}    	
    }
    
    /* Lisää projektin kantaan */
    @RequestMapping("/insertproject")
    public Project insertProject(@RequestParam(value="company", defaultValue="0") String company, 
    							@RequestParam(value="email", defaultValue="0") String email, 
    							@RequestParam(value="desc", defaultValue="0") String description, 
    							@RequestParam(value="open", defaultValue="0") String open) {
    	ProjectDao dao = new ProjectDao();
    	dao.init();
    	Project project = new Project();
    	project.setCompany(company);
    	project.setEmail(email);
    	project.setDescription(description);
    	project.setOpen(Integer.parseInt(open));
    	dao.persist(project);
    	return project;
    }
    
    /* Studentinlisäyslomake */
    @RequestMapping("/addstudent")
    public String addStudent() {
		StringBuffer sb = new StringBuffer();
		try{
			Scanner in = new Scanner(new FileReader("AddStudent.html"));
			while(in.hasNext()){
				String rivi =in.nextLine();
				sb.append(rivi);
			}
			return sb.toString();	
		}
		catch(Exception e){
			return e.toString();
		}    	
    }
    
    /* Lisää studentin kantaan */
    @RequestMapping("/insertstudent")
    public Student insertStudent(@RequestParam(value="name", defaultValue="0") String name, 
    							@RequestParam(value="email", defaultValue="0") String email, 
    							@RequestParam(value="mobile", defaultValue="0") String mobile, 
    							@RequestParam(value="credits", defaultValue="0") String credits, 
    							@RequestParam(value="grade", defaultValue="0") String grade, 
    							@RequestParam(value="available", defaultValue="0") String available) {
    	StudentDao dao = new StudentDao();
    	dao.init();
    	Student student = new Student();
    	student.setName(name);
    	student.setEmail(email);
    	student.setMobile(mobile);
    	student.setCredits(Integer.parseInt(credits));
    	student.setGrade(grade);
    	student.setAvailable(Integer.parseInt(available));
    	dao.persist(student);
    	return student;
    }
}