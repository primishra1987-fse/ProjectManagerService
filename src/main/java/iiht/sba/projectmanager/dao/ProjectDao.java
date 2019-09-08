package iiht.sba.projectmanager.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import iiht.sba.projectmanager.entity.Project;

public interface ProjectDao extends JpaRepository<Project, Long>{

}
