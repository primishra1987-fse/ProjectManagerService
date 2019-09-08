package iiht.sba.projectmanager.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import iiht.sba.projectmanager.entity.User;

public interface UserDao extends JpaRepository<User, Long>{

}
