package com.vitsed.myTestApp.backend.repository;

import com.vitsed.myTestApp.backend.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
