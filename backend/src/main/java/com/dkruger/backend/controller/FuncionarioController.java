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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.dkruger.backend.domain.Funcionario;
import com.dkruger.backend.repository.FuncionarioRepository;
import com.dkruger.backend.services.FuncionarioServices;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/funcionario")
public class FuncionarioController {
    @Autowired
    private FuncionarioRepository funcRepository;

    @Autowired
    private FuncionarioServices funcionarioServices;

    @GetMapping()
    public ResponseEntity<List<Funcionario>> findAll() {
        List<Funcionario> funcionarios = funcRepository.findAll();

        return ResponseEntity.ok().body(funcionarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Funcionario>> findById(@PathVariable Integer id) {
        Optional<Funcionario> funcioanrio = funcRepository.findById(id);

        return ResponseEntity.ok().body(funcioanrio);
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<Funcionario>> findByName(@PathVariable String nome) {
        return ResponseEntity.ok().body(funcRepository.findByName(nome));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFuncionario(@PathVariable Integer id) {
        funcRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id_dept}")
    public ResponseEntity<Funcionario> insertFuncionario(@PathVariable Integer id_dept, @Valid @RequestBody Funcionario funcionario) {
        Funcionario newFuncionario = funcionarioServices.insertFuncionario(funcionario, id_dept);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                                             .path("/{id}")
                                             .buildAndExpand(newFuncionario.getId_funcionario())
                                             .toUri();
        return ResponseEntity.created(uri).body(newFuncionario);
    }
}
