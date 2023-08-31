package com.dkruger.backend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dkruger.backend.domain.Department;
import com.dkruger.backend.domain.Funcionario;
import com.dkruger.backend.repository.DepartmentRepository;
import com.dkruger.backend.repository.FuncionarioRepository;

@RestController
@RequestMapping(value = "/department")
public class DepartmentController {
    @Autowired
    private DepartmentRepository deptRepository;

    @Autowired
    private FuncionarioRepository funcRepository;

    @GetMapping
    public ResponseEntity<List<Department>> findAll() {
        List<Department> departments = deptRepository.findAll();

        return ResponseEntity.ok().body(departments);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Department>> findById(@PathVariable Integer id) {
        Optional<Department> department = deptRepository.findById(id);
        
        return ResponseEntity.ok().body(department);
    }

    @GetMapping("/{id}/funcionarios")
    public ResponseEntity<List<Funcionario>> fingByDept(@PathVariable Integer id) {
        return ResponseEntity.ok().body(funcRepository.findByDept(id));
    }
}
