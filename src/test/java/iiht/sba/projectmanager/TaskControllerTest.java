package iiht.sba.projectmanager;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import com.fasterxml.jackson.databind.ObjectMapper;

import iiht.sba.projectmanager.dao.ParentTaskDao;
import iiht.sba.projectmanager.dao.TaskDao;
import iiht.sba.projectmanager.entity.Parent;
import iiht.sba.projectmanager.entity.Project;
import iiht.sba.projectmanager.entity.Task;
import iiht.sba.projectmanager.entity.User;
import iiht.sba.projectmanager.model.TaskModel;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class TaskControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private TaskDao mockRepository;
	
	@MockBean
	private ParentTaskDao mockParent;

	private static final ObjectMapper om = new ObjectMapper();
	
	@Before
	public void init() {
		Parent parent1 = new Parent();
		parent1.setParentId(1L);
		parent1.setParentTask("Parent1");
		
		Parent parent2 = new Parent();
		parent2.setParentId(2L);
		parent2.setParentTask("Parent2");
		
		
		Task task = new Task();
	    User user2 = new User();
		user2.setFirstName("Viraaj");
		user2.setLastName("Tiwari");
		user2.setEmployeeId("102");
	
		Project prj1 = new Project();
		prj1.setProjectName("TestProject 1");
		prj1.setProjectId(1L);
		
		task.setTaskId(5L);
		task.setParent(parent1);
		task.setTaskName("task1");
		task.setUser(user2);
		task.setProject(prj1);
		
		Task task1 = new Task();
		task1.setTaskId(6L);
		task1.setParent(parent2);
		task1.setTaskName("task2");
		task1.setUser(user2);
		task1.setProject(prj1);
		
		List<Task> taskList = Arrays.asList(task, task1);
		
		
		
		
		
		List<Parent> parentList = Arrays.asList(parent1, parent2);
		when(mockRepository.findById(5L)).thenReturn(Optional.of(task));
		when(mockParent.findAll()).thenReturn(parentList);
		when(mockParent.save(any(Parent.class))).thenReturn(parent2);
		when(mockRepository.save(any(Task.class))).thenReturn(task);
		when(mockRepository.findAll()).thenReturn(taskList);
	}
	
	
	@Test 
	public void find_getAllParentTask_ok() throws Exception {
	mockMvc.perform(get("/task/getAllParent")).andExpect(status().isOk())
	.andExpect(jsonPath("$.[0].parentId", is(1)))
	 .andExpect(jsonPath("$.[0].parentTask", is("Parent1")));
	 
	 
	 }
	
	 @Test
	 public void save_addParent_ok() throws Exception {

		 Parent parentTask = new Parent();
		 parentTask.setParentId(2L);
		 parentTask.setParentTask("Parent2");
			mockMvc.perform(post("/task/addParent").content(om.writeValueAsString(parentTask)).header(HttpHeaders.CONTENT_TYPE,
					MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(jsonPath("$.parentTask", is("Parent2")))
					.andExpect(jsonPath("$.parentId", is(2)));
		
	 }
	
	 @Test
	 public void save_addTask_ok() throws Exception {

		 Task task = new Task();
		 User user2 = new User();
			user2.setFirstName("Viraaj");
			user2.setLastName("Tiwari");
			user2.setEmployeeId("102");
		
			Project prj1 = new Project();
			prj1.setProjectName("TestProject 1");
			prj1.setProjectId(1L);
			
			Parent parent1 = new Parent();
			parent1.setParentId(1L);
			parent1.setParentTask("Parent1");
			task.setTaskId(5L);
			task.setParent(parent1);
			task.setTaskName("task1");
			task.setUser(user2);
			task.setProject(prj1);
			
			mockMvc.perform(post("/task/add").content(om.writeValueAsString(task)).header(HttpHeaders.CONTENT_TYPE,
					MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(jsonPath("$.taskName", is("task1")))
					.andExpect(jsonPath("$.taskId", is(5)));
					
		
	 }
	 
	 @Test 
	 public void find_getAllTask_ok() throws Exception {
	 mockMvc.perform(get("/task/getAllTasks")).andExpect(status().isOk())
	 .andExpect(jsonPath("$.[1].taskId", is(6)))
	  .andExpect(jsonPath("$.[1].taskName", is("task2")));
	  
	  
	  }
	 
	 @Test
	 public void find_suspendTask_ok() throws Exception {
		 Task task = new Task();
		    User user2 = new User();
			user2.setFirstName("Viraaj");
			user2.setLastName("Tiwari");
			user2.setEmployeeId("102");
		
			Project prj1 = new Project();
			prj1.setProjectName("TestProject 1");
			prj1.setProjectId(1L);
			
			Parent parent1 = new Parent();
			parent1.setParentId(1L);
			parent1.setParentTask("Parent1");
			
			task.setTaskId(5L);
			task.setParent(parent1);
			task.setTaskName("task1");
			task.setUser(user2);
			task.setProject(prj1);
		 mockMvc.perform(put("/task/suspend/5").content(om.writeValueAsString(task)).header(HttpHeaders.CONTENT_TYPE,
					MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(jsonPath("$.taskId", is(5)))
					.andExpect(jsonPath("$.taskName", is("task1")));
	 }
	 
	 @Test
	 public void save_updateTask_ok() throws Exception {

		 Task task = new Task();
		 User user2 = new User();
			user2.setFirstName("Viraaj");
			user2.setLastName("Tiwari");
			user2.setEmployeeId("102");
		
			Project prj1 = new Project();
			prj1.setProjectName("TestProject 1");
			prj1.setProjectId(1L);
			
			Parent parent1 = new Parent();
			parent1.setParentId(1L);
			parent1.setParentTask("Parent1");
			task.setTaskId(5L);
			task.setParent(parent1);
			task.setTaskName("task1");
			task.setUser(user2);
			task.setProject(prj1);
			
			mockMvc.perform(post("/task/add").content(om.writeValueAsString(task)).header(HttpHeaders.CONTENT_TYPE,
					MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(jsonPath("$.taskName", is("task1")))
					.andExpect(jsonPath("$.taskId", is(5)));
					
		
	 }
	 
	 @Test
	 public void find_taskById_ok() throws Exception {

		 mockMvc.perform(get("/task/getTask/5")).andExpect(status().isOk())
		 .andExpect(jsonPath("$.taskId", is(5)))
		  .andExpect(jsonPath("$.taskName", is("task1")));
		  
	 }
		  
		 
	 }

	


