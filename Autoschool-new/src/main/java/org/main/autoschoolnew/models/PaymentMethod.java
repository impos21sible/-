package org.main.autoschoolnew.models;

import jakarta.persistence.*;

@Entity
@Table(name = "payment_methods", schema = "autoschool")
public class PaymentMethod {

    @Id
    @Column(name = "id_method")
    private Integer idMethod;

    @Column(name = "method_name", length = 50)
    private String methodName;

    // Getters and setters
    public Integer getIdMethod() { return idMethod; }
    public void setIdMethod(Integer idMethod) { this.idMethod = idMethod; }

    public String getMethodName() { return methodName; }
    public void setMethodName(String methodName) { this.methodName = methodName; }
}
