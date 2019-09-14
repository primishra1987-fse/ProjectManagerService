package iiht.sba.projectmanager.serviceimpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import iiht.sba.projectmanager.dao.ParentTaskDao;
import iiht.sba.projectmanager.dao.TaskDao;
import iiht.sba.projectmanager.entity.Parent;
import iiht.sba.projectmanager.entity.Task;

import iiht.sba.projectmanager.service.TaskService;
@Service
public class TaskServiceImpl implements TaskService {

	@Autowired
	TaskDao taskDao;
	
	@Autowired
	ParentTaskDao parentTaskDao;
	
	@Override
	public List<Task> getAllTask(){
		
		for (Task t1 :this.taskDao.findAll() ) {
			System.out.println(t1.getTaskName());
		}
		return this.taskDao.findAll();
	}
	
	@Override
	public List<Parent> getAllParentTask(){
		return this.parentTaskDao.findAll();
	}
	
	@Override
	public Task addTask(Task task) {
		System.out.println("user Id -- " +task.getUser().getFirstName());
		
		return this.taskDao.save(task);
	}
	
	@Override
	public Parent addParent(Task task) {
		Parent parent = new Parent();
		parent.setParentTask(task.getTaskName());
		return this.parentTaskDao.save(parent);
	}
	
	@Override
	public Task getTaskById(Long taskId) {
	Optional<Task> opTask = this.taskDao.findById(taskId);
			if (!opTask.isPresent()) {
				throw new EmptyResultDataAccessException(1);
			}
			System.out.println("getTask by Id -- " +opTask.get().getProject().getProjectName());
		return opTask.get();
	}
	
	@Override
	public Task editTask(Task task, Long taskId) {
		 Optional<Task> task1 = this.taskDao.findById(taskId); 
		 System.out.println("edit --" +task1.get().getTaskName());
		 System.out.println("task object --" +task.getTaskName());
		 task1.get().setUser(task.getUser());
		 task1.get().setTaskName(task.getTaskName());
		 task1.get().setStatus(task.getStatus());
		 task1.get().setStartDate(task.getStartDate());
		 task1.get().setEndDate(task.getEndDate());
		 System.out.println(" edit task task' project -- " +task.getProject().getProjectId());
		 task1.get().setProject(task.getProject());
		 task1.get().setPriority(task.getPriority());
		 task1.get().setParent(task.getParent());
		return this.taskDao.save(task1.get());
	}
	
	@Override
	public Task endTask(Long taskId) {
		Optional<Task>  task = this.taskDao.findById(taskId);
		task.get().setStatus("1");
		task.get().setEndDate(LocalDate.now());
		return this.taskDao.save(task.get());
	}
	
	
	
}
