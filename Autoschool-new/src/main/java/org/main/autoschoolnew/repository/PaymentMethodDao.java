package org.main.autoschoolnew.repository;

import org.main.autoschoolnew.models.PaymentMethod;

public class PaymentMethodDao extends BaseDao<PaymentMethod> {
    public PaymentMethodDao(){
        super(PaymentMethod.class);
    }
}
