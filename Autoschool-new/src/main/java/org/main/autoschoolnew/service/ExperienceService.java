package org.main.autoschoolnew.service;

import org.main.autoschoolnew.models.Experience;
import org.main.autoschoolnew.repository.ExperienceDao;

import java.util.List;

public class ExperienceService {
    private final ExperienceDao experienceDao = new ExperienceDao();

    public ExperienceService() {
    }

    // Найти все записи опыта
    public List<Experience> findAll() {
        return experienceDao.findAll();
    }

    // Найти запись опыта по ID
    public Experience findOne(final long id) {
        return experienceDao.findOne(id);
    }

    // Сохранить новую запись опыта
    public void save(final Experience entity) {
        if (entity == null)
            return;
        experienceDao.save(entity);
    }

    // Обновить существующую запись опыта
    public void update(final Experience entity) {
        if (entity == null)
            return;
        experienceDao.update(entity);
    }

    // Удалить запись опыта
    public void delete(final Experience entity) {
        if (entity == null)
            return;
        experienceDao.delete(entity);
    }

    // Удалить запись опыта по ID
    public void deleteById(final Long id) {
        if (id == null)
            return;
        experienceDao.deleteById(id);
    }
}
