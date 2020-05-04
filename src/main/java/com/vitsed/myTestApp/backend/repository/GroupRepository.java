package com.vitsed.myTestApp.backend.repository;

import com.vitsed.myTestApp.backend.entity.StudentGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<StudentGroup, Long> {
}
