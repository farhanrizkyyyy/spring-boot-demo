package com.example.demo.service;

import com.example.demo.model.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    private static final List<Student> students = new ArrayList<>();
    private static String responseMessage = "";

    public String getResponseMessage() {
        return responseMessage;
    }

    public List<Student> getStudents() {
        seedStudents();

        responseMessage = "";
        List<Student> result = new ArrayList<>();

        for (Student student : students) {
            if (student.getActive()) result.add(student);
        }

        return result;
    }

    public Student getStudentById(String id) {
        seedStudents();

        id = id.trim();
        responseMessage = "";
        Student result = null;

        for (Student student : students) {
            if (student.getId().equals(id) && student.getActive()) result = student;
        }

        if (result == null) responseMessage = "Cannot find student with ID " + id;

        return result;
    }

    public void addStudent(String id, String name, String phone) {
        seedStudents();

        id = id.trim();
        name = name.trim();
        phone = phone.trim();
        responseMessage = "";
        boolean isExist = false;

        for (Student student : students) {
            if (student.getId().equals(id)) {
                isExist = true;
                break;
            }
        }

        if (!isExist) {
            if (isIdValid(id) && isNameValid(name) && isPhoneValid(phone)) {
                Student newStudent = new Student(id, name, phone);
                students.add(newStudent);
                responseMessage = "Student successfully added";
            }
        } else {
            responseMessage = "Student with ID " + id + " already exist";
        }
    }

    private boolean isIdValid(String id) {
        String numbersOnly = "[0-9]+";

        if (id.equals("")) {
            responseMessage = "ID cannot be empty";
            return false;
        } else {
            if (id.matches(numbersOnly)) {
                if (id.length() == 6) {
                    return true;
                } else {
                    responseMessage = "ID must be 6 digit";
                    return false;
                }
            } else {
                responseMessage = "ID must be a number";
                return false;
            }
        }
    }

    private boolean isNameValid(String name) {
        String charactersOnly = "[a-zA-Z ]+";

        if (name.equals("")) {
            responseMessage = "Name cannot be empty";
            return false;
        } else {
            if (name.matches(charactersOnly)) {
                return true;
            } else {
                responseMessage = "Name must be character only";
                return false;
            }
        }
    }

    private boolean isPhoneValid(String phone) {
        String numbersOnly = "[0-9]+";

        if (phone.equals("")) {
            responseMessage = "Phone number cannot be empty";
            return false;
        } else {
            if (phone.matches(numbersOnly)) {
                if (phone.length() >= 10 && phone.length() <= 13) {
                    return true;
                } else {
                    responseMessage = "Phone number must be 10-13 digit";
                    return false;
                }
            } else {
                responseMessage = "Phone number must be a number";
                return false;
            }
        }
    }

    public void deleteStudent(String id) {
        responseMessage = "";
        Student selectedStudent = getStudentById(id);

        if (selectedStudent != null) {
            selectedStudent.setActive(false);
            responseMessage = "Student with ID " + id + " successfully deleted";
        }
    }

    public void updateStudent(String id, String newName, String newPhone) {
        responseMessage = "";
        id = id.trim();
        newName = newName.trim();
        newPhone = newPhone.trim();
        Student selectedStudent = getStudentById(id);

        if (selectedStudent != null) {
            if (isNameValid(newName) && isPhoneValid(newPhone)) {
                selectedStudent.setName(newName);
                selectedStudent.setPhone(newPhone);
                responseMessage = "Student successfully updated";
            } else {
                selectedStudent.setName(selectedStudent.getName());
                selectedStudent.setPhone(selectedStudent.getPhone());
            }
        }
    }

    private void seedStudents() {
        if (students.isEmpty()) {
            students.add(new Student("111111", "Farhan 1", "111111111111"));
            students.add(new Student("222222", "Farhan 2", "222222222222"));
            students.add(new Student("333333", "Farhan 3", "333333333333"));
            students.add(new Student("444444", "Farhan 4", "444444444444"));
        }
    }
}
