package com.sid.sdj2.services;

import com.sid.sdj2.Entity.Student;
import com.sid.sdj2.dto.StudentAddressDTO;
import com.sid.sdj2.repo.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Component
public class StudentService {
    private StudentRepository studentRepository;

    @Autowired
    private void studentRepository(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Autowired
    private ModelMapper modelMapper;


    public ResponseEntity<String> save(Student student) {
        studentRepository.save(student);
        return new ResponseEntity<String>("Saved", HttpStatus.OK);

    }

    public ResponseEntity<String> delete(Integer id) {
        studentRepository.deleteById(id);
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }

    @Transactional
    public Optional<Student> findByName(String name) {
        System.out.println("finding");
        return studentRepository.findByName(name);

    }

    //@Transactional
    public void updateStudentNameById(String name, Integer id) {
        studentRepository.updateStudentNameByID(name, id);
        System.out.println("Name Updated");
    }

    public ResponseEntity<?> findStudentAddressListById(Integer integer) {
        Optional<Student> student = studentRepository.findById(integer);
        if (student.isPresent()) {
            StudentAddressDTO dto = modelMapper.map(student.get(), StudentAddressDTO.class);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        }
        return new ResponseEntity<>("Not found", HttpStatus.OK);
    }

    //StudentConversion --->> StudentAddressDTO(manually)
//    public StudentAddressDTO convertStudentToStudentAddressDTO(Student student) {
//        StudentAddressDTO studentAddressDTO = new StudentAddressDTO();
//        studentAddressDTO.setId(student.getId());
//        studentAddressDTO.setName(student.getName());
//        studentAddressDTO.setAddressList(student.getAddressList());
//        return studentAddressDTO;
//    }
}
