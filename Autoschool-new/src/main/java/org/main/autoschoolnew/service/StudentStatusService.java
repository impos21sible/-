package org.main.autoschoolnew.service;

import org.main.autoschoolnew.models.StudentStatus;
import org.main.autoschoolnew.repository.StudentStatusDao;

import java.util.List;

public class StudentStatusService {
    private final StudentStatusDao studentStatusDao = new StudentStatusDao();

    public StudentStatusService() {
    }

    // Получить список всех статусов студентов
    public List<StudentStatus> findAll() {
        return studentStatusDao.findAll();
    }

    // Найти конкретный статус студента по ID
    public StudentStatus findOne(final long id) {
        return studentStatusDao.findOne(id);
    }

    // Сохранить новый статус студента
    public void save(final StudentStatus entity) {
        if (entity == null)
            return;
        studentStatusDao.save(entity);
    }

    // Обновить существующий статус студента
    public void update(final StudentStatus entity) {
        if (entity == null)
            return;
        studentStatusDao.update(entity);
    }

    // Удалить статус студента
    public void delete(final StudentStatus entity) {
        if (entity == null)
            return;
        studentStatusDao.delete(entity);
    }

    // Удалить статус студента по его ID
    public void deleteById(final Long id) {
        if (id == null)
            return;
        studentStatusDao.deleteById(id);
    }
}
