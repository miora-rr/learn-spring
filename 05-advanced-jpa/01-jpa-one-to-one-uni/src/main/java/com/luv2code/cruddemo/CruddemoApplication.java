package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.AppDAO;
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
			deleteInstructor(appDAO);
		};
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
