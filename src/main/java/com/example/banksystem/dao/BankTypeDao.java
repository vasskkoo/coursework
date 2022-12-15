package com.example.banksystem.dao;

import com.example.banksystem.database.config.DatabaseConnection;
import com.example.banksystem.database.schema.tables.records.BankTypeRecord;
import com.example.banksystem.model.BankType;
import org.jooq.DSLContext;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.banksystem.database.schema.tables.BankType.BANK_TYPE;

public class BankTypeDao implements Dao<BankType> {

    @Override
    public Optional<BankType> get(long id) {

        Optional<BankType> bankTypeOptional = Optional.empty();

        try {
            Connection connection = DatabaseConnection.getConnection();
            DSLContext context = DSL.using(connection, SQLDialect.MYSQL);

            BankTypeRecord record = context.selectFrom(BANK_TYPE)
                    .where(BANK_TYPE.ID.eq((int) id))
                    .fetchAny();

            BankType bankType = new BankType(
                    record.getValue(BANK_TYPE.ID),
                    record.getValue(BANK_TYPE.NAME)
            );

            bankTypeOptional = Optional.of(bankType);

        } catch (SQLException e) {
            e.printStackTrace();
            e.getCause();
        }

        return bankTypeOptional;
    }

    @Override
    public List<BankType> getAll() {
        List<BankType> bankTypes = new ArrayList<>();

        try {
            Connection connection = DatabaseConnection.getConnection();
            DSLContext context = DSL.using(connection, SQLDialect.MYSQL);

            Result<BankTypeRecord> bankTypeRecords = context.selectFrom(BANK_TYPE)
                    .fetch();

            for (BankTypeRecord record : bankTypeRecords)
                bankTypes.add(new BankType(record.getValue(BANK_TYPE.ID), record.getValue(BANK_TYPE.NAME)));

        } catch (SQLException e) {
            e.printStackTrace();
            e.getCause();
        }

        return bankTypes;
    }

    @Override
    public void insert(BankType bankType) {

    }

    @Override
    public void update(BankType bankType, String[] params) {

    }

    @Override
    public void delete(BankType bankType) {

    }
}
