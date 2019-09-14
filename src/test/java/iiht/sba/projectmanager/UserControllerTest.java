package iiht.sba.projectmanager;

import java.util.Arrays;
import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.http.HttpHeaders;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.Before;
import org.junit.Test;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.is;
import org.springframework.http.MediaType;
import iiht.sba.projectmanager.dao.UserDao;
import iiht.sba.projectmanager.entity.User;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class UserControllerTest {
	@Autowired
	private MockMvc mockMvc;

	// private static final ObjectMapper om = new ObjectMapper();

	@MockBean
	private UserDao mockRepository;

	private static final ObjectMapper om = new ObjectMapper();

	@Before
	public void init() {

		User user1 = new User();
		user1.setFirstName("Priyanka");
		user1.setUserId(1L);
		user1.setEmployeeId("101");

		User user2 = new User();
		user2.setFirstName("Viraaj");
		user2.setLastName("Tiwari");
		user2.setEmployeeId("102");

		List<User> userList = Arrays.asList(user1, user2);

		when(mockRepository.findAll()).thenReturn(userList);
		when(mockRepository.save(any(User.class))).thenReturn(user2);
		doNothing().when(mockRepository).deleteById(1L);

	}

	@Test
	public void find_getAllUsers_ok() throws Exception {

		mockMvc.perform(get("/user/allUsers")).andExpect(status().isOk())
				.andExpect(jsonPath("$.[0].employeeId", is("101")))
				.andExpect(jsonPath("$.[0].firstName", is("Priyanka")));

	}

	@Test
	public void save_addUser_ok() throws Exception {
		User userSave = new User();
		userSave.setFirstName("Viraaj");
		userSave.setLastName("Tiwari");
		userSave.setEmployeeId("102");

		mockMvc.perform(post("/user/add").content(om.writeValueAsString(userSave)).header(HttpHeaders.CONTENT_TYPE,
				MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(jsonPath("$.firstName", is("Viraaj")))
				.andExpect(jsonPath("$.lastName", is("Tiwari"))).andExpect(jsonPath("$.employeeId", is("102")));
	}

	@Test
	public void delete_removeUser_ok() throws Exception {
		mockMvc.perform(delete("/user/delete/1")).andExpect(status().isOk());
	}
}
