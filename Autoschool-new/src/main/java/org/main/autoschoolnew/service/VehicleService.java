package org.main.autoschoolnew.service;

import org.main.autoschoolnew.models.Vehicle;
import org.main.autoschoolnew.repository.VehicleDao;

import java.util.List;

public class VehicleService {
    private final VehicleDao vehicleDao = new VehicleDao();

    public VehicleService() {
    }

    // Получить все транспортные средства
    public List<Vehicle> findAll() {
        return vehicleDao.findAll();
    }

    // Найти транспортное средство по ID
    public Vehicle findOne(final long id) {
        return vehicleDao.findOne(id);
    }

    // Сохранить новое транспортное средство
    public void save(final Vehicle entity) {
        if (entity == null)
            return;
        vehicleDao.save(entity);
    }

    // Обновить транспортное средство
    public void update(final Vehicle entity) {
        if (entity == null)
            return;
        vehicleDao.update(entity);
    }

    // Удалить транспортное средство
    public void delete(final Vehicle entity) {
        if (entity == null)
            return;
        vehicleDao.delete(entity);
    }

    // Удалить транспортное средство по ID
    public void deleteById(final Long id) {
        if (id == null)
            return;
        vehicleDao.deleteById(id);
    }
}
