module com.involuntary.revpos {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.involuntary.revpos to javafx.fxml;
    exports com.involuntary.revpos;
    exports com.involuntary.revpos.controller;
    opens com.involuntary.revpos.controller to javafx.fxml;
}