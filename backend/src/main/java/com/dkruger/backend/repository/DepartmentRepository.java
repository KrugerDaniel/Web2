package com.dkruger.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dkruger.backend.domain.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {
    
}
