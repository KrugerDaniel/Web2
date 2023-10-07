package com.dkruger.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dkruger.backend.domain.Aluno;
import com.dkruger.backend.domain.Aula;
import com.dkruger.backend.exceptions.ObjectNotFoundException;
import com.dkruger.backend.repository.AlunoRepository;
import com.dkruger.backend.repository.AulaRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/aula")
public class AulaController {
    @Autowired
    private AulaRepository aulaRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    @GetMapping
    public ResponseEntity<List<Aula>> findAll() {
        List<Aula> aulas = aulaRepository.findAll();
        return ResponseEntity.ok().body(aulas);
    }

    @PostMapping(value = "/{id_aula}/aluno/{id_aluno}")
    public ResponseEntity<Aula> insertAlunoAula(@Valid @PathVariable Integer id_aula, @Valid @PathVariable Integer id_aluno) {
        Aula aula = aulaRepository.findById(id_aula)
            .orElseThrow(
                () -> new ObjectNotFoundException("Aula " + id_aula + " not found")
            );
        Aluno aluno = alunoRepository.findById(id_aluno)
            .orElseThrow(
                () -> new ObjectNotFoundException("Aluno " + id_aluno + " not found")
            );
        List<Aluno> alunos = aula.getAlunos();
        alunos.add(aluno);
        aula.setAlunos(alunos);
        aulaRepository.save(aula);
        return ResponseEntity.ok().body(aula);
    }

    @DeleteMapping("/{id_aula}/aluno/{id_aluno}")
    public ResponseEntity<Void> deleteAulaAluno(@Valid @PathVariable Integer idAula, @Valid @PathVariable Integer idAluno) {
        Aula aula = aulaRepository.findById(idAula)
            .orElseThrow(
                () -> new ObjectNotFoundException("Aula " + idAula + " not found")
            );
        Aluno aluno = alunoRepository.findById(idAluno)
            .orElseThrow(
                () -> new ObjectNotFoundException("Aluno " + idAluno + " not found")
            );
        List<Aluno> alunos = aula.getAlunos();
        alunos.remove(aluno);
        aula.setAlunos(alunos);
        aulaRepository.save(aula);
        return ResponseEntity.noContent().build();
    }
}
