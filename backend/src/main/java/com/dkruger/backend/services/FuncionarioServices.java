package com.dkruger.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dkruger.backend.domain.Department;
import com.dkruger.backend.domain.Funcionario;
import com.dkruger.backend.exceptions.ObjectNotFoundException;
import com.dkruger.backend.repository.DepartmentRepository;
import com.dkruger.backend.repository.FuncionarioRepository;

@Service
public class FuncionarioServices {
    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    public Funcionario insertFuncionario(Funcionario funcionario, Integer deptId) {
        Department dept = departmentRepository.findById(deptId)
                                              .orElseThrow(
                                                () -> new ObjectNotFoundException("Department " + deptId + " not found!")
                                              );
        funcionario.setId_funcionario(null);
        funcionario.setDepartment(dept);
        return funcionarioRepository.save(funcionario);
    }

    public void deleteFuncionario(Integer id) {
        funcionarioRepository.findById(id).orElseThrow(
            () -> new ObjectNotFoundException("Funcionario " + id + " not found!")
        );
        funcionarioRepository.deleteById(id);
    }

    public Funcionario updateFuncionario(Integer id, Funcionario func) {
        Funcionario funcAtual = 
            funcionarioRepository.findById(id)
                .orElseThrow(
                    () -> new ObjectNotFoundException("Funcionário " + id + " not found!")
                );
        funcAtual.setNm_funcionario(func.getNm_funcionario());
        return funcionarioRepository.save(funcAtual);
    }
}
