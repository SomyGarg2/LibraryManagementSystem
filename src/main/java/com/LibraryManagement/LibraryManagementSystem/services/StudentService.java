package com.LibraryManagement.LibraryManagementSystem.services;

import com.LibraryManagement.LibraryManagementSystem.Repositories.StudentRepository;
import com.LibraryManagement.LibraryManagementSystem.dto.StudentDto;
import com.LibraryManagement.LibraryManagementSystem.entity.Student;
import org.modelmapper.ModelMapper;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;

    public StudentService(StudentRepository studentRepository, ModelMapper modelMapper) {
        this.studentRepository = studentRepository;
        this.modelMapper = modelMapper;
    }

    public StudentDto createStudent(StudentDto inputStudent){
        Student toSaveEntity = modelMapper.map(inputStudent, Student.class);
        Student savedEntity = studentRepository.save(toSaveEntity);
        return modelMapper.map(savedEntity, StudentDto.class);
    }

    public StudentDto getStudentById(Long id){
        Student studentEntity = studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student not found"));
        return modelMapper.map(studentEntity, StudentDto.class);
    }

    public StudentDto addBalance(Long StudentId, Double amount){
        if (amount == null || amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        Student studentEntity = studentRepository
                .findById(StudentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id:" + StudentId));
        studentEntity.setWalletBalance(studentEntity.getWalletBalance() + amount);
        Student updatedEntity = studentRepository.save(studentEntity);
        return modelMapper.map(updatedEntity, StudentDto.class);
    }

    public Double getBalance(Long studentId) {
        Student studentEntity = studentRepository
                .findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + studentId));
        return studentEntity.getWalletBalance();
    }
}
