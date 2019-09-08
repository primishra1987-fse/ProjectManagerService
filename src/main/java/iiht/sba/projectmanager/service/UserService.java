package iiht.sba.projectmanager.service;

import java.util.List;

import iiht.sba.projectmanager.entity.User;

public interface UserService {

	User addUser(User user);
	List<User> getAllUser();
	void deleteUser(User user);
	User editUser(User user);
}
