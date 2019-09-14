package iiht.sba.projectmanager;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import iiht.sba.projectmanager.dao.ProjectDao;
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

	private static final ObjectMapper om = new ObjectMapper();
	
	@Before
	public void init() {
		/*
		 * Task task1 = new Task(); task1.setTaskId(5L); task1.setTaskName("task1");
		 */
		Project prj1 = new Project();
		prj1.setProjectName("TestProject 1");
		prj1.setProjectId(1L);
		prj1.setNoOfTask(1);
		
		Project prj2= new Project();
		prj2.setProjectName("TestProject 2");
		prj2.setProjectId(3L);
		prj2.setNoOfTask(1);
		
		List<Project> prjList = Arrays.asList(prj1, prj2);

		when(mockRepository.findAll()).thenReturn(prjList);
		when(mockRepository.findById(1L)).thenReturn(Optional.of(prj1));
	}
	
	 @Test public void find_getAllProjects_ok() throws Exception {
	 
	 mockMvc.perform(get("/project/allProjects")).andExpect(status().isOk())
	 .andExpect(jsonPath("$.[0].projectId", is(1L)))
	  .andExpect(jsonPath("$.[0].projectName", is("TestProject 1")));
	  
	  
	  }
	 
	
}

