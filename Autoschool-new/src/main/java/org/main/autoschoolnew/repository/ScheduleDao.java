package org.main.autoschoolnew.repository;

import org.main.autoschoolnew.models.Schedule;

public class ScheduleDao extends BaseDao<Schedule> {
    // Здесь можно добавить специфичные для расписания методы, если они нужны
    // Например, методы для фильтрации расписания по группам или датам
    public ScheduleDao() {
        super(Schedule.class); // Инициализация базового класса с моделью Schedule
    }
}
