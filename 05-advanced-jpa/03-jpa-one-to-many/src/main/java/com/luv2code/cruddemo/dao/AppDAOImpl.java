package com.luv2code.cruddemo.dao;

import com.luv2code.cruddemo.entity.Course;
import com.luv2code.cruddemo.entity.Instructor;
import com.luv2code.cruddemo.entity.InstructorDetail;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AppDAOImpl implements AppDAO{

    EntityManager entityManager;

    @Autowired
    public AppDAOImpl (EntityManager theEntityManager) {
        entityManager = theEntityManager;
    }
    @Override
    @Transactional
    public void save(Instructor theInstructor) {
        // Since Cascade.all will also persist InstructorDetails
        entityManager.persist(theInstructor);
    }

    @Override
    public Instructor findInstructorById(int theId) {
        return entityManager.find(Instructor.class, theId);
    }

    @Override
    @Transactional
    public void deleteInstructorById(int id) {
        // Find instructor
        Instructor tempInstructor = entityManager.find(Instructor.class, id);

        // Get courses
        List<Course> tempCourses = tempInstructor.getCourses();

        // Break association of all courses for the instructor
        tempCourses
                .stream()
                .forEach(course -> course.setInstructor(null));

        // Remove the course
        entityManager.remove(tempInstructor);
    }

    @Override
    public InstructorDetail findInstructorDetailById(int id) {
        return entityManager.find(InstructorDetail.class, id);
    }

    @Override
    @Transactional
    public void deleteInstructorDetailById(int theId) {
        InstructorDetail tempInstructorDetail = entityManager.find(InstructorDetail.class, theId);

        //Remove the associated object reference.
        // Break the bidirectionnal link by making it null
        tempInstructorDetail
                .getInstructor()
                .setInstructorDetail(null);

        entityManager.remove(tempInstructorDetail);
    }

    @Override
    public List<Course> findCoursesByInstructorId(int theId) {
        TypedQuery<Course> query = entityManager.createQuery(
                "from Course where instructor.id = :data", Course.class
        );
        query.setParameter("data", theId);

        return query.getResultList();
    }

    @Override
    public Instructor findInstructorByIdJoinFetch(int theId) {
        // Similar to FetchType.LAZY but better :)
        // Make sur that there is a whitespace at the end of each line of the query
        TypedQuery<Instructor> query = entityManager.createQuery(
                "select i " + "from Instructor i "
                + "JOIN FETCH i.courses " // Make it one query to get Courses
                + "JOIN FETCH i.instructorDetail " // Make it one query to get instructorDetails
                + "where i.id = :data", Instructor.class
        );
        // Don't forget this line
        query.setParameter("data", theId);

        return query.getSingleResult();
    }

    @Override
    @Transactional // dont forget, we are updating the Instructor
    public void update(Instructor tempInstructor) {
        entityManager.merge(tempInstructor);
    }

    @Override
    @Transactional
    public void update(Course course) {
        entityManager.merge(course);
    }

    @Override
    public Course findCourseById(int theId) {
        return entityManager.find(Course.class, theId);
    }

    @Override
    @Transactional // dont forget
    public void deleteCourseById(int theId) {
        // Retrieve
        Course tempCourse = entityManager.find(Course.class, theId);

        // Delete
        entityManager.remove(tempCourse);
    }
}
