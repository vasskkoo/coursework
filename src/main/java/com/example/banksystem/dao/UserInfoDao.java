package com.example.banksystem.dao;

import com.example.banksystem.context.UserContext;
import com.example.banksystem.database.config.DatabaseConnection;
import com.example.banksystem.database.schema.tables.records.UserInfoRecord;
import com.example.banksystem.model.UserInfo;
import org.jooq.DSLContext;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.banksystem.database.schema.tables.UserInfo.USER_INFO;

public class UserInfoDao implements Dao<UserInfo> {

    @Override
    public Optional get(long id) {
        return Optional.empty();
    }

    @Override
    public List<UserInfo> getAll() {
        List<UserInfo> userList = new ArrayList<>();

        try {
            Connection connection = DatabaseConnection.getConnection();
            DSLContext context = DSL.using(connection, SQLDialect.MYSQL);

            Result<UserInfoRecord> userRecords = context.selectFrom(USER_INFO)
                    .fetch();

            for (UserInfoRecord userRecord : userRecords)
                userList.add(new UserInfo(
                        userRecord.getValue(USER_INFO.ID),
                        UserContext.getInstance().getUser().getId(),
                        userRecord.getValue(USER_INFO.BANK_ID),
                        userRecord.getValue(USER_INFO.SUM),
                        userRecord.getValue(USER_INFO.TERM),
                        userRecord.getValue(USER_INFO.LOAN)
                ));

        } catch (SQLException e) {
            e.printStackTrace();
            e.getCause();
        }

        return userList;
    }

    @Override
    public void insert(UserInfo userInfo) {

        try {
            Connection connection = DatabaseConnection.getConnection();
            DSLContext context = DSL.using(connection, SQLDialect.MYSQL);

            context.insertInto(USER_INFO, USER_INFO.BANK_ID, USER_INFO.SUM, USER_INFO.TERM, USER_INFO.LOAN, USER_INFO.USER_ID)
                    .values(userInfo.getBankId(), userInfo.getSum(), userInfo.getTerm(), userInfo.getLoan(), UserContext.getInstance().getUser().getId())
                    .execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(UserInfo o, String[] params) {

    }

    @Override
    public void delete(UserInfo o) {

    }
}
