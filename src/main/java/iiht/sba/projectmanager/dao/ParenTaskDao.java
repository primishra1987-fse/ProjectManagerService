package iiht.sba.projectmanager.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import iiht.sba.projectmanager.entity.ParentTask;

public interface ParenTaskDao extends JpaRepository<ParentTask, Long> {

}
