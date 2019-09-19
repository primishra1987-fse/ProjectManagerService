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
import iiht.sba.projectmanager.model.TaskModel;
import iiht.sba.projectmanager.service.TaskService;
@Service
public class TaskServiceImpl implements TaskService {

	@Autowired
	TaskDao taskDao;
	
	@Autowired
	ParentTaskDao parentTaskDao;
	
	@Override
	public List<TaskModel> getAllTask(){
		
		List<TaskModel> taskListFinal =new ArrayList<TaskModel>();
		for (Task t1 :this.taskDao.findAll() ) {
			System.out.println(t1.getTaskName());
		}
		List<Task> taskList =  this.taskDao.findAll();
		
		for (Task task:taskList)
		{
			TaskModel taskLocal = getTaskModel(task);
			taskListFinal.add(taskLocal);
			
		}
		
		return taskListFinal;
		
	}
	
	private Task getTaskFromModel (TaskModel taskModel)
	{

		Task task = new Task();
		task.setParent(taskModel.getParent());
		task.setProject(taskModel.getProject());
		task.setTaskId(taskModel.getTaskId());
		task.setUser(taskModel.getUser());
		task.setStartDate(taskModel.getStartDate());
		task.setStatus(taskModel.getStatus());
		task.setPriority(taskModel.getPriority());
		task.setTaskName(taskModel.getTaskName());
		task.setEndDate(taskModel.getEndDate());
		
		return task;
	
	}
	
	private TaskModel getTaskModel(Task task)
	{
		TaskModel taskModel = new TaskModel();
		taskModel.setParent(task.getParent());
		taskModel.setProject(task.getProject());
		taskModel.setTaskId(task.getTaskId());
		taskModel.setUser(task.getUser());
		taskModel.setStartDate(task.getStartDate());
		taskModel.setStatus(task.getStatus());
		taskModel.setPriority(task.getPriority());
		taskModel.setTaskName(task.getTaskName());
		taskModel.setEndDate(task.getEndDate());
		
		return taskModel;
	}	
	
	@Override
	public List<Parent> getAllParentTask(){
		return this.parentTaskDao.findAll();
	}
	
	@Override
	public TaskModel addTask(TaskModel taskModel) {
		
		Task task = getTaskFromModel(taskModel);
		TaskModel returnTaskModel = getTaskModel(this.taskDao.save(task));
		return returnTaskModel;
	}
	
	@Override
	public Parent addParent(Task task) {
		Parent parent = new Parent();
		parent.setParentTask(task.getTaskName());
		return this.parentTaskDao.save(parent);
	}
	
	@Override
	public TaskModel getTaskById(Long taskId) {
	Optional<Task> opTask = this.taskDao.findById(taskId);
			if (!opTask.isPresent()) {
				throw new EmptyResultDataAccessException(1);
			}
			System.out.println("getTask by Id -- " +opTask.get().getProject().getProjectName());
		return this.getTaskModel(opTask.get());
	}
	
	@Override
	public TaskModel editTask(TaskModel task, Long taskId) {
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
		return getTaskModel(this.taskDao.save(task1.get()));
	}
	
	@Override
	public TaskModel endTask(Long taskId) {
		Optional<Task>  task = this.taskDao.findById(taskId);
		task.get().setStatus("1");
		task.get().setEndDate(LocalDate.now());
		return getTaskModel(this.taskDao.save(task.get()));
	}
	
	
	
}
