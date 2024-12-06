package org.main.autoschoolnew.service;

import org.main.autoschoolnew.models.VehicleType;
import org.main.autoschoolnew.repository.VehicleTypeDao;

import java.util.List;

public class VehicleTypeService {
    private final VehicleTypeDao vehicleTypeDao = new VehicleTypeDao();

    public VehicleTypeService() {
    }

    // Получить все типы транспортных средств
    public List<VehicleType> findAll() {
        return vehicleTypeDao.findAll();
    }

    // Найти тип транспортного средства по ID
    public VehicleType findOne(final long id) {
        return vehicleTypeDao.findOne(id);
    }

    // Сохранить новый тип транспортного средства
    public void save(final VehicleType entity) {
        if (entity == null)
            return;
        vehicleTypeDao.save(entity);
    }

    // Обновить тип транспортного средства
    public void update(final VehicleType entity) {
        if (entity == null)
            return;
        vehicleTypeDao.update(entity);
    }

    // Удалить тип транспортного средства
    public void delete(final VehicleType entity) {
        if (entity == null)
            return;
        vehicleTypeDao.delete(entity);
    }

    // Удалить тип транспортного средства по ID
    public void deleteById(final Long id) {
        if (id == null)
            return;
        vehicleTypeDao.deleteById(id);
    }
}
