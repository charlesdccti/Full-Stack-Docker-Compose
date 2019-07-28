package com.fsd.backend.Entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

// TODO: Auto-generated Javadoc
/**
 * The Class User.
 */
/**
 * @author paiprasadm
 *
 */
@Entity
public class User implements Serializable {
	
	/** The user id. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@NotNull
	private long userId;

	/** The first name. */
	@NotNull
	private String firstName;
	
	/** The last name. */
	@NotNull
	private String lastName;
	
	/** The employee id. */
	@NotNull
	private String employeeId;
		
	/** The task. */
	@OneToMany
	@JoinTable(name="TASK_ID",joinColumns=@JoinColumn(name="USER_ID"),
	           inverseJoinColumns = @JoinColumn(name="TASK_ID"))
	private List<Task> task = new ArrayList();

	/** The project. */
	@OneToMany
	@JoinTable(name="PROJECT_ID",joinColumns=@JoinColumn(name="USER_ID"),
	           inverseJoinColumns = @JoinColumn(name="PROJECT_ID"))
	private List<Project> project = new ArrayList();

	/**
	 * Instantiates a new user.
	 */
	public User() {
	}

	/**
	 * Instantiates a new user.
	 *
	 * @param userId the user id
	 * @param firstName the first name
	 * @param lastName the last name
	 * @param employeeId the employee id
	 * @param task the task
	 * @param project the project
	 */
	public User(@NotNull long userId, @NotNull String firstName, @NotNull String lastName, @NotNull String employeeId,
			List<Task> task, List<Project> project) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.employeeId = employeeId;
		this.task = task;
		this.project = project;
	}

	/**
	 * Gets the user id.
	 *
	 * @return the user id
	 */
	public long getUserId() {
		return userId;
	}

	/**
	 * Sets the user id.
	 *
	 * @param userId the new user id
	 */
	public void setUserId(long userId) {
		this.userId = userId;
	}

	/**
	 * Gets the first name.
	 *
	 * @return the first name
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Sets the first name.
	 *
	 * @param firstName the new first name
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Gets the last name.
	 *
	 * @return the last name
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets the last name.
	 *
	 * @param lastName the new last name
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Gets the employee id.
	 *
	 * @return the employee id
	 */
	public String getEmployeeId() {
		return employeeId;
	}

	/**
	 * Sets the employee id.
	 *
	 * @param employeeId the new employee id
	 */
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	/**
	 * Gets the task.
	 *
	 * @return the task
	 */
	public List<Task> getTask() {
		return task;
	}

	/**
	 * Sets the task.
	 *
	 * @param task the new task
	 */
	public void setTask(List<Task> task) {
		this.task = task;
	}

	/**
	 * Gets the project.
	 *
	 * @return the project
	 */
	public List<Project> getProject() {
		return project;
	}

	/**
	 * Sets the project.
	 *
	 * @param project the new project
	 */
	public void setProject(List<Project> project) {
		this.project = project;
	}
	
}
