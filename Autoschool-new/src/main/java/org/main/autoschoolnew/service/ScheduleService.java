package org.main.autoschoolnew.service;

import org.main.autoschoolnew.models.Schedule;
import org.main.autoschoolnew.repository.ScheduleDao;

import java.util.List;

public class ScheduleService {
    private final ScheduleDao scheduleDao = new ScheduleDao();

    public ScheduleService() {
    }

    // Найти все расписания
    public List<Schedule> findAll() {
        return scheduleDao.findAll();
    }

    // Найти расписание по ID
    public Schedule findOne(final long id) {
        return scheduleDao.findOne(id);
    }

    // Сохранить новое расписание
    public void save(final Schedule entity) {
        if (entity == null)
            return;
        scheduleDao.save(entity);
    }

    // Обновить существующее расписание
    public void update(final Schedule entity) {
        if (entity == null)
            return;
        scheduleDao.update(entity);
    }

    // Удалить расписание
    public void delete(final Schedule entity) {
        if (entity == null)
            return;
        scheduleDao.delete(entity);
    }

    // Удалить расписание по ID
    public void deleteById(final Long id) {
        if (id == null)
            return;
        scheduleDao.deleteById(id);
    }
}
