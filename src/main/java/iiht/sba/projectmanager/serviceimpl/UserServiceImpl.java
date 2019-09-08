package iiht.sba.projectmanager.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import iiht.sba.projectmanager.dao.UserDao;
import iiht.sba.projectmanager.entity.User;
import iiht.sba.projectmanager.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserDao userDao;
	
	public User addUser(User user) {
		return this.userDao.save(user);
		
	}
	@Override
	public List<User> getAllUser() {
		return this.userDao.findAll();
	}

	@Override
	public void deleteUser(User user) {
		
		this.userDao.deleteById(user.getUserId());
	}
	
	@Override
	public User editUser(User user) {
		return this.userDao.save(user);
		
	}

}
