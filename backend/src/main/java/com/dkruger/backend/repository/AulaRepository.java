package com.dkruger.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dkruger.backend.domain.Aula;

public interface AulaRepository extends JpaRepository<Aula, Integer> {
    
}
