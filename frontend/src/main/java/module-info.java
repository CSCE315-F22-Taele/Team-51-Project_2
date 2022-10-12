module com.involuntary.revpos {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.involuntary.revpos to javafx.fxml;
    exports com.involuntary.revpos;
    exports com.involuntary.revpos.controller;
    opens com.involuntary.revpos.controller to javafx.fxml;
    exports com.involuntary.revpos.database;
    opens com.involuntary.revpos.database to javafx.fxml;
    exports com.involuntary.revpos.models;
    opens com.involuntary.revpos.models to javafx.fxml;
}