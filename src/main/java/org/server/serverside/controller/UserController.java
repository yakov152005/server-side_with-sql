package org.server.serverside.controller;

import org.server.serverside.service.Student;
import org.server.serverside.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private StudentRepository studentRepository;

    @PostMapping("/addStudent")
    public String addStudent(@RequestBody Student student) {
        if (student.getName() != null && student.getAge() > 0 && student.getId() > 0) {
            studentRepository.save(student);
            return "Student added successfully: " + student.getName();
        } else {
            return "Error: Invalid student data.";
        }
    }

    @GetMapping("/getStudentById/{id}")
    public Student getStudentById(@PathVariable int id) {
        return studentRepository.findById(id).orElse(null);
    }

    @GetMapping("/getAllStudents")
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @DeleteMapping("/deleteStudentByName/{name}")
    public boolean deleteStudentByName(@PathVariable String name){
        List<Student> students = studentRepository.findAll();
        boolean isSuccess = false;
        for (Student student : students) {
            if (student.getName().equals(name)) {
                studentRepository.delete(student);
                System.out.println("Delete Student " + name + " is Success." );
                isSuccess = true;
                break;
            }
        }
        if (!isSuccess){
            System.err.println("Delete Student NOT is Success." );
        }
        return isSuccess;
    }

}


/*
package org.server.serverside.controller;


import org.server.serverside.service.Student;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private static final List<Student> studentList = new ArrayList<>();

    @PostMapping("/addStudent")
    public String addStudent(@RequestBody Student student) {
        if (student.getName() != null && student.getAge() > 0 && student.getId() > 0) {
            studentList.add(student);
            return "Student added successfully: " + student.getName();
        } else {
            return "Error: Invalid student data.";
        }
    }

    @GetMapping("/getStudentById/{id}")
    public Student getStudentById(@PathVariable int id) {
        return studentList.stream()
                .filter(student -> student.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @GetMapping("/getAllStudents")
    public List<Student> getAllStudents() {
        studentList
                .forEach(student ->
                System.out.println(
                        "------- \n" +"Name:" + student.getName() + "\nAge: " + student.getAge() + "\nId: " + student.getId() + "\n"));
        return studentList;
    }

    @DeleteMapping("/deleteStudentByName/{name}")
    public boolean deleteStudentByName(@PathVariable String name){
        boolean isSuccess = false;
        String nameDelete = "";
        System.out.println(name);
        for (int i = 0; i < studentList.size(); i++) {
            nameDelete = studentList.get(i).getName();
            if (name.equals(nameDelete)){
                studentList.remove(i);
                System.out.println("Delete Student " + nameDelete + " is Success." );
                isSuccess = true;
                break;
            }
        }
        if (!isSuccess){
            System.err.println("Delete Student NOT is Success." );
        }
        return isSuccess;
    }
}
 */




