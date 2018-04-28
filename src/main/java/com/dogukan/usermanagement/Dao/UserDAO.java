package com.dogukan.usermanagement.Dao;

import com.dogukan.usermanagement.Model.User;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDAO extends JpaRepository<User, Integer> {

}

