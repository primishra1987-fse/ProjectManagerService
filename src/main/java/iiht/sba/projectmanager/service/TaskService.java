package iiht.sba.projectmanager.service;

import java.util.List;

import iiht.sba.projectmanager.entity.Parent;
import iiht.sba.projectmanager.entity.Task;
import iiht.sba.projectmanager.model.TaskModel;

public interface TaskService {
	List<TaskModel> getAllTask();

	List<Parent> getAllParentTask();

	TaskModel addTask(TaskModel task);

	Parent addParent(Task parentTask);

	TaskModel getTaskById(Long taskId);

	TaskModel editTask(TaskModel taskModel, Long taskId);

	TaskModel endTask(Long taskId);

}
