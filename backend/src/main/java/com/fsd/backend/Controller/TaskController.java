package com.fsd.backend.Controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fsd.backend.Entity.Task;
import com.fsd.backend.Service.TaskService;

// TODO: Auto-generated Javadoc
/**
 * The Class TaskController.
 */
/**
 * @author paiprasadm
 *
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/taskManager/taskAction")
public class TaskController {

/** The log. */
private final Logger log = LoggerFactory.getLogger(TaskController.class);
	
	/** The task service. */
	@Autowired
	private TaskService taskService;
		
		
	/**
	 * Adds the task.
	 *
	 * @param task the task
	 * @return the task
	 * @throws Exception the exception
	 */
	@PostMapping("/addTask")
	public Task addTask(@RequestBody Task task) throws Exception
	{
		
		log.debug("===========================================");
		log.debug("Inside Class Name: Task Controller ");
		log.debug("Inside Method Name: addTask ");
		log.debug("Task Details to be added:" + task.toString());
		log.debug("===========================================");
		taskService.addTask(task);
		return task;
	}
	

	/**
	 * Gets the all task.
	 *
	 * @return the all task
	 * @throws Exception the exception
	 */
	@RequestMapping("/getAllTask")
	public List<Task> getAllTask() throws Exception
	{
		List<Task> taskList = null;
	
		log.debug("===========================================");
		log.debug("Inside Class Name: Task Controller ");
		log.debug("Inside Method Name: getAllTask ");
		log.debug("===========================================");
		taskList=taskService.getAllTask();
		return  taskList;
	}
	
	
	/**
	 * Delete task.
	 *
	 * @param task the task
	 * @return the task
	 * @throws Exception the exception
	 */
	@RequestMapping("/deleteTask")
	public Task deleteTask(@RequestBody Task task)throws Exception
	{
		Task returned = null;
	
		log.debug("===========================================");
		log.debug("Inside Class Name: Task Controller ");
		log.debug("Inside Method Name: deleteTask ");
		log.debug("===========================================");
		returned=taskService.deleteTask(task);
		return  returned;
	}
	
	
	/**
	 * Gets the task by id.
	 *
	 * @param taskId the task id
	 * @return the task by id
	 * @throws Exception the exception
	 */
	@RequestMapping("/getTaskById/{taskId}")
	public Task getTaskById(@PathVariable(name="taskId")String taskId ) throws Exception
	{
		log.debug("===========================================");
		log.debug("Inside Class Name: Task Controller ");
		log.debug("Inside Method Name: getTaskById ");
		log.debug("Task Id Searched for: " + taskId);
		log.debug("===========================================");
		Task task = null;
		task = taskService.findTaskById(Long.parseLong(taskId));
		return task;
	}

	/**
	 * Update task.
	 *
	 * @param task the task
	 * @return the task
	 * @throws Exception the exception
	 */
	@PostMapping("/updateTask")
	private Task updateTask(@RequestBody Task task) throws Exception{

		log.debug("===========================================");
		log.debug("Inside Class Name: Task Controller ");
		log.debug("Inside Method Name: updateTask ");
		log.debug("===========================================");
		taskService.addTask(task);
		return  task;
	}
}
