package com.example.banksystem.controller;

public class BankInfoModel {
    public Integer Idbank ;
    public String name;
    public Integer min_sum;
    public Integer max_sum;
    public Integer min_term;
    public Integer max_term;
    public Double percent;

    public BankInfoModel(Integer idbank, String name, Integer min_sum, Integer max_sum, Integer min_term, Integer max_term, Double percent) {
        this.Idbank = idbank;
        this.name = name;
        this.min_sum = min_sum;
        this.max_sum = max_sum;
        this.min_term = min_term;
        this.max_term = max_term;
        this.percent = percent;
    }

    public Integer getIdbank() {
        return Idbank;
    }

    public void setIdbank(Integer idbank) {
        Idbank = idbank;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMin_sum() {
        return min_sum;
    }

    public void setMin_sum(Integer min_sum) {
        this.min_sum = min_sum;
    }

    public Integer getMax_sum() {
        return max_sum;
    }

    public void setMax_sum(Integer max_sum) {
        this.max_sum = max_sum;
    }

    public Integer getMin_term() {
        return min_term;
    }

    public void setMin_term(Integer min_term) {
        this.min_term = min_term;
    }

    public Integer getMax_term() {
        return max_term;
    }

    public void setMax_term(Integer max_term) {
        this.max_term = max_term;
    }

    public Double getPercent() {
        return percent;
    }

    public void setPercent(Double percent) {
        this.percent = percent;
    }

    @Override
    public String toString() {
        return "BankInfoControler{" +
                "Idbank=" + Idbank +
                ", name='" + name + '\'' +
                ", min_sum=" + min_sum +
                ", max_sum=" + max_sum +
                ", min_term=" + min_term +
                ", max_term=" + max_term +
                ", percent=" + percent +
                '}';
    }
}
