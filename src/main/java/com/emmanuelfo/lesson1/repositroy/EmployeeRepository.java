package com.emmanuelfo.lesson1.repositroy;

import com.emmanuelfo.lesson1.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {

}
