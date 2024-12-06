package org.main.autoschoolnew.service;

import org.main.autoschoolnew.models.PaymentMethod;
import org.main.autoschoolnew.repository.PaymentMethodDao;

import java.util.List;

public class PaymentMethodService {
    private final PaymentMethodDao paymentMethodDao = new PaymentMethodDao();

    public PaymentMethodService() {
    }

    // Найти все методы оплаты
    public List<PaymentMethod> findAll() {
        return paymentMethodDao.findAll();
    }

    // Найти метод оплаты по ID
    public PaymentMethod findOne(final long id) {
        return paymentMethodDao.findOne(id);
    }

    // Сохранить новый метод оплаты
    public void save(final PaymentMethod entity) {
        if (entity == null)
            return;
        paymentMethodDao.save(entity);
    }

    // Обновить существующий метод оплаты
    public void update(final PaymentMethod entity) {
        if (entity == null)
            return;
        paymentMethodDao.update(entity);
    }

    // Удалить метод оплаты
    public void delete(final PaymentMethod entity) {
        if (entity == null)
            return;
        paymentMethodDao.delete(entity);
    }

    // Удалить метод оплаты по ID
    public void deleteById(final Long id) {
        if (id == null)
            return;
        paymentMethodDao.deleteById(id);
    }
}
