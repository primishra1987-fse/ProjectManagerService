package iiht.sba.projectmanager.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import iiht.sba.projectmanager.entity.Task;

public interface TaskDao extends JpaRepository<Task, Long>{

}
