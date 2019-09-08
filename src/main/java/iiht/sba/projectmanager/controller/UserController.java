package iiht.sba.projectmanager.controller;

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

import iiht.sba.projectmanager.entity.User;
import iiht.sba.projectmanager.service.UserService;

@RestController
@RequestMapping("/user")

public class UserController {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserService userService;
	
	@CrossOrigin(origins = "*", maxAge = 3600)
	@GetMapping(value = "allUsers", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<User>> getAllUsers() {
		log.info("<-- Inside getAllUsers -->");
		List<User> users = this.userService.getAllUser();
		if (users == null || users.isEmpty()) {
			return new ResponseEntity<List<User>>(users, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}
	
	@CrossOrigin(origins = "*", maxAge = 3600)
	@PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> addUser(@RequestBody User user) {
		log.info("In User Controller - addUser");
		return new ResponseEntity<User>(this.userService.addUser(user), HttpStatus.OK);
	}
	
	@CrossOrigin(origins = "*", maxAge = 3600)
	@DeleteMapping(value="/delete/{userID}",  produces = MediaType.APPLICATION_JSON_VALUE)
			public ResponseEntity<Long> deleteUser(@PathVariable("userID") Long userID){
		try {
			log.info("Controller - deleteUser call for User.");
			userService.deleteUser(userID);
		}
		catch (Exception e) {
			log.error("Controller - deleteUser call returned error : " +e.getMessage());
			e.printStackTrace();
		}
		
		
		return new ResponseEntity<Long>(userID, HttpStatus.OK);
			
	}
	
	
}
