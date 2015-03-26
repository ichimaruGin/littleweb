package com.iwebirth.db.model;

import javax.persistence.*;

@Entity
@Table(name = "department")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name = "name", nullable = false, unique = true)
    String name;

    @Column(name = "location", nullable = false)
    String location;

    @Column(name = "latitude", nullable = false)
    String latitude;

    @Column(name = "longitude", nullable = false)
    String longitude;

    @Column(name = "function", nullable = false)
    String function;  //refer to StaticParam.java DEPARTMENT_FUNCTION


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public Department() {
    }

    public Department(String name, String location, String latitude, String longitude, String function) {
        this.name = name;
        this.location = location;
        this.latitude = latitude;
        this.longitude = longitude;
        this.function = function;
    }
}
