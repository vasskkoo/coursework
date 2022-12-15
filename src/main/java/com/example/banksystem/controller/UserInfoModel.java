package com.example.banksystem.controller;

public class UserInfoModel {

    public Integer userId;
    public String firstname;
    public String lastname;
    public Integer age;
    public String username;
    public String password;
    public Integer sum;
    public Integer term;
    public Integer loan;

    public UserInfoModel(Integer userId, String firstname, String lastname, Integer age, String username, String password, Integer sum, Integer term, Integer loan) {
        this.userId = userId;
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
        this.username = username;
        this.password = password;
        this.sum = sum;
        this.term = term;
        this.loan = loan;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getSum() {
        return sum;
    }

    public void setSum(Integer sum) {
        this.sum = sum;
    }

    public Integer getTerm() {
        return term;
    }

    public void setTerm(Integer term) {
        this.term = term;
    }

    public Integer getLoan() {
        return loan;
    }

    public void setLoan(Integer loan) {
        this.loan = loan;
    }

    @Override
    public String toString() {
        return "UserInfoModel{" +
                "userId=" + userId +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", age=" + age +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", sum=" + sum +
                ", term=" + term +
                ", loan=" + loan +
                '}';
    }

}
