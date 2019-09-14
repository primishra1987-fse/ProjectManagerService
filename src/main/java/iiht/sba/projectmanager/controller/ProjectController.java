package iiht.sba.projectmanager.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import iiht.sba.projectmanager.entity.Project;
import iiht.sba.projectmanager.entity.User;
import iiht.sba.projectmanager.service.ProjectService;

@RestController
@RequestMapping("/project")
public class ProjectController {
	private static final Logger logger = LoggerFactory.getLogger(ProjectController.class);
	@Autowired
	private ProjectService projectService;

	@CrossOrigin(origins = "*", maxAge = 3600)
	@GetMapping(value = "/allProjects", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<Project>> getAllProjects() {
		
		logger.info("<-- Inside getAllProjects -->");
		List<Project> projects = this.projectService.getAllProjects();
		
		System.out.println("Project task size :"+ projects.size());
		if (projects == null || projects.isEmpty()) {
			return new ResponseEntity<List<Project>>(projects, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Project>>(projects, HttpStatus.OK);
		
		
		
	}
	
	@CrossOrigin(origins = "*", maxAge = 3600)
	@PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Project> saveOrUpdate(@RequestBody Project project) {
		return new ResponseEntity<Project>(this.projectService.addProject(project), HttpStatus.OK);
	}
	
	@CrossOrigin(origins = "*", maxAge = 3600)
	@DeleteMapping(value="/suspend/{projectId}",  produces = MediaType.APPLICATION_JSON_VALUE)
			public ResponseEntity<Long> deleteUser(@PathVariable("projectId") Long projectId){
		try {
			logger.info("Controller - suspend call for project.");
			projectService.suspendProject(projectId);
		}
		catch (Exception e) {
			logger.error("Controller - suspendProject call returned error : " +e.getMessage());
			e.printStackTrace();
		}
		return new ResponseEntity<Long>(projectId, HttpStatus.OK);
			
	}
}

