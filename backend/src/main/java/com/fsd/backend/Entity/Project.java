package com.fsd.backend.Entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.springframework.format.annotation.DateTimeFormat;

// TODO: Auto-generated Javadoc
/**
 * The Class Project.
 **
 * @author Prasad Pai M
 *
 */
@Entity
public class Project implements Serializable {
	
	/** The project id. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long projectId;
	
	/** The project. */
	private String project;
	
	/** The start date. */
	@DateTimeFormat(pattern = "yyyy-MM-dd" )
	private LocalDate startDate;
	
	/** The end date. */
	@DateTimeFormat(pattern = "yyyy-MM-dd" )
	private LocalDate endDate;
	
	/** The priority. */
	private int priority;

	/** The task. */
	@OneToMany
	@JoinTable(name="TASK_ID",joinColumns=@JoinColumn(name="PROJECT_ID"),
	           inverseJoinColumns = @JoinColumn(name="TASK_ID"))
	private List<Task> task = new ArrayList();
	
	/** The user. */
	@OneToOne
	private User user;
	
	/**
	 * Instantiates a new project.
	 */
	public Project() {
	}


	/**
	 * Instantiates a new project.
	 *
	 * @param projectId the project id
	 * @param project the project
	 * @param startDate the start date
	 * @param endDate the end date
	 * @param priority the priority
	 * @param task the task
	 * @param user the user
	 */
	public Project(long projectId, String project, LocalDate startDate, LocalDate endDate, int priority, List<Task> task,
			User user) {
		this.projectId = projectId;
		this.project = project;
		this.startDate = startDate;
		this.endDate = endDate;
		this.priority = priority;
		this.task = task;
		this.user = user;
	}


	/**
	 * Gets the project id.
	 *
	 * @return the project id
	 */
	public long getProjectId() {
		return projectId;
	}



	/**
	 * Sets the project id.
	 *
	 * @param projectId the new project id
	 */
	public void setProjectId(long projectId) {
		this.projectId = projectId;
	}



	/**
	 * Gets the project.
	 *
	 * @return the project
	 */
	public String getProject() {
		return project;
	}



	/**
	 * Sets the project.
	 *
	 * @param project the new project
	 */
	public void setProject(String project) {
		this.project = project;
	}



	/**
	 * Gets the start date.
	 *
	 * @return the start date
	 */
	public LocalDate getStartDate() {
		return startDate;
	}



	/**
	 * Sets the start date.
	 *
	 * @param startDate the new start date
	 */
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}



	/**
	 * Gets the end date.
	 *
	 * @return the end date
	 */
	public LocalDate getEndDate() {
		return endDate;
	}



	/**
	 * Sets the end date.
	 *
	 * @param endDate the new end date
	 */
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}



	/**
	 * Gets the priority.
	 *
	 * @return the priority
	 */
	public int getPriority() {
		return priority;
	}



	/**
	 * Sets the priority.
	 *
	 * @param priority the new priority
	 */
	public void setPriority(int priority) {
		this.priority = priority;
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
	 * Gets the user.
	 *
	 * @return the user
	 */
	public User getUser() {
		return user;
	}



	/**
	 * Sets the user.
	 *
	 * @param user the new user
	 */
	public void setUser(User user) {
		this.user = user;
	}



	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "Project [projectId=" + projectId + ", project=" + project + ", startDate=" + startDate + ", endDate="
				+ endDate + ", priority=" + priority + ", task=" + task + ",user="+ user+"]";
	}
	

}
