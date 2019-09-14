package iiht.sba.projectmanager.service;

import java.util.List;

import iiht.sba.projectmanager.entity.Parent;
import iiht.sba.projectmanager.entity.Task;

public interface TaskService {
	List<Task> getAllTask();
	List<Parent> getAllParentTask();
	
	Task addTask(Task task);
	Parent addParent(Task parentTask);
	
	Task getTaskById(Long taskId);
	
	Task editTask(Task task, Long taskId);
	Task endTask(Long taskId);
	

}
