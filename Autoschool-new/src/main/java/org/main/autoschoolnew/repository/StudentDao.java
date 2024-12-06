package org.main.autoschoolnew.repository;

import org.main.autoschoolnew.models.Student;

public class StudentDao extends BaseDao<Student> {
    public StudentDao(){
        super(Student.class);
    }
}
