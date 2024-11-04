package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.StudentDAO;
import com.luv2code.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO) {
		return runner -> {
			//createStudent(studentDAO);
			//createMultipleStudent(studentDAO);
			//readStudent(studentDAO);
			//queryForStudents(studentDAO);
			queryForStudentsByLastName(studentDAO);
		};
	}

	private void queryForStudentsByLastName(StudentDAO studentDAO) {
		List<Student> theStudents = studentDAO.findByLastName("Jonas");

		for (Student student : theStudents) {
			System.out.println(student);
		}
	}

	private void queryForStudents(StudentDAO studentDAO) {
		List<Student> theStudents = studentDAO.findAll();

		for (Student student : theStudents) {
			System.out.println(student);
		}
	}

	private void readStudent(StudentDAO studentDAO) {
		System.out.println("Creating a student object...");
		Student tempStudent = new Student("Mickey", "Mouse", "mickey.mouse@email.com");

		System.out.println("Saving student");
		studentDAO.save(tempStudent);

		//display student
		System.out.println("Save student. Generated id: " + tempStudent.getId());

		System.out.println("Retrieve the student with id " + tempStudent.getId());
		Student theStudent = studentDAO.findById(tempStudent.getId());

		System.out.println("Found the student: " + theStudent);
	}

	private void createMultipleStudent(StudentDAO studentDAO) {
		System.out.println("Creating 3 students object...");
		Student tempStudent1 = new Student("Joe", "Jonas", "joe.doe@email.com");
		Student tempStudent2 = new Student("Nick", "Jonas", "nick.doe@email.com");
		Student tempStudent3 = new Student("Kevin", "Jonas", "kevin.doe@email.com");

		System.out.println("Saving out students");
		studentDAO.save(tempStudent1);
		studentDAO.save(tempStudent2);
		studentDAO.save(tempStudent3);
	}

	private void createStudent(StudentDAO studentDAO) {
		// Create student, save student object, display the saved student
		System.out.println("Creating new student object...");
		Student tempStudent = new Student("Paul", "Doe", "paul.doe@email.com");

		System.out.println("Saving student");
		studentDAO.save(tempStudent);

		//display student
		System.out.println("Save student. Generated id: " + tempStudent.getId());
	}
}
