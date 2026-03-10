package com.jpa.mappings.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Entity representing a Student.
 *
 * Demonstrates the inverse side of a Many-To-Many relationship
 * with the Course entity.
 */
@Entity
@Table(name = "student")
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;

    // -------------------------------------------------------------
    // Fields
    // -------------------------------------------------------------

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    /**
     * Inverse side of Many-To-Many relationship with Course.
     */
    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE,
                    CascadeType.DETACH,
                    CascadeType.REFRESH
            },
            mappedBy = "students"
    )
    private List<Course> courses = new ArrayList<>();


    // -------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------

    public Student() {
    }

    public Student(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public Student(int id, String firstName, String lastName, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    // -------------------------------------------------------------
    // Getters & Setters
    // -------------------------------------------------------------

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    // -------------------------------------------------------------
    // Relationship Helper Methods
    // -------------------------------------------------------------

    /**
     * Adds a course to the student and maintains the
     * bidirectional relationship.
     */
    public void addCourse(Course course) {

        if (courses == null) {
            courses = new ArrayList<>();
        }

        courses.add(course);

        if (!course.getStudents().contains(this)) {
            course.addStudent(this);
        }
    }

    /**
     * Removes a course from the student.
     */
    public void removeCourse(Course course) {

        if (courses != null) {
            courses.remove(course);
        }

        if (course.getStudents() != null) {
            course.getStudents().remove(this);
        }
    }

    // -------------------------------------------------------------
    // Utility Methods
    // -------------------------------------------------------------

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    /**
     * Equality based on primary key.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student student)) return false;
        return id == student.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
