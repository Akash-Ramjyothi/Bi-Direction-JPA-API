package com.jpa.mappings;

import com.jpa.mappings.dao.AppDAO;
import com.jpa.mappings.entity.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class MappingsApplication {

    // ----------------------------------------------------------------
    // Application Entry Point
    // ----------------------------------------------------------------
    public static void main(String[] args) {
        SpringApplication.run(MappingsApplication.class, args);
    }

    // ----------------------------------------------------------------
    // Constants (used across demo methods)
    // ----------------------------------------------------------------
    private static final int INSTRUCTOR_ID = 1;
    private static final int COURSE_ID = 10;
    private static final int STUDENT_ID = 2;

    // ----------------------------------------------------------------
    // Command Line Runner
    // ----------------------------------------------------------------
    @Bean
    public CommandLineRunner commandLineRunner(AppDAO appDAO) {

        return runner -> {

            System.out.println("\n===============================");
            System.out.println("JPA MAPPINGS DEMO APPLICATION");
            System.out.println("===============================\n");

            // Toggle demo methods as required

//            createInstructor(appDAO);
//            findInstructor(appDAO);
//            updateInstructor(appDAO);
//            deleteInstructor(appDAO);

//            createInstructorWithCourses(appDAO);
//            findInstructorWithCourses(appDAO);
//            findCoursesForInstructor(appDAO);
//            findInstructorWithCoursesJoinFetch(appDAO);

//            updateCourse(appDAO);
//            deleteCourse(appDAO);

//            createCourseAndReviews(appDAO);
//            retrieveCourseAndReviews(appDAO);
//            deleteCourseAndReviews(appDAO);

//            createCourseAndStudents(appDAO);
//            findCourseAndStudents(appDAO);
//            findStudentAndCourses(appDAO);
//            addMoreCoursesForStudent(appDAO);

            deleteStudent(appDAO);

            System.out.println("\n===============================");
            System.out.println("EXECUTION FINISHED");
            System.out.println("===============================\n");
        };
    }

    // ----------------------------------------------------------------
    // MANY TO MANY: STUDENT <-> COURSE
    // ----------------------------------------------------------------

    private void deleteStudent(AppDAO appDAO) {

        int theId = 1;

        System.out.println("\nDeleting Student ID: " + theId);

        appDAO.deleteStudentById(theId);

        System.out.println("Student deleted successfully!\n");
    }

    private void addMoreCoursesForStudent(AppDAO appDAO) {

        int theId = STUDENT_ID;

        System.out.println("\nLoading student...");

        Student tempStudent = appDAO.findStudentAndCourseByStudentId(theId);

        Course tempCourse1 = new Course("Rubik's Cube - How to Speed Cube");
        Course tempCourse2 = new Course("Atari 2600 - Game Development");

        tempStudent.addCourse(tempCourse1);
        tempStudent.addCourse(tempCourse2);

        System.out.println("Updating Student: " + tempStudent);
        System.out.println("Associated Courses: " + tempStudent.getCourses());

        appDAO.update(tempStudent);

        System.out.println("Student updated successfully!\n");
    }

    private void findStudentAndCourses(AppDAO appDAO) {

        int theId = STUDENT_ID;

        System.out.println("\nFinding Student ID: " + theId);

        Student tempStudent = appDAO.findStudentAndCourseByStudentId(theId);

        System.out.println("Loaded Student: " + tempStudent);
        System.out.println("Courses: " + tempStudent.getCourses());

        System.out.println("Fetch completed!\n");
    }

    private void findCourseAndStudents(AppDAO appDAO) {

        int theId = COURSE_ID;

        System.out.println("\nFinding Course ID: " + theId);

        Course tempCourse = appDAO.findCourseAndStudentsByCourseId(theId);

        System.out.println("Loaded Course: " + tempCourse);
        System.out.println("Students Enrolled: " + tempCourse.getStudents());

        System.out.println("Fetch completed!\n");
    }

    private void createCourseAndStudents(AppDAO appDAO) {

        System.out.println("\nCreating course and students...");

        Course tempCourse = new Course("Pacman - How To Score One Million Points");

        Student tempStudent1 = new Student("John", "Doe", "john@luv2code.com");
        Student tempStudent2 = new Student("Mary", "Public", "mary@luv2code.com");

        tempCourse.addStudent(tempStudent1);
        tempCourse.addStudent(tempStudent2);

        System.out.println("Saving Course: " + tempCourse);
        System.out.println("Associated Students: " + tempCourse.getStudents());

        appDAO.save(tempCourse);

        System.out.println("Course and Students saved successfully!\n");
    }

    // ----------------------------------------------------------------
    // ONE TO MANY: COURSE -> REVIEWS
    // ----------------------------------------------------------------

    private void deleteCourseAndReviews(AppDAO appDAO) {

        int theId = COURSE_ID;

        System.out.println("\nDeleting Course ID: " + theId);

        appDAO.deleteCourseById(theId);

        System.out.println("Course deleted successfully!\n");
    }

    private void retrieveCourseAndReviews(AppDAO appDAO) {

        int theId = COURSE_ID;

        System.out.println("\nFetching Course and Reviews...");

        Course tempCourse = appDAO.findCourseAndReviewsByCourseId(theId);

        System.out.println("Course: " + tempCourse);
        System.out.println("Reviews: " + tempCourse.getReviews());

        System.out.println("Fetch completed!\n");
    }

    private void createCourseAndReviews(AppDAO appDAO) {

        System.out.println("\nCreating Course with Reviews...");

        Course tempCourse = new Course("Pacman - How To Score One Million Points");

        tempCourse.addReview(new Review("Great course ... loved it!"));
        tempCourse.addReview(new Review("Cool course, job well done."));
        tempCourse.addReview(new Review("Could be improved but still useful."));

        System.out.println("Saving Course: " + tempCourse);
        System.out.println("Reviews: " + tempCourse.getReviews());

        appDAO.save(tempCourse);

        System.out.println("Course with reviews saved successfully!\n");
    }

    // ----------------------------------------------------------------
    // COURSE CRUD
    // ----------------------------------------------------------------

    private void deleteCourse(AppDAO appDAO) {

        int theId = COURSE_ID;

        System.out.println("\nDeleting Course ID: " + theId);

        appDAO.deleteCourseById(theId);

        System.out.println("Course deleted!\n");
    }

    private void updateCourse(AppDAO appDAO) {

        int theId = COURSE_ID;

        System.out.println("\nFinding Course ID: " + theId);

        Course tempCourse = appDAO.findCourseById(theId);

        System.out.println("Updating Course Title...");

        tempCourse.setTitle("Enjoy the Simple Things");

        appDAO.update(tempCourse);

        System.out.println("Course updated successfully!\n");
    }

    // ----------------------------------------------------------------
    // INSTRUCTOR OPERATIONS
    // ----------------------------------------------------------------

    private void updateInstructor(AppDAO appDAO) {

        int theId = INSTRUCTOR_ID;

        System.out.println("\nFinding Instructor ID: " + theId);

        Instructor tempInstructor = appDAO.findInstructorById(theId);

        System.out.println("Updating Instructor Last Name...");

        tempInstructor.setLastName("TESTER");

        appDAO.update(tempInstructor);

        System.out.println("Instructor updated!\n");
    }

    private void findInstructorWithCoursesJoinFetch(AppDAO appDAO) {

        int theId = INSTRUCTOR_ID;

        System.out.println("\nFetching Instructor using JOIN FETCH...");

        Instructor tempInstructor = appDAO.findInstructorByIdJoinFetch(theId);

        System.out.println("Instructor: " + tempInstructor);
        System.out.println("Courses: " + tempInstructor.getCourses());

        System.out.println("Fetch completed!\n");
    }

    private void findCoursesForInstructor(AppDAO appDAO) {

        int theId = INSTRUCTOR_ID;

        System.out.println("\nFinding Instructor ID: " + theId);

        Instructor tempInstructor = appDAO.findInstructorById(theId);

        System.out.println("Instructor: " + tempInstructor);

        System.out.println("Fetching courses separately...");

        List<Course> courses = appDAO.findCoursesByInstructorId(theId);

        tempInstructor.setCourses(courses);

        System.out.println("Courses: " + tempInstructor.getCourses());

        System.out.println("Fetch completed!\n");
    }

    private void findInstructorWithCourses(AppDAO appDAO) {

        int theId = INSTRUCTOR_ID;

        System.out.println("\nFinding Instructor ID: " + theId);

        Instructor tempInstructor = appDAO.findInstructorById(theId);

        System.out.println("Instructor: " + tempInstructor);
        System.out.println("Courses: " + tempInstructor.getCourses());

        System.out.println("Fetch completed!\n");
    }

    private void createInstructorWithCourses(AppDAO appDAO) {

        System.out.println("\nCreating Instructor with Courses...");

        Instructor tempInstructor =
                new Instructor("Susa", "Public", "madhususan.public@luv2code.com");

        InstructorDetail tempInstructorDetail =
                new InstructorDetail("http://www.youtube.com", "Video Games");

        tempInstructor.setInstructorDetail(tempInstructorDetail);

        Course tempCourse1 = new Course("Air Guitar - The Ultimate Guide");
        Course tempCourse2 = new Course("The Pinball Masterclass");

        tempInstructor.add(tempCourse1);
        tempInstructor.add(tempCourse2);

        System.out.println("Saving Instructor: " + tempInstructor);
        System.out.println("Courses: " + tempInstructor.getCourses());

        appDAO.save(tempInstructor);

        System.out.println("Instructor with courses saved!\n");
    }

    // ----------------------------------------------------------------
    // INSTRUCTOR DETAIL OPERATIONS
    // ----------------------------------------------------------------

    private void deleteInstructorDetail(AppDAO appDAO) {

        int theId = 3;

        System.out.println("\nDeleting Instructor Detail ID: " + theId);

        appDAO.deleteInstructorDetailById(theId);

        System.out.println("Instructor detail deleted!\n");
    }

    private void findInstructorDetail(AppDAO appDAO) {

        int theId = 2;

        System.out.println("\nFinding InstructorDetail ID: " + theId);

        InstructorDetail tempInstructorDetail =
                appDAO.findInstructorDetailById(theId);

        System.out.println("InstructorDetail: " + tempInstructorDetail);
        System.out.println("Instructor: " + tempInstructorDetail.getInstructor());

        System.out.println("Fetch completed!\n");
    }

    // ----------------------------------------------------------------
    // INSTRUCTOR CRUD
    // ----------------------------------------------------------------

    private void deleteInstructor(AppDAO appDAO) {

        int theId = INSTRUCTOR_ID;

        System.out.println("\nDeleting Instructor ID: " + theId);

        appDAO.deleteInstructorById(theId);

        System.out.println("Instructor deleted!\n");
    }

    private void findInstructor(AppDAO appDAO) {

        int theId = 2;

        System.out.println("\nFinding Instructor ID: " + theId);

        Instructor tempInstructor = appDAO.findInstructorById(theId);

        System.out.println("Instructor: " + tempInstructor);
        System.out.println("Instructor Detail: " + tempInstructor.getInstructorDetail());

        System.out.println("Fetch completed!\n");
    }

    private void createInstructor(AppDAO appDAO) {

        System.out.println("\nCreating Instructor...");

        Instructor tempInstructor =
                new Instructor("Madhu", "Patel", "madhu@luv2code.com");

        InstructorDetail tempInstructorDetail =
                new InstructorDetail("http://www.luv2code.com/youtube", "Guitar");

        tempInstructor.setInstructorDetail(tempInstructorDetail);

        System.out.println("Instructor: " + tempInstructor);
        System.out.println("Instructor Detail: " + tempInstructorDetail);

        appDAO.save(tempInstructor);

        System.out.println("Instructor saved successfully!\n");
    }
}
