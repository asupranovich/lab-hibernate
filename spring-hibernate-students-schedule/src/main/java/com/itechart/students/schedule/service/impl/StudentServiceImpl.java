package com.itechart.students.schedule.service.impl;

import com.itechart.students.schedule.dao.StudentDao;
import com.itechart.students.schedule.model.Student;
import com.itechart.students.schedule.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author asupranovich
 */
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentDao studentDao;
    
    @Transactional(readOnly = true)
    @Override
    public Student getById(long id) {
        return studentDao.getById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public Student getWithAllDataById(long id) {
        return studentDao.getWithAllData(id);
    }

    @Transactional
    @Override
    public void save(Student student) {
        
        if (student == null) {
            throw new RuntimeException("Student should not be null!");
        }
        
        Long studentId = student.getId();
        if (studentId == null) {
            studentDao.create(student);
        } else {
            studentDao.update(student);
        }
    }

}
