package com.jpa.mappings.dao;

import com.jpa.mappings.entity.Course;
import com.jpa.mappings.entity.Instructor;
import com.jpa.mappings.entity.InstructorDetail;
import com.jpa.mappings.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class AppDAOImpl implements AppDAO {

    private final EntityManager entityManager;

    @Autowired
    public AppDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * Save Instructor
     */
    @Override
    @Transactional
    public void save(Instructor instructor) {
        entityManager.persist(instructor);
    }

    /**
     * Find Instructor by ID
     */
    @Override
    public Instructor findInstructorById(int id) {
        return entityManager.find(Instructor.class, id);
    }

    /**
     * Delete Instructor by ID
     * Break association with courses before deleting
     */
    @Override
    @Transactional
    public void deleteInstructorById(int id) {

        Instructor instructor = entityManager.find(Instructor.class, id);

        if (instructor != null) {

            List<Course> courses = instructor.getCourses();

            for (Course course : courses) {
                course.setInstructor(null);
            }

            entityManager.remove(instructor);
        }
    }

    /**
     * Find InstructorDetail by ID
     */
    @Override
    public InstructorDetail findInstructorDetailById(int id) {
        return entityManager.find(InstructorDetail.class, id);
    }

    /**
     * Delete InstructorDetail by ID
     * Break bi-directional relationship first
     */
    @Override
    @Transactional
    public void deleteInstructorDetailById(int id) {

        InstructorDetail instructorDetail = entityManager.find(InstructorDetail.class, id);

        if (instructorDetail != null) {
            instructorDetail.getInstructor().setInstructorDetail(null);
            entityManager.remove(instructorDetail);
        }
    }

    /**
     * Find Courses by Instructor ID
     */
    @Override
    public List<Course> findCoursesByInstructorId(int id) {

        TypedQuery<Course> query =
                entityManager.createQuery(
                        "FROM Course WHERE instructor.id = :instructorId",
                        Course.class
                );

        query.setParameter("instructorId", id);

        return query.getResultList();
    }

    /**
     * Fetch Instructor with Courses and InstructorDetail using JOIN FETCH
     */
    @Override
    public Instructor findInstructorByIdJoinFetch(int id) {

        TypedQuery<Instructor> query =
                entityManager.createQuery(
                        "SELECT i FROM Instructor i " +
                                "JOIN FETCH i.courses " +
                                "JOIN FETCH i.instructorDetail " +
                                "WHERE i.id = :instructorId",
                        Instructor.class
                );

        query.setParameter("instructorId", id);

        return query.getSingleResult();
    }

    /**
     * Update Instructor
     */
    @Override
    @Transactional
    public void update(Instructor instructor) {
        entityManager.merge(instructor);
    }

    /**
     * Update Course
     */
    @Override
    @Transactional
    public void update(Course course) {
        entityManager.merge(course);
    }

    /**
     * Find Course by ID
     */
    @Override
    public Course findCourseById(int id) {
        return entityManager.find(Course.class, id);
    }

    /**
     * Delete Course by ID
     */
    @Override
    @Transactional
    public void deleteCourseById(int id) {

        Course course = entityManager.find(Course.class, id);

        if (course != null) {
            entityManager.remove(course);
        }
    }

    /**
     * Save Course
     */
    @Override
    @Transactional
    public void save(Course course) {
        entityManager.persist(course);
    }

    /**
     * Fetch Course with Reviews
     */
    @Override
    public Course findCourseAndReviewsByCourseId(int id) {

        TypedQuery<Course> query =
                entityManager.createQuery(
                        "SELECT c FROM Course c " +
                                "JOIN FETCH c.reviews " +
                                "WHERE c.id = :courseId",
                        Course.class
                );

        query.setParameter("courseId", id);

        return query.getSingleResult();
    }

    /**
     * Fetch Course with Students
     */
    @Override
    public Course findCourseAndStudentsByCourseId(int id) {

        TypedQuery<Course> query =
                entityManager.createQuery(
                        "SELECT c FROM Course c " +
                                "JOIN FETCH c.students " +
                                "WHERE c.id = :courseId",
                        Course.class
                );

        query.setParameter("courseId", id);

        return query.getSingleResult();
    }

    /**
     * Fetch Student with Courses
     */
    @Override
    public Student findStudentAndCourseByStudentId(int id) {

        TypedQuery<Student> query =
                entityManager.createQuery(
                        "SELECT s FROM Student s " +
                                "JOIN FETCH s.courses " +
                                "WHERE s.id = :studentId",
                        Student.class
                );

        query.setParameter("studentId", id);

        return query.getSingleResult();
    }

    /**
     * Update Student
     */
    @Override
    @Transactional
    public void update(Student student) {
        entityManager.merge(student);
    }

    /**
     * Delete Student by ID
     * Remove student from all courses first
     */
    @Override
    @Transactional
    public void deleteStudentById(int id) {

        Student student = entityManager.find(Student.class, id);

        if (student != null) {

            List<Course> courses = student.getCourses();

            for (Course course : courses) {
                course.getStudents().remove(student);
            }

            entityManager.remove(student);
        }
    }
}
