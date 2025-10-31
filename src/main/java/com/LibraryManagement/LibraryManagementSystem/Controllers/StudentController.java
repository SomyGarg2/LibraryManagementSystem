package com.LibraryManagement.LibraryManagementSystem.Controllers;

import com.LibraryManagement.LibraryManagementSystem.dto.StudentDto;
import com.LibraryManagement.LibraryManagementSystem.services.StudentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping(path = "/{id}")
    public StudentDto getStudentById(@PathVariable Long id) {
        return studentService.getStudentById(id);
    }

    @PostMapping
    public StudentDto createStudent(@RequestBody StudentDto studentDto){
        return studentService.createStudent(studentDto);
    }

    @PostMapping("/{id}/wallet/add")
    public StudentDto addBalance(@PathVariable Long id, @RequestParam Double amount) {
        return studentService.addBalance(id, amount);
    }

    @GetMapping(path = "/{id}/balance")
    public Double getBalance(@PathVariable Long id) {
        return studentService.getBalance(id);
    }


}
