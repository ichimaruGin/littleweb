package com.iwebirth.db.model;

import javax.persistence.*;

/**
 * Created by YY_410 on 2015/3/23.
 */
@Entity
@Table(name = "vehicle")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name = "terminal_id", nullable = false)
    String terminalId;

    @Column(name = "type", nullable = false)
    String type;

    @Column(name = "terminal_license", nullable = false)
    String terminalLicense;

    @Column(name = "belong_department_id", nullable = false)
    Integer belongId;

    @Column(name = "origin_department_id", nullable = false)
    Integer originId;

    @Column(name = "current_status", nullable = false)
    String currentStatus;

    @Column(name = "recent_rent_id", nullable = false)
    Integer recentRentId = 0;

    @Column(name = "recent_sell_id", nullable = false)
    Integer recentSellId = 0;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTerminalLicense() {
        return terminalLicense;
    }

    public void setTerminalLicense(String terminalLicense) {
        this.terminalLicense = terminalLicense;
    }

    public Integer getBelongId() {
        return belongId;
    }

    public void setBelongId(Integer belongId) {
        this.belongId = belongId;
    }

    public Integer getOriginId() {
        return originId;
    }

    public void setOriginId(Integer originId) {
        this.originId = originId;
    }

    public String getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(String currentStatus) {
        this.currentStatus = currentStatus;
    }

    public Integer getRecentRentId() {
        return recentRentId;
    }

    public void setRecentRentId(Integer recentRentId) {
        this.recentRentId = recentRentId;
    }

    public Integer getRecentSellId() {
        return recentSellId;
    }

    public void setRecentSellId(Integer recentSellId) {
        this.recentSellId = recentSellId;
    }

    public Vehicle() {

    }

    public Vehicle(Integer originId, String terminalId, String type, String terminalLicense, Integer belongId, String currentStatus, Integer recentRentId, Integer recentSellId) {
        this.originId = originId;
        this.terminalId = terminalId;
        this.type = type;
        this.terminalLicense = terminalLicense;
        this.belongId = belongId;
        this.currentStatus = currentStatus;
        this.recentRentId = recentRentId;
        this.recentSellId = recentSellId;
    }
}
