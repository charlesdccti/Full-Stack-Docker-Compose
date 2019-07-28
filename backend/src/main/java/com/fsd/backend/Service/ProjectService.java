package com.fsd.backend.Service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.fsd.backend.Entity.Project;
import com.fsd.backend.Repository.ProjectRepository;
import com.google.common.collect.Lists;

// TODO: Auto-generated Javadoc
/**
 * The Class ProjectService.
 */
/**
 * @author paiprasadm
 *
 */
@Service
@Transactional
public class ProjectService {
	
/** The log. */
private final Logger log = LoggerFactory.getLogger(ProjectService.class);
	
	/** The project repo. */
	private final ProjectRepository projectRepo;
	
	/**
	 * Instantiates a new project service.
	 *
	 * @param projectRepo the project repo
	 */
	public ProjectService(ProjectRepository projectRepo) {
		this.projectRepo = projectRepo;
	}

	/**
	 * Adds the project.
	 *
	 * @param project the project
	 * @return the project
	 */
	public Project addProject(Project project) {
		
		log.debug("===========================================");
		log.debug("Inside Class Name: Project Service ");
		log.debug("Inside Method Name: addProject");
		log.debug("===========================================");
		Project i = projectRepo.save(project);
		return i;
	}
	
	/**
	 * Find project by id.
	 *
	 * @param Id the id
	 * @return the project
	 */
	public Project findProjectById(Long Id) {
		
		log.debug("===========================================");
		log.debug("Inside Class Name: Project Service ");
		log.debug("Inside Method Name: findProjectById ");
		log.debug("===========================================");
		Optional<Project> projectIfExist = projectRepo.findById(Id);
		if(projectIfExist != null) 
			return (Project) projectIfExist.get();
			else
			return null;
	}

	/**
	 * Gets the all project.
	 *
	 * @return the all project
	 */
	public List<Project> getAllProject()
	{
		log.debug("===========================================");
		log.debug("Inside Class Name: Project Service ");
		log.debug("Inside Method Name: getAllProjects ");
		log.debug("===========================================");
		Iterable<Project> project = projectRepo.findAll();
		List<Project> projectList = Lists.newArrayList(project);
		return projectList;
	}
	

	/**
	 * Delete project.
	 *
	 * @param project the project
	 * @return the project
	 */
	public Project deleteProject(Project project) {
		log.debug("===========================================");
		log.debug("Inside Class Name: Project Service ");
		log.debug("Inside Method Name: deleteProject ");
		log.debug("===========================================");
		projectRepo.delete(project);
		return project;
	}

}
