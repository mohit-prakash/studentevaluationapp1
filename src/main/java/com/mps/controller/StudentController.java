package com.mps.controller;

import com.mps.entity.Student;
import com.mps.exception.StudentNotFoundException;
import com.mps.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private IStudentService service;

    @GetMapping("/reg")
    public String studentRegister(Model model, @RequestParam(name = "message",required = false)String message){
        model.addAttribute("message",message);
        return "StudentReg";
    }

    @PostMapping("/save")
    public String saveStudent(@ModelAttribute Student student, RedirectAttributes attributes){
        if(isValidSection(student) && isValidMarks(student) &&
                ("Male".equalsIgnoreCase(student.getGender()) || "Female".equalsIgnoreCase(student.getGender()))
                    && isValidAge(student)) {
            Long id = service.addStudent(student);
            String message = "Student " + id + " saved successfully!!";
            attributes.addAttribute("message", message);
            return "redirect:reg";
        }else{
            attributes.addAttribute("message","Not a valid Entry!!");
            return "redirect:reg";
        }
    }

    private boolean isValidSection(Student student){
        if (student.getSection()=='A' || student.getSection()=='B' || student.getSection()=='C'){
            return true;
        }
        else {
            return false;
        }
    }
    private boolean isValidMarks(Student student){
        if ((student.getMarks1()>=0 && student.getMarks1()<=100) && (student.getMarks2()>=0 && student.getMarks2()<=100)
            && (student.getMarks3()>=0 && student.getMarks3()<=100)){
            return true;
        }
        else {
            return false;
        }
    }

    private boolean isValidAge(Student student){
        LocalDate dob=student.getDob();
        LocalDate curDate=LocalDate.now();
        if(Period.between(dob,curDate).getYears()>=15 && Period.between(dob,curDate).getYears()<=18) {
            return true;
        }
        else {
            return false;
        }
    }

    @GetMapping("/all")
    public String showAllStudents(Model model,@RequestParam(name = "message",required = false)String message){
        List<Student> studentList = service.getAllStudent();
        model.addAttribute("students",studentList);
        model.addAttribute("message",message);
        return "StudentData";
    }

    @GetMapping("/edit")
    public String editStudent(@RequestParam("id") Long id,Model model,RedirectAttributes attributes){
        try {
            Student student = service.getOneStudent(id);
            model.addAttribute("student",student);
            return "StudentEdit";
        }
        catch (StudentNotFoundException snfe)
        {
            attributes.addAttribute("message",snfe.getMessage());
            return "redirect:all";
        }
    }

    @GetMapping("/delete")
    public String deleteStudent(@RequestParam("id") Long id, RedirectAttributes attributes){
        try {
            service.removeStudent(id);
            attributes.addAttribute("message","Student "+id+" removed successfully!!");
            return "redirect:all";
        }
        catch (StudentNotFoundException snfe)
        {
            attributes.addAttribute("message",snfe.getMessage());
            return "redirect:all";
        }
    }

    @PostMapping("/update")
    public String updateStudent(@ModelAttribute Student student,RedirectAttributes attributes){
        Long id = service.updateStudent(student);
        String message="Student "+id+" updated successfully!!";
        attributes.addAttribute("message",message);
        return "redirect:all";
    }
}
