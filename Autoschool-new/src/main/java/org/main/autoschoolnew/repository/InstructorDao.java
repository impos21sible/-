package org.main.autoschoolnew.repository;


import org.main.autoschoolnew.models.Instructor;

public class InstructorDao extends BaseDao<Instructor> {
    public InstructorDao() {
        super(Instructor.class);
    }
}