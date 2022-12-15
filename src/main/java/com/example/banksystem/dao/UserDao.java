package com.example.banksystem.dao;

import com.example.banksystem.database.config.DatabaseConnection;
import com.example.banksystem.database.schema.tables.records.UserRecord;
import com.example.banksystem.model.User;
import org.jooq.DSLContext;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.banksystem.database.schema.tables.User.USER;

public class UserDao implements Dao<User> {

    @Override
    public Optional<User> get(long id) {
        Optional<User> userOptional = Optional.empty();

        try {
            Connection connection = DatabaseConnection.getConnection();
            DSLContext context = DSL.using(connection, SQLDialect.MYSQL);

            UserRecord record = context.selectFrom(USER)
                    .where(USER.ID.eq((int) id))
                    .fetchAny();

            userOptional = Optional.of(new User(
                    record.getValue(USER.ID),
                    record.getValue(USER.NAME),
                    record.getValue(USER.SURNAME),
                    record.getValue(USER.USERNAME),
                    record.getValue(USER.PASSWORD),
                    record.getValue(USER.LOCATION),
                    record.getValue(USER.AGE),
                    record.getValue(USER.GENDER)
            ));

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userOptional;
    }

    @Override
    public List<User> getAll() {
        List<User> userList = new ArrayList<>();

        try {
            Connection connection = DatabaseConnection.getConnection();
            DSLContext context = DSL.using(connection, SQLDialect.MYSQL);

            Result<UserRecord> userRecords = context.selectFrom(USER)
                    .fetch();

            for (UserRecord userRecord : userRecords)
                userList.add(new User(userRecord.getValue(USER.ID), userRecord.getValue(USER.NAME), userRecord.getValue(USER.SURNAME),
                        userRecord.getValue(USER.USERNAME), userRecord.getValue(USER.PASSWORD), userRecord.getValue(USER.LOCATION),
                        userRecord.getValue(USER.AGE), userRecord.getValue(USER.GENDER)));

        } catch (SQLException e) {
            e.printStackTrace();
            e.getCause();
        }

        return userList;
    }

    @Override
    public void insert(User user) {

        try {
            Connection connection = DatabaseConnection.getConnection();
            DSLContext context = DSL.using(connection, SQLDialect.MYSQL);

            context.insertInto(USER, USER.NAME, USER.SURNAME, USER.USERNAME, USER.PASSWORD, USER.LOCATION, USER.AGE, USER.GENDER)
                    .values(user.getFirstname(), user.getLastname(), user.getUsername(), user.getPassword(), user.getLocation(),
                            user.getAge(), user.getGender())
                    .execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void update(User user, String[] params) {

    }

    @Override
    public void delete(User user) {

    }
}
