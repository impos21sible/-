package org.main.autoschoolnew.repository;

import org.main.autoschoolnew.models.StudentStatus;

public class StudentStatusDao extends BaseDao<StudentStatus> {
    public StudentStatusDao(){
        super(StudentStatus.class);
    }
}
