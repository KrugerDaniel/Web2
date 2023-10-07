package com.dkruger.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dkruger.backend.domain.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Integer> {
    
}
