package com.sid.sdj2;


import com.sid.sdj2.repo.ResourceRepo;
import com.sid.sdj2.repo.StudentRepository;
import com.sid.sdj2.repo.VideoRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@EnableAspectJAutoProxy
@SpringBootApplication
public class Sdj2Application implements CommandLineRunner {

    @Autowired
    private VideoRepo videoRepo;
    @Autowired
    private ResourceRepo resourceRepo;

    @Autowired
    private StudentRepository studentRepository;

    public static void main(String[] args) {
        SpringApplication.run(Sdj2Application.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
//        List<Resource> resorce = resourceRepo.findAll();
//        System.out.println(resorce);

//        Specification<Student> specification = Specification.where(StudentSpecification.hasName("Sid"));
//        specification.and(StudentSpecification.rollNoLike("53"));
//        List<Student> students = studentRepository.findAll(specification);
//        students.forEach(System.out::println);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
