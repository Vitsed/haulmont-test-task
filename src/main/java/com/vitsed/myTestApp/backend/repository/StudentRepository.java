package com.vitsed.myTestApp.backend.repository;

import com.vitsed.myTestApp.backend.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("select s from Student s " +
            "where lower(s.lastName) like lower(concat('%', :searchTerm, '%'))")
    List<Student> search(@Param("searchTerm") String searchTerm);

    @Query("select s from Student s where s.studentGroup.number = :searchItem")
    List<Student> search(@Param("searchItem") Integer searchItem);

    @Query("select s from Student s where lower(s.lastName) " +
            "like lower(concat('%',:item,'%')) and " +
            "s.studentGroup.number = :number")
    List<Student> search(
            @Param("item") String lastName,
            @Param("number") Integer groupNumber
    );
}
