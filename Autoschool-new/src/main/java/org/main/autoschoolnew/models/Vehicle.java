package org.main.autoschoolnew.models;

import jakarta.persistence.*;

@Entity
@Table(name = "vehicles", schema = "autoschool")
public class Vehicle {

    @Id
    @Column(name = "id_vehicle")
    private Integer idVehicle;

    @ManyToOne
    @JoinColumn(name = "vehicle_type", nullable = false)
    private VehicleType vehicleType;

    @Column(name = "vehicle_model", length = 100, nullable = false)
    private String vehicleModel;

    @Column(name = "registration_number", length = 20, nullable = false)
    private String registrationNumber;

    // Getters and setters
    public Integer getIdVehicle() { return idVehicle; }
    public void setIdVehicle(Integer idVehicle) { this.idVehicle = idVehicle; }

    public VehicleType getVehicleType() { return vehicleType; }
    public void setVehicleType(VehicleType vehicleType) { this.vehicleType = vehicleType; }

    public String getVehicleModel() { return vehicleModel; }
    public void setVehicleModel(String vehicleModel) { this.vehicleModel = vehicleModel; }

    public String getRegistrationNumber() { return registrationNumber; }
    public void setRegistrationNumber(String registrationNumber) { this.registrationNumber = registrationNumber; }
}
