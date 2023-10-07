package com.dkruger.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dkruger.backend.exceptions.ObjectNotFoundException;
import com.dkruger.backend.repository.DepartmentRepository;

@Service
public class DepartmentServices {
    @Autowired
    private DepartmentRepository departmentRepository;

    public void deleteDepartment(Integer id) {
        departmentRepository.findById(id).orElseThrow(
            () -> new ObjectNotFoundException("Department " + id + " not found!")
        );
        try {
            departmentRepository.deleteById(id);
        } catch (org.springframework.dao.DataIntegrityViolationException e) {
            throw new com.dkruger.backend.exceptions.DataIntegrityViolationException("Department " + id + " can not be deleted! There are employees linked!");
        }
    }
}
