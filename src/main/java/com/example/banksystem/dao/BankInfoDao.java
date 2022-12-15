package com.example.banksystem.dao;

import com.example.banksystem.database.config.DatabaseConnection;
import com.example.banksystem.database.schema.tables.records.BankInfoRecord;
import com.example.banksystem.model.BankInfo;
import org.jooq.DSLContext;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.banksystem.database.schema.tables.BankInfo.BANK_INFO;

public class BankInfoDao implements Dao<BankInfo> {
    @Override
    public Optional<BankInfo> get(long id) {

        Optional<BankInfo> bankInfoOptional = Optional.empty();

        try {
            Connection connection = DatabaseConnection.getConnection();
            DSLContext context = DSL.using(connection, SQLDialect.MYSQL);

            BankInfoRecord record = context.selectFrom(BANK_INFO)
                    .where(BANK_INFO.BANK_ID.eq((int) id))
                    .fetchAny();

            BankInfo bankInfo = new BankInfo(
                    record.getValue(BANK_INFO.ID),
                    record.getValue(BANK_INFO.BANK_ID),
                    record.getValue(BANK_INFO.MIN_SUM),
                    record.getValue(BANK_INFO.MAX_SUM),
                    record.getValue(BANK_INFO.MIN_TERM),
                    record.getValue(BANK_INFO.MAX_TERM),
                    record.getValue(BANK_INFO.PERCENT)
            );

            bankInfoOptional = bankInfoOptional.of(bankInfo);

        } catch (SQLException e) {
            e.printStackTrace();
            e.getCause();
        }

        return bankInfoOptional;
    }

    @Override
    public List<BankInfo> getAll() {
        List<BankInfo> bankInfos = new ArrayList<>();

        try {
            Connection connection = DatabaseConnection.getConnection();
            DSLContext context = DSL.using(connection, SQLDialect.MYSQL);

            Result<BankInfoRecord> bankInfoRecords = context.selectFrom(BANK_INFO)
                    .fetch();

            for (BankInfoRecord record : bankInfoRecords)
                bankInfos.add(new BankInfo(
                        record.getValue(BANK_INFO.ID),
                        record.getValue(BANK_INFO.BANK_ID),
                        record.getValue(BANK_INFO.MIN_SUM),
                        record.getValue(BANK_INFO.MAX_SUM),
                        record.getValue(BANK_INFO.MIN_TERM),
                        record.getValue(BANK_INFO.MAX_TERM),
                        record.getValue(BANK_INFO.PERCENT)
                ));

        } catch (SQLException e) {
            e.printStackTrace();
            e.getCause();
        }

        return bankInfos;
    }

    @Override
    public void insert(BankInfo bankInfo) {

    }

    @Override
    public void update(BankInfo bankInfo, String[] params) {

    }

    @Override
    public void delete(BankInfo bankInfo) {

    }
}
