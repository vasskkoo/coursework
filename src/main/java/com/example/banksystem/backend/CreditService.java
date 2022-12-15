package com.example.banksystem.backend;

import com.example.banksystem.dao.BankInfoDao;
import com.example.banksystem.dao.Dao;
import com.example.banksystem.model.BankInfo;

import java.util.Optional;

public class CreditService {

    private static Dao<BankInfo> bankInfoDao;

    public static double getCreditSum(int sum, int term, int bankId) {
        bankInfoDao = new BankInfoDao();
        Optional<BankInfo> bankInfoOptional = bankInfoDao.get(bankId);


        double percent = bankInfoOptional.get().getPercent();

        double credit = sum + (sum * percent * term/12)/100;

        return credit;
    }

    public static double getCreditSumResult(int sum,double creditsume){
        double rescredit = creditsume - sum;
        return rescredit;
    }

    public static double getMontroothsToPay(double creditsum){
        double mounspay = creditsum/12;
        return mounspay;
   }


}
