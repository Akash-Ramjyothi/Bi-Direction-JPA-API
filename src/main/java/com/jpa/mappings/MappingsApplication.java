package com.jpa.mappings;

import com.jpa.mappings.dao.AppDAO;
import com.jpa.mappings.entity.Instructor;
import com.jpa.mappings.entity.InstructorDetail;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MappingsApplication {

    public static void main(String[] args) {
        SpringApplication.run(MappingsApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(AppDAO appDAO) {

        return runner -> {
//            createInstructor(appDAO);
//            findInstructor(appDAO);
            deleteInstructor(appDAO);
        };
    }

    private void deleteInstructor(AppDAO appDAO) {
        int theId = 1;
        System.out.println("Deleting instrutor ID: " + theId);

        appDAO.deleteInstructorById(theId);
        System.out.println("Done!");
    }

    private void findInstructor(AppDAO appDAO) {
        int theId = 2;

        System.out.println("Finding instructor ID: " + theId);
        Instructor tempInstructor = appDAO.findInstructorById(theId);
        System.out.println("tempInstructor : " + tempInstructor);
        System.out.println("the associate instructorDetail only : " + tempInstructor.getInstructorDetail());
    }

    private void createInstructor(AppDAO appDAO) {

//        Instructor tempInstructor = new Instructor("Chad", "Darby", "darby@luv2code.com");
//        InstructorDetail tempInstructorDetail = new InstructorDetail("http://www.luv2code.com/youtube", "Luv 2 code !!!");

        Instructor tempInstructor = new Instructor("Madhu", "Patel", "madhu@luv2code.com");
        InstructorDetail tempInstructorDetail = new InstructorDetail("http://www.luv2code.com/youtube", "Guitar");

        tempInstructor.setInstructorDetail(tempInstructorDetail);

        System.out.println("🚀 tempInstructor = " + tempInstructor);
        System.out.println("🚜 tempInstructorDetail = " + tempInstructorDetail);

        appDAO.save(tempInstructor);
        System.out.println("Done!");
    }
}
