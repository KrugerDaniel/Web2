package com.dkruger.backend.domain;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;

@Entity
public class Funcionario implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_funcionario;

    @NotEmpty(message = "Campo NM_FUNCIONARIO não pode ser vazio!")
    @Length(min = 5, max = 255, message = "Campo NM_FUNCIONARIO deve ter entre 5 a 255 caracteres")
    private String nm_funcionario;

    @ManyToOne
    @JoinColumn(name = "id_department")
    private Department department;

    public Funcionario() {}

    public Funcionario(Integer id_funcionario, String nm_duncionario, Department department) {
        this.setId_funcionario(id_funcionario);
        this.setNm_funcionario(nm_duncionario);
        this.setDepartment(department);
    }

    public void setId_funcionario(Integer id_funcionario) {
        this.id_funcionario = id_funcionario;
    }

    public Integer getId_funcionario() {
        return this.id_funcionario;
    }

    public void setNm_funcionario(String nm_funcionario) {
        this.nm_funcionario = nm_funcionario;
    }

    public String getNm_funcionario() {
        return this.nm_funcionario;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Department getDepartment() {
        return this.department;
    }
}