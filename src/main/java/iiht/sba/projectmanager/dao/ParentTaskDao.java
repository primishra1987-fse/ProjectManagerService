package iiht.sba.projectmanager.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import iiht.sba.projectmanager.entity.Parent;

public interface ParentTaskDao extends JpaRepository<Parent, Long> {

}
