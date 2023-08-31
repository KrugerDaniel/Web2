package com.dkruger.backend.domain;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;

@Entity
public class Department implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_department;

    @NotEmpty(message = "Field NM_DEPARTMENT must not be empty")
    @Length(min = 5, max = 250, message = "Field NM_DEPARTMENT shall be between 5 and 250 caracthers")
    private String nm_department;

    public Department() {}

    public Department(Integer id_department, String nm_department) {
        this.setId_department(id_department);
        this.setNm_department(nm_department);
    }
    
    public Integer getId_department() {
        return id_department;
    }

    public void setId_department(Integer id_department) {
        this.id_department = id_department;
    }

    public String getNm_department() {
        return nm_department;
    }

    public void setNm_department(String nm_department) {
        this.nm_department = nm_department;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id_department == null) ? 0 : id_department.hashCode());
        result = prime * result + ((nm_department == null) ? 0 : nm_department.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Department other = (Department) obj;
        if (id_department == null) {
            if (other.id_department != null)
                return false;
        } else if (!id_department.equals(other.id_department))
            return false;
        if (nm_department == null) {
            if (other.nm_department != null)
                return false;
        } else if (!nm_department.equals(other.nm_department))
            return false;
        return true;
    }
}
