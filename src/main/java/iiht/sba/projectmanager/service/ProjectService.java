package iiht.sba.projectmanager.service;

import java.util.List;
import java.util.Set;

import iiht.sba.projectmanager.entity.Project;
import iiht.sba.projectmanager.entity.Task;

public interface ProjectService {
	List<Project> getAllProjects();
	Project addProject(Project project);
	void suspendProject(Long projectId);
	 Set<Task> getTaskListByProject(Long projectId); 
	 int getCompletedTask(Long projectId);
}
