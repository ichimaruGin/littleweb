package com.iwebirth.db.model;

import javax.persistence.*;

/**
 * Created by YY_410 on 2015/3/23.
 */
@Entity
@Table(name="sell_info")
public class SellInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name="terminal_id",nullable = false)
    String terminalId;

    @Column(name="sell_time",nullable = false)
    Long sellTime;

    @Column(name="seller_id",nullable = false)
    Integer sellerId;

    @Column(name="buyer_id",nullable = false)
    Integer buyerId;

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

    public Long getSellTime() {
        return sellTime;
    }

    public void setSellTime(Long sellTime) {
        this.sellTime = sellTime;
    }

    public Integer getSellerId() {
        return sellerId;
    }

    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }

    public Integer getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(Integer buyerId) {
        this.buyerId = buyerId;
    }

    public SellInfo(){

    }

    public SellInfo(String terminalId, Long sellTime, Integer sellerId, Integer buyerId) {
        this.terminalId = terminalId;
        this.sellTime = sellTime;
        this.sellerId = sellerId;
        this.buyerId = buyerId;
    }
}
