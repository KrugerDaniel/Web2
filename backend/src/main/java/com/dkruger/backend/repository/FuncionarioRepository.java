package com.dkruger.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dkruger.backend.domain.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer> {

    @Query("SELECT objeto_func FROM Funcionario objeto_func WHERE objeto_func.department.id_department = :pIdDept ORDER BY nm_funcionario")
    List<Funcionario> findByDept(@Param(value = "pIdDept") Integer pIdDept);

    @Query("SELECT objeto_func FROM Funcionario objeto_func where objeto_func.nm_funcionario LIKE %:pNm_funcionario% ORDER BY nm_funcionario")
    List<Funcionario> findByName(@Param(value = "pNm_funcionario") String pNm_funcionario);
}