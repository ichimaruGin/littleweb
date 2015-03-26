package com.iwebirth.db.model;

import javax.persistence.*;


/**
 * Created by YY_410 on 2015/3/23.
 */
@Entity
@Table(name = "rent_info")
public class RentInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name="terminal_id",nullable = false)
    String terminalId;

    @Column(name="start_time",nullable = false)
    Long startTime;

    @Column(name="deadline",nullable = false)
    Long deadline;

    @Column(name="lesseer_id",nullable = false)
    Integer lesserId;

    @Column(name="renter_id",nullable = false)
    Integer renterId;

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

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getDeadline() {
        return deadline;
    }

    public void setDeadline(Long deadline) {
        this.deadline = deadline;
    }

    public Integer getRenterId() {
        return renterId;
    }

    public void setRenterId(Integer renterId) {
        this.renterId = renterId;
    }

    public Integer getLesserId() {
        return lesserId;
    }

    public void setLesserId(Integer lesserId) {
        this.lesserId = lesserId;
    }

    public RentInfo(){

    }

    public RentInfo(String terminalId, Long startTime, Long deadline, Integer lesserId, Integer renterId) {
        this.terminalId = terminalId;
        this.startTime = startTime;
        this.deadline = deadline;
        this.lesserId = lesserId;
        this.renterId = renterId;
    }
}
