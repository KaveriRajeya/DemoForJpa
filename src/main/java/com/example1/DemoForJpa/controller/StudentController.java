package com.example1.DemoForJpa.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example1.DemoForJpa.entity.Student;

@RestController
public class StudentController {

	@Autowired
	com.example1.DemoForJpa.repo.StudentRepo studentRepo;
	
	
	@PostMapping("/api/students") // adding the details
	private ResponseEntity<com.example1.DemoForJpa.entity.Student> saveStudent(@RequestBody Student student)
	{
		return new ResponseEntity<>(studentRepo.save(student),HttpStatus.CREATED);
	}
	
	@GetMapping("/api/students") // - Shows All The Details
	public ResponseEntity<List<com.example1.DemoForJpa.entity.Student>> getStudents()
	{
		return new ResponseEntity<>(studentRepo.findAll(),HttpStatus.OK);
	}
	
	@GetMapping("/api/students/{id}") // return Single student details based on id
	public ResponseEntity<com.example1.DemoForJpa.entity.Student> getStudents(@PathVariable long id) // To Handle pathParam so instead of List using only single student
	{
		Optional<com.example1.DemoForJpa.entity.Student> student = studentRepo.findById(id);
		if(student.isPresent())
		{
			return new ResponseEntity<>(student.get(),HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/api/students/{id}") // updating the details
	public ResponseEntity<Student> updateStudent(@PathVariable long id,@RequestBody Student stud) // To Handle pathParam so instead of List using only single student
	{
		Optional<Student> student = studentRepo.findById(id);
		if(student.isPresent())
		{
			student.get().setStudentName(stud.getStudentName());
			student.get().setStudentEmail(stud.getStudentEmail());
			student.get().setStudentAddress(stud.getStudentAddress());
			return new ResponseEntity<>(studentRepo.save(student.get()),HttpStatus.OK);
		}
		else
		{	
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/api/students/{id}") // return (delete) Single student details based on id
	public ResponseEntity<Void> deleteStudents(@PathVariable long id) // To Handle pathParam so instead of List using only single student
	{
		Optional<Student> student = studentRepo.findById(id);
		if(student.isPresent())
		{
			studentRepo.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		else
		{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
