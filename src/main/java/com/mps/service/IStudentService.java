package com.mps.service;

import com.mps.entity.Student;

import java.util.List;
import java.util.Map;

public interface IStudentService {
    Long addStudent(Student student);
    List<Student> getAllStudent();
    void removeStudent(Long id);
    Student getOneStudent(Long id);
    Long updateStudent(Student student);
}
