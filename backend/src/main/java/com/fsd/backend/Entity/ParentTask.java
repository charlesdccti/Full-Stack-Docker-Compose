/*
 * 
 */
package com.fsd.backend.Entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.persistence.CascadeType;



// TODO: Auto-generated Javadoc
/**
 * The Class ParentTask.
 */
/**
 * @author paiprasadm
 *
 */
@Entity
public class ParentTask {

	/** The parent task id. */
	@Id
	@GeneratedValue
	@NotNull
	private long parentTaskId;
	
	/** The parent task. */
	@NotNull
	private String parentTask;
	
	/**
	 * Instantiates a new parent task.
	 */
	public ParentTask() {
	}


	/**
	 * Instantiates a new parent task.
	 *
	 * @param parentTaskId
	 *            the parent task id
	 * @param parentTask
	 *            the parent task
	 */
	public ParentTask(@NotNull long parentTaskId, @NotNull String parentTask) {
		super();
		this.parentTaskId = parentTaskId;
		this.parentTask = parentTask;
	}
	
	
	/**
	 * Gets the parent task id.
	 *
	 * @return the parent task id
	 */
	public long getParentTaskId() {
		return parentTaskId;
	}
	
	
	/**
	 * Sets the parent task id.
	 *
	 * @param parentTaskId
	 *            the new parent task id
	 */
	public void setParentTaskId(long parentTaskId) {
		this.parentTaskId = parentTaskId;
	}
	
	
	/**
	 * Gets the parent task.
	 *
	 * @return the parent task
	 */
	public String getParentTask() {
		return parentTask;
	}
	
	
	/**
	 * Sets the parent task.
	 *
	 * @param parentTask
	 *            the new parent task
	 */
	public void setParentTask(String parentTask) {
		this.parentTask = parentTask;
	}
	

	/**
	 * To string.
	 *
	 * @return the string
	 */
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ParentTask [parentTaskId=" + parentTaskId + ", parentTask=" + parentTask +"]";
	}
	


	
}
