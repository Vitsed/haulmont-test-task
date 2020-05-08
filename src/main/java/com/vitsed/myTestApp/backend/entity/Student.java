package com.vitsed.myTestApp.backend.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
@EqualsAndHashCode(callSuper = true)
@Data
public class Student extends AbstractEntity implements Cloneable {
    @NotNull
    @NotEmpty
    private String firstName ="";

    @NotNull
    @NotEmpty
    private String lastName ="";

    @NotNull
    @NotEmpty
    private String patronymic="";

    @NotNull
    @Positive
    private Integer yearOfBirth = 0;

    @ManyToOne
    @JoinColumn(name = "studentGroup_id")
    private StudentGroup studentGroup;

}
