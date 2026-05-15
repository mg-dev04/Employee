package com.employee.manage.Repo;

import com.employee.manage.Model.userCredentials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface userRepo extends JpaRepository<userCredentials,Integer> {
            userCredentials findByUsername(String username);
            userCredentials findByEmail(String email);
}
