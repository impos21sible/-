package org.main.autoschoolnew.service;

import org.main.autoschoolnew.models.Instructor;
import org.main.autoschoolnew.repository.InstructorDao;

import java.util.List;

public class InstructorService {
    private final InstructorDao instructorDao = new InstructorDao();

    public InstructorService() {
    }

    // Найти все записи опыта
    public List<Instructor> findAll() {
        return instructorDao.findAll();
    }

    // Найти запись опыта по ID
    public Instructor findOne(final long id) {
        return instructorDao.findOne(id);
    }

    // Сохранить новую запись опыта
    public void save(final Instructor entity) {
        if (entity == null)
            return;
        instructorDao.save(entity);
    }

    // Обновить существующую запись опыта
    public void update(final Instructor entity) {
        if (entity == null)
            return;
        instructorDao.update(entity);
    }

    // Удалить запись опыта
    public void delete(final Instructor entity) {
        if (entity == null)
            return;
        instructorDao.delete(entity);
    }

    // Удалить запись опыта по ID
    public void deleteById(final Long id) {
        if (id == null)
            return;
        instructorDao.deleteById(id);
    }
}
