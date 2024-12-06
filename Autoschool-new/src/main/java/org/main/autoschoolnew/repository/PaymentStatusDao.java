package org.main.autoschoolnew.repository;

import org.main.autoschoolnew.models.PaymentStatus;

public class PaymentStatusDao extends BaseDao<PaymentStatus >{
    public PaymentStatusDao(){
        super(PaymentStatus.class);
    }
}
