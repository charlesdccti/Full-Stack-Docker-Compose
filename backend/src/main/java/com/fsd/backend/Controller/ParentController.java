package com.fsd.backend.Controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fsd.backend.Entity.ParentTask;
import com.fsd.backend.Entity.Project;
import com.fsd.backend.Service.ParentService;
import com.fsd.backend.Service.ProjectService;

// TODO: Auto-generated Javadoc
/**
 * The Class ParentController.
 */
/**
 * @author paiprasadm
 *
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/taskManager/parentAction")
public class ParentController {
	
/** The log. */
private final Logger log = LoggerFactory.getLogger(ProjectController.class);
	
	/** The parent service. */
	@Autowired
	private ParentService parentService;
	
	/**
	 * Adds the parent.
	 *
	 * @param parentTask the parent task
	 * @return the parent task
	 * @throws Exception the exception
	 */
	@PostMapping("/addParent")
	public ParentTask addParent(@RequestBody ParentTask parentTask) throws Exception
	{
		
		log.debug("===========================================");
		log.debug("Inside Class Name: Parent Controller ");
		log.debug("Inside Method Name: addParent ");
		log.debug("Project Details to be added:" + parentTask.toString());
		log.debug("===========================================");
		parentService.addParentTask(parentTask);
		return parentTask;
	}
	
	/**
	 * Gets the all parent.
	 *
	 * @return the all parent
	 * @throws Exception the exception
	 */
	@RequestMapping("/getAllParent")
	public List<ParentTask> getAllParent() throws Exception
	{
		List<ParentTask> parentList = null;
	
		log.debug("===========================================");
		log.debug("Inside Class Name: Project Controller ");
		log.debug("Inside Method Name: getAllProject ");
		log.debug("===========================================");
		parentList=parentService.getAllParent();
		return  parentList;
	}

}
