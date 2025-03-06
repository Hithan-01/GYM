package com.example.Gym.Repositories;



import com.example.Gym.Entidades.EmployeeDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeDetailsRepository extends JpaRepository<EmployeeDetails, Long> {
    Optional<EmployeeDetails> findByUserId(Long userId);
    List<EmployeeDetails> findByDepartmentId(Integer departmentId);
    List<EmployeeDetails> findAll();
}
