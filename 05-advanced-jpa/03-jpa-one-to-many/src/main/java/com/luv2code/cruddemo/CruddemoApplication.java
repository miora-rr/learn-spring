package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.AppDAO;
import com.luv2code.cruddemo.entity.Course;
import com.luv2code.cruddemo.entity.Instructor;
import com.luv2code.cruddemo.entity.InstructorDetail;
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
    CommandLineRunner commandLineRunner (AppDAO appDAO) {
		return runner -> {
			//createInstructor(appDAO);
			//findInstructor(appDAO);
			//deleteInstructor(appDAO);

			//findInstructorDetail(appDAO);
			//deleteInstructorDetail(appDAO);

			createInstructorWithCourses(appDAO);
		};
	}

	private void createInstructorWithCourses(AppDAO appDAO) {
		// Create instructor and detail
		Instructor tempInstructor =
				new Instructor("Susan", "Public", "Susan@email.com");

		InstructorDetail tempInstructorDetail = new InstructorDetail("susanVEVO",
				"the sims 4");

		tempInstructor.setInstructorDetail(tempInstructorDetail);

		// Create some courses
		Course tempCourse1 = new Course("Spring Boot");
		Course tempCourse2 = new Course("Kubernetes");

		// Add the courses to the instructor
		tempInstructor.add(tempCourse1);
		tempInstructor.add(tempCourse2);

		// Save that instructor
		System.out.println("Saving the instructor: " + tempInstructor);
		System.out.println("The courses: " + tempInstructor.getCourses());
		appDAO.save(tempInstructor);

		System.out.println("Done!");
	}

	private void deleteInstructorDetail(AppDAO appDAO) {
		int id = 4;
		System.out.println("Deleting instructor detail id: " + id);

		appDAO.deleteInstructorDetailById(id);

		System.out.println("Done!");
	}

	private void findInstructorDetail(AppDAO appDAO) {
		int id = 2;

		System.out.println("Find instructorDetail with id: " + id);
		InstructorDetail instructorDetail = appDAO.findInstructorDetailById(id);

		System.out.println("The instructor found is :" + instructorDetail);
		System.out.println("The associate instructor is " + instructorDetail.getInstructor());
		System.out.println("Done!");

	}

	private void deleteInstructor(AppDAO appDAO) {
		int id = 1;

		System.out.println("deleting instructor id:" + id);

		appDAO.deleteInstructorById(id);

		System.out.println("Good job!");
	}

	private void findInstructor(AppDAO appDAO) {
		int id = 1;

		System.out.println("Find instructor with id: " + id);
		Instructor instructor = appDAO.findInstructorById(id);

		System.out.println("The instructor found is :" + instructor);
		System.out.println("The associate instructor detail only " + instructor.getInstructorDetail());
	}

	private void createInstructor(AppDAO appDAO) {
		Instructor tempInstructor =
				new Instructor("Chad", "Darby", "his@email.com");

		InstructorDetail tempInstructorDetail = new InstructorDetail("youtube.com",
				"drink coffee");

		tempInstructor.setInstructorDetail(tempInstructorDetail);

		System.out.println("Saving the instructor: " + tempInstructor);
		//Save instructor + instructorDetail bc Cascade.ALL
		appDAO.save(tempInstructor);
	}
}
