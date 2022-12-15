package com.example.banksystem.model;

import org.jooq.Name;

public class Bank {
    private  Integer id;

    private String Name;

    private Integer max_sum;
    private Integer min_sum;

    private Integer max_term;
    private Integer min_term;

    private Double percent;


    public Bank(Integer id, String name, Integer max_sum, Integer min_sum, Integer max_term, Integer min_term, Double percent) {
        this(name,max_sum,min_sum,max_term,min_term,percent);
        this.id = id;

    }

    public Bank(String name, Integer max_sum, Integer min_sum, Integer max_term, Integer min_term, Double percent) {
        this.Name = name;
        this.max_sum = max_sum;
        this.min_sum = min_sum;
        this.max_term = max_term;
        this.min_term = min_term;
        this.percent = percent;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Integer getMax_sum() {
        return max_sum;
    }

    public void setMax_sum(Integer max_sum) {
        this.max_sum = max_sum;
    }

    public Integer getMin_sum() {
        return min_sum;
    }

    public void setMin_sum(Integer min_sum) {
        this.min_sum = min_sum;
    }

    public Integer getMax_term() {
        return max_term;
    }

    public void setMax_term(Integer max_term) {
        this.max_term = max_term;
    }

    public Integer getMin_term() {
        return min_term;
    }

    public void setMin_term(Integer min_term) {
        this.min_term = min_term;
    }

    public Double getPercent() {
        return percent;
    }

    public void setPercent(Double percent) {
        this.percent = percent;
    }

    @Override
    public String toString() {
        return "Bank{" +
                "id=" + id +
                ", Name='" + Name + '\'' +
                ", max_sum=" + max_sum +
                ", min_sum=" + min_sum +
                ", max_term=" + max_term +
                ", min_term=" + min_term +
                ", percent=" + percent +
                '}';
    }
}
