package org.main.autoschoolnew.service;

import org.main.autoschoolnew.models.PaymentStatus;
import org.main.autoschoolnew.repository.PaymentStatusDao;

import java.util.List;

public class PaymentStatusService {
    private final PaymentStatusDao paymentStatusDao = new PaymentStatusDao();

    public PaymentStatusService() {
    }

    // Найти все статусы оплаты
    public List<PaymentStatus> findAll() {
        return paymentStatusDao.findAll();
    }

    // Найти статус оплаты по ID
    public PaymentStatus findOne(final long id) {
        return paymentStatusDao.findOne(id);
    }

    // Сохранить новый статус оплаты
    public void save(final PaymentStatus entity) {
        if (entity == null)
            return;
        paymentStatusDao.save(entity);
    }

    // Обновить существующий статус оплаты
    public void update(final PaymentStatus entity) {
        if (entity == null)
            return;
        paymentStatusDao.update(entity);
    }

    // Удалить статус оплаты
    public void delete(final PaymentStatus entity) {
        if (entity == null)
            return;
        paymentStatusDao.delete(entity);
    }

    // Удалить статус оплаты по ID
    public void deleteById(final Long id) {
        if (id == null)
            return;
        paymentStatusDao.deleteById(id);
    }
}
