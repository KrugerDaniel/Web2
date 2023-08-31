package com.dkruger.backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GeneralController {
    @GetMapping("/")
    public String index() {
        return "Olá, mundo!";
    }

    @GetMapping("/outroEP")
    public String index2() {
        return "Another EP";
    }

    @GetMapping("/outroEp2/{parameter}")
    public String index2Parametro(@PathVariable Integer parameter) {
        return "Você passou " + parameter + " como parâmetro";
    }

    @GetMapping(value = "gera/JSON", produces = "application/json")
    public String retornaJSON() {
        return "{\"nome\" : \"Daniel\", \"Cargo\" : \"aluno\"}";
    }
}
