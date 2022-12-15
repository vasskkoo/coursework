module com.example.banksystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires org.jooq;
    requires fontawesomefx;


    opens com.example.banksystem to javafx.fxml;
    opens com.example.banksystem.controller to javafx.fxml;
    exports com.example.banksystem;
    exports com.example.banksystem.controller;
    exports com.example.banksystem.database.config;
    exports com.example.banksystem.database.schema.tables.records to org.jooq;
    exports com.example.banksystem.database.schema.tables to com.example.banksystem.controller;
}