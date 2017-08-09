package com.pangpang.controller;

import com.pangpang.domain.optimisticlock.Student;
import com.pangpang.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by leewake on 2017/8/9 0009.
 */

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @RequestMapping("/newStudent")
    public String student(){
        Student student = new Student();
        student.setName("xushequ");
        studentRepository.save(student);
        return "student";
    }

    @RequestMapping("/testVersion")
    public String testVersion() throws InterruptedException {
        final Student student = studentRepository.findOne("6ed16acc-61df-4a66-add9-d17c88b69755");
        student.setName("xushequ");
        new Thread(new Runnable() {
            @Override
            public void run() {
                studentRepository.findOne("6ed16acc-61df-4a66-add9-d17c88b69755");
                student.setName("xushequInThread");
                studentRepository.save(student);
            }
        }).start();
        Thread.sleep(1000);
        studentRepository.save(student);
        return "testVersion";
    }


    @RequestMapping("/updateNameById")
    public String updateNameById(){
        studentRepository.updateNameById("xushequ2","6ed16acc-61df-4a66-add9-d17c88b69755");
        return "updateNameById";
    }
}
