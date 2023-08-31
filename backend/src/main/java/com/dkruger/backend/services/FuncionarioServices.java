package com.dkruger.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dkruger.backend.domain.Department;
import com.dkruger.backend.domain.Funcionario;
import com.dkruger.backend.repository.DepartmentRepository;
import com.dkruger.backend.repository.FuncionarioRepository;

@Service
public class FuncionarioServices {
    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    public Funcionario insertFuncionario(Funcionario funcionario, Integer deptId) {
        Department dept = departmentRepository.findById(deptId).orElseThrow();
        funcionario.setId_funcionario(null);
        funcionario.setDepartment(dept);
        return funcionarioRepository.save(funcionario);
    }
}
