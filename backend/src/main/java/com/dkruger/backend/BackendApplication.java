package com.dkruger.backend;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.dkruger.backend.domain.Department;
import com.dkruger.backend.domain.Funcionario;
import com.dkruger.backend.repository.DepartmentRepository;
import com.dkruger.backend.repository.FuncionarioRepository;

@SpringBootApplication
public class BackendApplication {
	/* 
	Abaixo link para abrir uma pseudo SGBD

	link: http://localhost:8080/h2-console
	*/

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(DepartmentRepository deptRepo,
		FuncionarioRepository funcRepo) {
		return (args) -> {
			Department dept1 = new Department(null, "Finanças");
			Department dept2 = new Department(null, "Produção");

			deptRepo.save(dept1);
			deptRepo.save(dept2);

			Funcionario func1 = new Funcionario(null, "Alexander", dept2);
			Funcionario func2 = new Funcionario(null, "Roberto", dept1);
			Funcionario func3 = new Funcionario(null, "Jorge", dept1);
			Funcionario func4 = new Funcionario(null, "Salsicha", dept2);

			funcRepo.save(func1);
			funcRepo.save(func2);
			funcRepo.save(func3);
			funcRepo.save(func4);
		};
	}
}
