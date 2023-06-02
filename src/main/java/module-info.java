module org.example.sqlitedemo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.xerial.sqlitejdbc;


    opens org.example.sqlitedemo to javafx.fxml;
    exports org.example.sqlitedemo;
}