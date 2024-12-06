module org.main.autoschoolnew {
    requires javafx.controls;
    requires javafx.fxml;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;
    requires java.naming;
    requires java.desktop;
    requires javafx.swing;


    opens org.main.autoschoolnew to javafx.fxml;
    opens org.main.autoschoolnew.models to org.hibernate.orm.core;
    exports org.main.autoschoolnew;
    exports org.main.autoschoolnew.controllers;
    opens org.main.autoschoolnew.controllers to javafx.fxml;
    opens org.main.autoschoolnew.util to org.hibernate.orm.core;
}