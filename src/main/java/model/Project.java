package model;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the project database table.
 * 
 */
@Entity @Table(name = "project")
@NamedQuery(name="Project.findAll", query="SELECT p FROM Project p")
public class Project implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="PROJECT_PROJECTID_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PROJECT_PROJECTID_GENERATOR")
	@Column(name="project_id")
	private int projectId;

	private String company;

	private String description;

	private String email;

	private int open;

	public Project() {
	}

	public int getProjectId() {
		return this.projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public String getCompany() {
		return this.company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getOpen() {
		return this.open;
	}

	public void setOpen(int open) {
		this.open = open;
	}

}