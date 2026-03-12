package com.jpa.mappings.entity;

import jakarta.persistence.*;

/**
 * Entity representing additional details for an Instructor.
 * This entity is mapped to the "instructor_detail" table.
 *
 * Relationship:
 * One-to-One with Instructor (inverse side).
 */
@Entity
@Table(name = "instructor_detail")
public class InstructorDetail {

    // ====================
    // Fields
    // ====================

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "youtube_channel", length = 128)
    private String youtubeChannel;

    @Column(name = "hobby", length = 64)
    private String hobby;

    /**
     * Bidirectional One-to-One mapping with Instructor.
     *
     * mappedBy indicates that the Instructor entity owns the relationship.
     * Cascade excludes REMOVE to prevent accidental deletion of Instructor
     * when InstructorDetail is deleted.
     */
    @OneToOne(
            mappedBy = "instructorDetail",
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE,
                    CascadeType.DETACH,
                    CascadeType.REFRESH
            }
    )
    private Instructor instructor;

    // ====================
    // Constructors
    // ====================

    public InstructorDetail() {
        // Default constructor required by JPA
    }

    public InstructorDetail(String youtubeChannel, String hobby) {
        this.youtubeChannel = youtubeChannel;
        this.hobby = hobby;
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


    public String getYoutubeChannel() {
        return youtubeChannel;
    }

    public void setYoutubeChannel(String youtubeChannel) {
        this.youtubeChannel = youtubeChannel;
    }


    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }


    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    // ====================
    // Utility Methods
    // ====================

    @Override
    public String toString() {
        return "InstructorDetail{" +
                "id=" + id +
                ", youtubeChannel='" + youtubeChannel + '\'' +
                ", hobby='" + hobby + '\'' +
                '}';
    }
}
