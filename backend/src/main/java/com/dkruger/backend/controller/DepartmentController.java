package com.dkruger.backend.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.dkruger.backend.domain.Department;
import com.dkruger.backend.domain.Funcionario;
import com.dkruger.backend.exceptions.ObjectNotFoundException;
import com.dkruger.backend.repository.DepartmentRepository;
import com.dkruger.backend.repository.FuncionarioRepository;
import com.dkruger.backend.services.DepartmentServices;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/department")
public class DepartmentController {
    @Autowired
    private DepartmentRepository deptRepository;

    @Autowired
    private FuncionarioRepository funcRepository;

    @Autowired
    private DepartmentServices departmentServices;

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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable Integer id) {
        departmentServices.deleteDepartment(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<Department> insertDepartment(@Valid @RequestBody Department department) {
        department.setId_department(null);
        deptRepository.save(department);
        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath()
            .path("/department/{id}")
            .buildAndExpand(department.getId_department())
            .toUri();
        return ResponseEntity.created(uri).body(department);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Department> updateDepartment(@Valid @PathVariable Integer id, @RequestBody Department dept) {
        Department deptAtual = 
            deptRepository.findById(id)
                .orElseThrow(
                    () -> new ObjectNotFoundException("Departament " + id + " not found!")
                );
        deptAtual.setNm_department(dept.getNm_department());
        deptRepository.save(deptAtual);
        return ResponseEntity.ok().body(deptAtual);
    }
}
