package com.example.demo.controller;

import com.example.demo.model.ApiResponse;
import com.example.demo.model.Student;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping("")
    public ResponseEntity<ApiResponse> getStudents() {
        List<Student> result = studentService.getStudents();
        ApiResponse response = new ApiResponse(studentService.getResponseMessage(), result);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getStudentById(@PathVariable("id") String id) {
        Student result = studentService.getStudentById(id);
        ApiResponse response = new ApiResponse(studentService.getResponseMessage(), result);

        if (result != null) return ResponseEntity.status(HttpStatus.OK).body(response);
        else return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);

    }

    @PostMapping("")
    public ResponseEntity<ApiResponse> addStudent(@RequestBody Student student) {
        studentService.addStudent(student.getId(), student.getName(), student.getPhone());
        ApiResponse response;

        if (studentService.getResponseMessage().contains("success")) {
            response = new ApiResponse(studentService.getResponseMessage(), student.trimWhiteSpaces());
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } else {
            response = new ApiResponse(studentService.getResponseMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteStudent(@PathVariable("id") String id) {
        Student selectedStudent = studentService.getStudentById(id);
        ApiResponse response;

        studentService.deleteStudent(id);

        if (selectedStudent != null) {
            if (studentService.getResponseMessage().contains("success")) {
                response = new ApiResponse(studentService.getResponseMessage(), selectedStudent);
                return ResponseEntity.status(HttpStatus.OK).body(response);
            } else {
                response = new ApiResponse(studentService.getResponseMessage(), null);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }
        } else {
            response = new ApiResponse(studentService.getResponseMessage(), null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateStudent(@PathVariable("id") String id, @RequestBody Student student) {
        student.setId(id);
        Student selectedStudent = studentService.getStudentById(id);
        ApiResponse response;

        studentService.updateStudent(id, student.getName(), student.getPhone());

        if (selectedStudent != null) {
            if (studentService.getResponseMessage().contains("success")) {
                response = new ApiResponse(studentService.getResponseMessage(), student);
                return ResponseEntity.status(HttpStatus.OK).body(response);
            } else {
                response = new ApiResponse(studentService.getResponseMessage(), null);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }
        } else {
            response = new ApiResponse(studentService.getResponseMessage(), null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
}
