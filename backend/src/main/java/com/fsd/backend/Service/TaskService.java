package com.fsd.backend.Service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.fsd.backend.Entity.Task;
import com.fsd.backend.Repository.TaskRepository;
import com.google.common.collect.Lists;

// TODO: Auto-generated Javadoc
/**
 * The Class TaskService.
 */
/**
 * @author paiprasadm
 *
 */
@Service
@Transactional
public class TaskService {

	/** The log. */
	private final Logger log = LoggerFactory.getLogger(TaskService.class);
	
    /** The task repo. */
    private final TaskRepository taskRepo;
	
	/**
	 * Instantiates a new task service.
	 *
	 * @param taskRepo the task repo
	 */
	public TaskService(TaskRepository taskRepo) {
		this.taskRepo = taskRepo;
	}
	
	/**
	 * Adds the task.
	 *
	 * @param task the task
	 * @return the task
	 */
	public Task addTask(Task task) {
		
		log.debug("===========================================");
		log.debug("Inside Class Name: Task Service ");
		log.debug("Inside Method Name: addTask");
		log.debug("===========================================");
		Task i = taskRepo.save(task);
		return i;
	}
	
	/**
	 * Find task by id.
	 *
	 * @param Id the id
	 * @return the task
	 */
	public Task findTaskById(Long Id) {
		
		log.debug("===========================================");
		log.debug("Inside Class Name: Task Service ");
		log.debug("Inside Method Name: findTaskById ");
		log.debug("===========================================");
		Optional<Task> taskIfExist = taskRepo.findById(Id);
		if(taskIfExist != null) 
			return (Task) taskIfExist.get();
			else
			return null;
	}

	/**
	 * Gets the all task.
	 *
	 * @return the all task
	 */
	public List<Task> getAllTask()
	{
		log.debug("===========================================");
		log.debug("Inside Class Name: Task Service ");
		log.debug("Inside Method Name: getAllTasks ");
		log.debug("===========================================");
		Iterable<Task> task = taskRepo.findAll();
		List<Task> taskList = Lists.newArrayList(task);
		return taskList;
	}
	

	/**
	 * Delete task.
	 *
	 * @param task the task
	 * @return the task
	 */
	public Task deleteTask(Task task) {
		log.debug("===========================================");
		log.debug("Inside Class Name: Task Service ");
		log.debug("Inside Method Name: deleteTask ");
		log.debug("===========================================");
		task.setProject(null);
		task.setUser(null);
		taskRepo.delete(task);
		return task;
	}

	
}
