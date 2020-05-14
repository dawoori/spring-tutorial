package com.example.springpractice.crudwithvaadin;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByLastNameStartsWithIgnoreCase(String lastName);
}
