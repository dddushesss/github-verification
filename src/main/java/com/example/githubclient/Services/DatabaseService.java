package com.example.githubclient.Services;

import com.example.githubclient.Model.Student;
import com.example.githubclient.Model.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public class DatabaseService {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private StudentMapper studentMapper;


    public List<Student> getStudents() {
        return jdbcTemplate.query("select Last_Name, First_Name, GitLogin, Repository from Student", studentMapper);
    }
}
