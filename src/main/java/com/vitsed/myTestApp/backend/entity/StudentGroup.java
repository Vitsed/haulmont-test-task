package com.vitsed.myTestApp.backend.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.LinkedList;
import java.util.List;

@Entity
@EqualsAndHashCode(callSuper = true)
@Data
public class StudentGroup extends AbstractEntity  {

    @NotNull
    @Positive
    private Integer number = 0;

    @NotNull
    @NotEmpty
    private String facultyName = "";

    @OneToMany(mappedBy = "studentGroup", fetch = FetchType.EAGER)
    private List<Student> students = new LinkedList<>();

    @Override
    public String toString() {
        return getNumber().toString();
    }
}
