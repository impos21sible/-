package org.main.autoschoolnew.models;

import jakarta.persistence.*;

@Entity
@Table(name = "payment_statuses", schema = "autoschool")
public class PaymentStatus {

    @Id
    @Column(name = "id_payment_status")
    private Integer idPaymentStatus;

    @Column(name = "name", length = 50)
    private String name;

    // Getters and setters
    public Integer getIdPaymentStatus() { return idPaymentStatus; }
    public void setIdPaymentStatus(Integer idPaymentStatus) { this.idPaymentStatus = idPaymentStatus; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
