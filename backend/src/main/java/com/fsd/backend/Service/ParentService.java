package com.fsd.backend.Service;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.fsd.backend.Entity.ParentTask;
import com.fsd.backend.Entity.Project;
import com.fsd.backend.Repository.ParentTaskRepo;
import com.google.common.collect.Lists;


// TODO: Auto-generated Javadoc
/**
 * The Class ParentService.
 *
 * @author paiprasadm
 */
@Service
@Transactional
public class ParentService {

	/** The log. */
	private final Logger log = LoggerFactory.getLogger(ParentService.class);
	
    /** The parent repo. */
    private final ParentTaskRepo parentRepo;
	
	/**
	 * Instantiates a new parent service.
	 *
	 * @param parentRepo the parent repo
	 */
	public ParentService(ParentTaskRepo parentRepo) {
		this.parentRepo = parentRepo;
	}

   /**
    * Adds the parent task.
    *
    * @param parent the parent
    * @return the parent task
    */
   public ParentTask addParentTask(ParentTask parent) {
		
		log.debug("===========================================");
		log.debug("Inside Class Name: Parent Service ");
		log.debug("Inside Method Name: addParentTask");
		log.debug("===========================================");
		ParentTask i = parentRepo.save(parent);
		return i;
	}
   
   
   /**
    * Gets the all parent.
    *
    * @return the all parent
    */
   public List<ParentTask> getAllParent()
	{
		log.debug("===========================================");
		log.debug("Inside Class Name: Parent Service ");
		log.debug("Inside Method Name: getAllParents ");
		log.debug("===========================================");
		Iterable<ParentTask> parentTask = parentRepo.findAll();
		List<ParentTask> parentTaskList = Lists.newArrayList(parentTask);
		return parentTaskList;
	}
   
}
