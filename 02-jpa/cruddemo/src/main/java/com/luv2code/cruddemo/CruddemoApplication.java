package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.StudentDAO;
import com.luv2code.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO) {
		return runner -> {
			createStudent(studentDAO);
		};
	}

	private void createStudent(StudentDAO studentDAO) {
		// Create student, save student object, display d of saved student
		System.out.println("Creating new student object...");
		Student tempStudent = new Student("Paul", "Doe", "paul.doe@email.com");

		System.out.println("Saving student");
		studentDAO.save(tempStudent);

		//display student
		System.out.println("Save student. Generated id: " + tempStudent.getId());
	}
}
