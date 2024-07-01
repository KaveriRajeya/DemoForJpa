package com.example1.DemoForJpa.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example1.DemoForJpa.entity.Student;

public interface StudentRepo extends JpaRepository<Student , Long>{

}