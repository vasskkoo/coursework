package com.example.banksystem.model;

public class BankType {

    private Integer id;
    private String name;

    public BankType(String name) {
        this.name = name;
    }

    public BankType(Integer id, String name) {
        this(name);
        this.id = id;
    }

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
}
