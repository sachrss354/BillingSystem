package com.srpingsecuritydb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.srpingsecuritydb.entity.Employee;


@Repository
public interface EmpRepo extends JpaRepository<Employee, Integer>{
public Employee findByEmail(String email);
public Employee findByVarificationCode(String code);
@Query("update Employee e set e.failedAttempt=?1 where email=?2")
@Modifying
public void updateFailedAttempt(int attempt,String email);
public Employee findByEmailAndMobile(String em,String mo);
}
