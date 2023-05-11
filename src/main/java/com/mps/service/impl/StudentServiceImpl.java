package com.mps.service.impl;

import com.mps.entity.Student;
import com.mps.exception.StudentNotFoundException;
import com.mps.repository.StudentRepository;
import com.mps.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements IStudentService {
    @Autowired
    private StudentRepository repo;

    @Override
    public Long addStudent(Student student) {
        if(student.getMarks1()>=35 && student.getMarks2()>=35 && student.getMarks3()>=35){
            student.setResult("Pass");
        }else {
            student.setResult("Fail");
        }
        student.setTotal(student.getMarks1()+student.getMarks2()+student.getMarks3());
        student.setAverage(student.getTotal()/3);
        return repo.save(student).getId();
    }

    @Override
    public List<Student> getAllStudent() {
        return repo.findAll();
    }

    @Override
    public void removeStudent(Long id) {
        repo.delete(getOneStudent(id));
    }

    @Override
    public Student getOneStudent(Long id) {
        return repo.findById(id).orElseThrow(()->new StudentNotFoundException("Student "+id+" not found!!"));
    }

    @Override
    public Long updateStudent(Student student) {
        if(student.getMarks1()>=35 && student.getMarks2()>=35 && student.getMarks3()>=35){
            student.setResult("Pass");
        }else {
            student.setResult("Fail");
        }
        student.setTotal(student.getMarks1()+student.getMarks2()+student.getMarks3());
        student.setAverage(student.getTotal()/3);
        return repo.save(student).getId();
    }

}
