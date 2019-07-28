/*
 * 
 */
package com.fsd.backend.Entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;


// TODO: Auto-generated Javadoc
/**
 * The Class Task.
 */
/**
 * @author paiprasadm
 *
 */
@Entity 
public class Task implements Serializable{

	/** The task id. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@NotNull
	private long taskId;
	
	/** The task. */
	@NotNull
	private String task;
	
	/** The priority. */
	@NotNull
	private int priority;
	
	/** The start date. */
	@DateTimeFormat(pattern = "yyyy-MM-dd" )
	@NotNull
	private LocalDate startDate;
	
	/** The end date. */
	@DateTimeFormat(pattern = "yyyy-MM-dd" )
	@NotNull
	private LocalDate endDate;
	
	
	/** The parent task. */
	@OneToOne(cascade = CascadeType.ALL, optional = false)
	private ParentTask parentTask;

	/** The project. */
	@ManyToOne(cascade = CascadeType.MERGE, optional = false)
	private Project project;
	
	/** The user. */
	@ManyToOne(cascade = CascadeType.MERGE, optional = false)	
	private User user;
	
	/** The status. */
	@NotNull
	private String status;

	/**
	 * Instantiates a new task.
	 */
	public Task() {
	}

	/**
	 * Instantiates a new task.
	 *
	 * @param taskId the task id
	 * @param task the task
	 * @param priority the priority
	 * @param startDate the start date
	 * @param endDate the end date
	 * @param parentTask the parent task
	 * @param project the project
	 * @param user the user
	 * @param status the status
	 */
	public Task(@NotNull long taskId, @NotNull String task, @NotNull int priority, @NotNull LocalDate startDate,
			@NotNull LocalDate endDate, ParentTask parentTask, Project project,User user, @NotNull String status) {
		super();
		this.taskId = taskId;
		this.task = task;
		this.priority = priority;
		this.startDate = startDate;
		this.endDate = endDate;
		this.parentTask = parentTask;
		this.project = project;
		this.status = status;
		this.user = user;
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
	 * Gets the project.
	 *
	 * @return the project
	 */
	public Project getProject() {
		return project;
	}


	/**
	 * Sets the project.
	 *
	 * @param project the new project
	 */
	public void setProject(Project project) {
		this.project = project;
	}


	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}


	/**
	 * Sets the status.
	 *
	 * @param status the new status
	 */
	public void setStatus(String status) {
		this.status = status;
	}


	/**
	 * Gets the task id.
	 *
	 * @return the task id
	 */
	public long getTaskId() {
		return taskId;
	}


	/**
	 * Sets the task id.
	 *
	 * @param taskId
	 *            the new task id
	 */
	public void setTaskId(long taskId) {
		this.taskId = taskId;
	}


	/**
	 * Gets the task.
	 *
	 * @return the task
	 */
	public String getTask() {
		return task;
	}


	/**
	 * Sets the task.
	 *
	 * @param task
	 *            the new task
	 */
	public void setTask(String task) {
		this.task = task;
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
	 * @param priority
	 *            the new priority
	 */
	public void setPriority(int priority) {
		this.priority = priority;
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
	 * @param startDate
	 *            the new start date
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
	 * @param endDate
	 *            the new end date
	 */
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}


	/**
	 * Gets the parent task.
	 *
	 * @return the parent task
	 */
	public ParentTask getParentTask() {
		return parentTask;
	}


	/**
	 * Sets the parent task.
	 *
	 * @param parentTask
	 *            the new parent task
	 */
	public void setParentTask(ParentTask parentTask) {
		this.parentTask = parentTask;
	}


	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "Task [taskId=" + taskId + ", task=" + task + ", priority=" + priority + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", parentTask=" + parentTask + ", project=" + project + ", status=" + status
				+",user="+ user +"]";
	}
	
	
	
}
