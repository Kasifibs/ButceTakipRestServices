package com.dispinar.butcetakip.server.iteminstances.entity;

import com.dispinar.butcetakip.server.common.entity.Period;
import com.dispinar.butcetakip.server.common.entity.User;
import com.dispinar.butcetakip.server.itemoperations.entity.IncomeItem;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by Tolga on 13.04.2017.
 */

@Entity
@Table(name="INCOME")
public class Income {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="ID")
    private Long id;

    @JsonIgnore
    @ManyToOne(optional=false, fetch=FetchType.LAZY)
    @JoinColumn(name="USER_ID", referencedColumnName="user_id")
    private User user;

    @ManyToOne(optional=false, fetch=FetchType.EAGER)
    @JoinColumn(name="INCOME_ITEM_ID", referencedColumnName="ID")
    private IncomeItem incomeItem;

    @ManyToOne(optional=false, fetch=FetchType.EAGER)
    @JoinColumn(name="PERIOD_ID", referencedColumnName="ID")
    private Period period;

    @Column ( name="AMOUNT", precision = 13, scale = 2 )
    private BigDecimal amount;

    public void copy(Income detachedIncome){
        this.incomeItem = detachedIncome.getIncomeItem();
        this.period = detachedIncome.getPeriod();
        this.amount = detachedIncome.getAmount();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public IncomeItem getIncomeItem() {
        return incomeItem;
    }

    public void setIncomeItem(IncomeItem incomeItem) {
        this.incomeItem = incomeItem;
    }

    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
