package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.AppDAO;
import com.luv2code.cruddemo.entity.Course;
import com.luv2code.cruddemo.entity.Instructor;
import com.luv2code.cruddemo.entity.InstructorDetail;
import com.luv2code.cruddemo.entity.Review;
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
    CommandLineRunner commandLineRunner (AppDAO appDAO) {
		return runner -> {
			//createCourseAndReviews(appDAO);
			//retreiveCourseAndReviews(appDAO);

			deleteCourseAndReviews(appDAO);
		};
	}

	private void deleteCourseAndReviews(AppDAO appDAO) {
		int theId = 10;

		System.out.println("Deleting course id " + theId);

		appDAO.deleteCourseById(theId);

		System.out.println("Done");
	}

	private void retreiveCourseAndReviews(AppDAO appDAO) {
		int theId = 10;
		// get course and reviews
		Course tempCourse = appDAO.findCourseAndReviewsByCourseId(theId);

		//print course
		System.out.println(tempCourse);

		// print reviews
		System.out.println(tempCourse.getReviews());

		System.out.println("Alright, done!");
	}

	private void createCourseAndReviews(AppDAO appDAO) {
		// create a course
		Course tempCourse = new Course("DevOps for Dummies");

		// add some reviews
		tempCourse.addReview(new Review("It was ok"));
		tempCourse.addReview(new Review("Useless"));
		tempCourse.addReview(new Review("Not for beginners"));

		// save the course. Also save the reviews since cascade.all
		System.out.println("Savinf the course");
		System.out.println(tempCourse);
		System.out.println(tempCourse.getReviews());

		appDAO.save(tempCourse);

		System.out.println("Done!");

	}

	private void deleteCourse(AppDAO appDAO) {
		int theId = 10;

		System.out.println("Deleting course id: " + theId);
		appDAO.deleteCourseById(theId);

		System.out.println("Done!");
	}

	private void updateCourse(AppDAO appDAO) {
		int theId = 10;

		// Find the course
		System.out.println("Find the course id : " + theId);
		Course tempCourse =  appDAO.findCourseById(theId);

		//Update the course
		System.out.println("Update the course with id : " + theId);
		tempCourse.setTitle("Advanced Breakup Songs");
		appDAO.update(tempCourse);

		System.out.println("Done!");
	}

	private void updateInstructor(AppDAO appDAO) {
		int theId = 1;

		// find instructor
		System.out.println("Finding the instructor id: " + theId);
		Instructor tempInstructor = appDAO.findInstructorById(theId);

		// Update instructor
		System.out.println("Updating the instructor id: " + theId);
		tempInstructor.setLastName("Swift");
		tempInstructor.setFirstName("Taylor");

		appDAO.update(tempInstructor);

		System.out.println("YEY!!");
	}

	private void findInstructorWithCoursesJoinFetch(AppDAO appDAO) {
		int theId = 1;

		System.out.println("Finding instructor id: " + theId);
		// Use the new method with JOIN FETCH
		Instructor tempInstructor = appDAO.findInstructorByIdJoinFetch(theId);

		System.out.println("tempInsturctor is " + tempInstructor);
		System.out.println("the associated courses: " + tempInstructor.getCourses());

		System.out.println("Good job!");
	}

	private void findCoursesForInstructor(AppDAO appDAO) {
		int id = 1;
		System.out.println("Find instructor id: " + id);

		Instructor tempInstructor = appDAO.findInstructorById(id);

		System.out.println("tempInstructor" + tempInstructor);

		//Find courses for instructor
		System.out.println("Finding courses for instructor id:" + id);
		List<Course> courses = appDAO.findCoursesByInstructorId(id);

		// Associate courses with Instructor
		tempInstructor.setCourses(courses);

		System.out.println("the associated courses: " + tempInstructor.getCourses());

		System.out.println("Done!");
	}

	private void findInstructorWithCourses(AppDAO appDAO) {
		int id = 1;
		System.out.println("Find instructor id: " + id);

		Instructor tempInstructor = appDAO.findInstructorById(id);

		System.out.println("tempInstructor" + tempInstructor);
		// Trying to get associated course with instructor. Will throw an exception
		System.out.println("The associated courses: " + tempInstructor.getCourses());

		System.out.println("Done!");
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
