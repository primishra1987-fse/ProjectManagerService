package iiht.sba.projectmanager.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table (name = "users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id", nullable = false, length=10)
	private Long userId;
	
	@Column(name = "first_name", nullable=false, length=50)
	private String firstName;
	
	@Column(name = "last_name", nullable=false, length=50)
	private String lastName;
	
	@Column(name = "employee_id", nullable=false, length=10)
	private String employeeId;
	
	
	/*
	 * @ManyToOne (fetch = FetchType.LAZY)
	 * 
	 * @JoinColumn (name = "projectId") private Project project;
	 * 
	 * @ManyToOne (fetch = FetchType.LAZY)
	 * 
	 * @JoinColumn( name = "taskId") private Task task;
	 */
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	/*
	 * public Project getProject() { return project; }
	 * 
	 * public void setProject(Project project) { this.project = project; }
	 */
	
}