package iiht.sba.projectmanager;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

import com.fasterxml.jackson.databind.ObjectMapper;

import iiht.sba.projectmanager.dao.ProjectDao;
import iiht.sba.projectmanager.dao.UserDao;
import iiht.sba.projectmanager.entity.Project;
import iiht.sba.projectmanager.entity.Task;
import iiht.sba.projectmanager.entity.User;;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ProjectControllerTest {
	
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ProjectDao mockRepository;

	@MockBean
	private UserDao userRepo;
	private static final ObjectMapper om = new ObjectMapper();
	
	@Before
	public void init() {
		
		Task task1 = new Task();
		task1.setTaskId(5L);
		task1.setTaskName("task1");
		 Set<Task> taskSet = new HashSet<Task>();
		 taskSet.add(task1);
		 User user2 = new User();
			user2.setFirstName("Viraaj");
			user2.setLastName("Tiwari");
			user2.setEmployeeId("102");
		Project prj1 = new Project();
		prj1.setProjectName("TestProject 1");
		prj1.setProjectId(1L);
		prj1.setNoOfTask(1);
		prj1.setTaskList(taskSet);
		prj1.setUser(user2);
		
		Project prj2= new Project();
		prj2.setProjectName("TestProject 2");
		prj2.setProjectId(3L);
		prj2.setNoOfTask(1);
		prj2.setTaskList(taskSet);
		prj2.setUser(user2);
		
		List<Project> prjList = Arrays.asList(prj1, prj2);

		when(mockRepository.findAll()).thenReturn(prjList);
		when(mockRepository.findById(1L)).thenReturn(Optional.of(prj1));
		when(mockRepository.findById(3L)).thenReturn(Optional.of(prj2));
		when(mockRepository.save(any(Project.class))).thenReturn(prj2);
		when(userRepo.save(any(User.class))).thenReturn(user2);
		doNothing().when(mockRepository).deleteById(1L);
	}
	
	 @Test 
	 public void find_getAllProjects_ok() throws Exception {
	 mockMvc.perform(get("/project/allProjects")).andExpect(status().isOk())
	 .andExpect(jsonPath("$.[0].projectId", is(1)))
	  .andExpect(jsonPath("$.[0].projectName", is("TestProject 1")));
	  
	  
	  }
	 
	 @Test
	 public void save_addProject_ok() throws Exception {

		 Task task1 = new Task();
			task1.setTaskId(5L);
			task1.setTaskName("task1");
			 Set<Task> taskSet = new HashSet<Task>();
			 taskSet.add(task1);
		 Project prj2= new Project();
			prj2.setProjectName("TestProject 2");
			prj2.setProjectId(3L);
			prj2.setNoOfTask(1);
			prj2.setTaskList(taskSet);

			mockMvc.perform(post("/project/add").content(om.writeValueAsString(prj2)).header(HttpHeaders.CONTENT_TYPE,
					MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(jsonPath("$.projectName", is("TestProject 2")))
					.andExpect(jsonPath("$.projectId", is(3)));
		
	 }
	 
	 @Test
	 public void delete_Project_ok() throws Exception {
		 mockMvc.perform(delete("/project/suspend/1")).andExpect(status().isOk());
		 
	 }
	
}

