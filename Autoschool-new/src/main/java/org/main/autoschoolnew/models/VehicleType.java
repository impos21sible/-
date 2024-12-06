package org.main.autoschoolnew.models;

import jakarta.persistence.*;

@Entity
@Table(name = "vehicle_types", schema = "autoschool")
public class VehicleType {

    @Id
    @Column(name = "id_vehicle_type")
    private Integer idVehicleType;

    @Column(name = "type_name", length = 50, nullable = false)
    private String typeName;

    // Getters and setters
    public Integer getIdVehicleType() { return idVehicleType; }
    public void setIdVehicleType(Integer idVehicleType) { this.idVehicleType = idVehicleType; }

    public String getTypeName() { return typeName; }
    public void setTypeName(String typeName) { this.typeName = typeName; }
}
