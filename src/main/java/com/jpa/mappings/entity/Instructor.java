package com.jpa.mappings.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Entity representing an Instructor.
 * Maps to the "instructor" table.
 *
 * Relationships:
 * 1. One-to-One with InstructorDetail (owning side)
 * 2. One-to-Many with Course
 */
@Entity
@Table(name = "instructor")
public class Instructor {

    // ====================
    // Fields
    // ====================

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "first_name", length = 45, nullable = false)
    private String firstName;

    @Column(name = "last_name", length = 45, nullable = false)
    private String lastName;

    @Column(name = "email", length = 128, unique = true, nullable = false)
    private String email;

    /**
     * One-to-One relationship with InstructorDetail.
     * Instructor is the owning side of the relationship.
     *
     * CascadeType.ALL allows operations on Instructor
     * to propagate to InstructorDetail.
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "instructor_detail_id")
    private InstructorDetail instructorDetail;

    /**
     * One-to-Many relationship with Course.
     *
     * mappedBy indicates that Course owns the relationship.
     * LAZY loading is used to avoid unnecessary data fetching.
     *
     * REMOVE is excluded intentionally to prevent accidental
     * deletion of courses when an instructor is deleted.
     */
    @OneToMany(
            mappedBy = "instructor",
            fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE,
                    CascadeType.DETACH,
                    CascadeType.REFRESH
            }
    )
    private List<Course> courses;

    // ====================
    // Constructors
    // ====================

    public Instructor() {
        // Required by JPA
    }

    public Instructor(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    // ====================
    // Getters & Setters
    // ====================

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


    public InstructorDetail getInstructorDetail() {
        return instructorDetail;
    }

    public void setInstructorDetail(InstructorDetail instructorDetail) {
        this.instructorDetail = instructorDetail;
    }


    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    // ====================
    // Helper Methods
    // ====================

    /**
     * Convenience method to add a course and maintain
     * the bidirectional relationship.
     */
    public void addCourse(Course tempCourse) {

        if (courses == null) {
            courses = new ArrayList<>();
        }

        courses.add(tempCourse);
        tempCourse.setInstructor(this);
    }

    /**
     * Convenience method to remove a course and maintain
     * bidirectional integrity.
     */
    public void removeCourse(Course tempCourse) {

        if (courses != null) {
            courses.remove(tempCourse);
            tempCourse.setInstructor(null);
        }
    }

    // ====================
    // Utility Methods
    // ====================

    @Override
    public String toString() {
        return "Instructor{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", instructorDetail=" + instructorDetail +
                '}';
    }
}
