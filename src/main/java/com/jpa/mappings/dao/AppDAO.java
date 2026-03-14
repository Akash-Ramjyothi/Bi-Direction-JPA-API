package com.jpa.mappings.dao;

import com.jpa.mappings.entity.Course;
import com.jpa.mappings.entity.Instructor;
import com.jpa.mappings.entity.InstructorDetail;
import com.jpa.mappings.entity.Student;

import java.util.List;

/**
 * Data Access Object (DAO) interface for managing persistence
 * operations related to Instructor, InstructorDetail, Course,
 * and Student entities.
 *
 * Defines CRUD operations and specialized fetch queries used
 * across the application.
 */
public interface AppDAO {

    /* ===============================
       Instructor Operations
       =============================== */

    /**
     * Persist a new Instructor entity.
     */
    void save(Instructor instructor);

    /**
     * Find Instructor by primary key.
     */
    Instructor findInstructorById(int instructorId);

    /**
     * Fetch Instructor along with associated courses
     * and instructor detail using JOIN FETCH.
     */
    Instructor findInstructorByIdJoinFetch(int instructorId);

    /**
     * Update an existing Instructor entity.
     */
    void update(Instructor instructor);

    /**
     * Delete Instructor by ID.
     */
    void deleteInstructorById(int instructorId);


    /* ===============================
       InstructorDetail Operations
       =============================== */

    /**
     * Find InstructorDetail by primary key.
     */
    InstructorDetail findInstructorDetailById(int instructorDetailId);

    /**
     * Delete InstructorDetail by ID.
     */
    void deleteInstructorDetailById(int instructorDetailId);


    /* ===============================
       Course Operations
       =============================== */

    /**
     * Persist a new Course entity.
     */
    void save(Course course);

    /**
     * Find Course by ID.
     */
    Course findCourseById(int courseId);

    /**
     * Fetch Course with associated reviews.
     */
    Course findCourseAndReviewsByCourseId(int courseId);

    /**
     * Fetch Course with associated students.
     */
    Course findCourseAndStudentsByCourseId(int courseId);

    /**
     * Update existing Course entity.
     */
    void update(Course course);

    /**
     * Delete Course by ID.
     */
    void deleteCourseById(int courseId);

    /**
     * Retrieve all courses belonging to a specific instructor.
     */
    List<Course> findCoursesByInstructorId(int instructorId);


    /* ===============================
       Student Operations
       =============================== */

    /**
     * Fetch Student along with enrolled courses.
     */
    Student findStudentAndCourseByStudentId(int studentId);

    /**
     * Update existing Student entity.
     */
    void update(Student student);

    /**
     * Delete Student by ID.
     */
    void deleteStudentById(int studentId);
}
